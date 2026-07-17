package com.example.myapplicationclean.presentation.ble


import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothStatusCodes
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper

/**
 * All Bluetooth logic lives here so the Activity/Adapter stay UI-only.
 *
 * Two responsibilities:
 *   1. scan()  -> report discovered devices to the UI
 *   2. openLock(device) -> connect, send the OPEN command, then disconnect
 *
 * NOTE: the caller (Activity) is responsible for requesting the runtime
 * permissions BLUETOOTH_SCAN and BLUETOOTH_CONNECT before using this class.
 */
@SuppressLint("MissingPermission")
class BleLockManager(private val context: Context) {

    private val mainHandler = Handler(Looper.getMainLooper())

    private val bluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private val adapter: BluetoothAdapter? = bluetoothManager.adapter
    private val scanner get() = adapter?.bluetoothLeScanner

    val isBluetoothOn: Boolean get() = adapter?.isEnabled == true

    // ----------------------------------------------------------------
    // 1. SCANNING
    // ----------------------------------------------------------------

    private var scanCallback: ScanCallback? = null

    /** Starts a scan. [onDeviceFound] is called on the main thread per device. */
    fun startScan(onDeviceFound: (BluetoothDevice) -> Unit) {
        stopScan()

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                mainHandler.post { onDeviceFound(result.device) }
            }
        }
        scanner?.startScan(null, settings, scanCallback)
    }

    fun stopScan() {
        scanCallback?.let { scanner?.stopScan(it) }
        scanCallback = null
    }

    // ----------------------------------------------------------------
    // 2. OPEN LOCK  (connect -> send command -> disconnect)
    // ----------------------------------------------------------------

    interface LockCallback {
        fun onStatus(message: String)
        fun onSuccess()
        fun onError(message: String)
    }

    /**
     * The one method the Activity calls from the adapter's "Open" button.
     *
     * Connects to [device], discovers services, writes the OPEN packet to the
     * first writable characteristic, then disconnects & closes the connection.
     *
     * Set [isLock] = true if you want to send the LOCK command instead.
     */
    fun openLock(device: BluetoothDevice, isLock: Boolean = false, callback: LockCallback) {
        val payload = PacketBuilder.buildLockPacket(device.address, isLock)

        device.connectGatt(context, false, object : BluetoothGattCallback() {

            // Report success/error exactly once.
            private var reported = false
            private fun reportSuccess() {
                if (!reported) { reported = true; post(callback) { it.onSuccess() } }
            }
            private fun reportError(msg: String) {
                if (!reported) { reported = true; post(callback) { it.onError(msg) } }
            }

            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                when (newState) {
                    BluetoothGatt.STATE_CONNECTED -> {
                        post(callback) { it.onStatus("Connected, discovering services…") }
                        gatt.discoverServices()
                    }
                    BluetoothGatt.STATE_DISCONNECTED -> {
                        gatt.close()
                        post(callback) { it.onStatus("Disconnected") }
                    }
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                val writeChar = findWritableCharacteristic(gatt)
                if (writeChar == null) {
                    reportError("No writable characteristic found")
                    gatt.disconnect()
                    return
                }
                post(callback) { it.onStatus("Sending command…") }

                val noResponse =
                    (writeChar.properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0
                val dispatched = writeCharacteristic(gatt, writeChar, payload)

                // Fire-and-forget lock: for no-response writes the command is "delivered"
                // once the stack accepts it — there is no ACK to wait for.
                if (noResponse) {
                    if (dispatched) reportSuccess() else reportError("Could not send command")
                    gatt.disconnect()
                }
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {
                // Only fires for write-WITH-response. 133 here is the known false negative:
                // the lock acted and dropped the link before ACKing, so treat it as delivered.
                if (status == BluetoothGatt.GATT_SUCCESS || status == 133) {
                    reportSuccess()
                } else {
                    reportError("Write failed (status $status)")
                }
                gatt.disconnect()
            }
        })

        post(callback) { it.onStatus("Connecting…") }
    }

    // ----------------------------------------------------------------
    // Helpers
    // ----------------------------------------------------------------

    private fun findWritableCharacteristic(gatt: BluetoothGatt): BluetoothGattCharacteristic? {
        for (service in gatt.services) {
            for (ch in service.characteristics) {
                val props = ch.properties
                val writable =
                    (props and BluetoothGattCharacteristic.PROPERTY_WRITE) != 0 ||
                            (props and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0
                if (writable) return ch
            }
        }
        return null
    }

    private fun writeCharacteristic(
        gatt: BluetoothGatt,
        ch: BluetoothGattCharacteristic,
        value: ByteArray
    ): Boolean {
        // Prefer write-WITHOUT-response when supported — avoids the ACK wait that
        // produces status 133 on these locks.
        val writeType =
            if ((ch.properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE) != 0)
                BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            else
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            gatt.writeCharacteristic(ch, value, writeType) == BluetoothStatusCodes.SUCCESS
        } else {
            @Suppress("DEPRECATION")
            ch.writeType = writeType
            @Suppress("DEPRECATION")
            ch.value = value
            @Suppress("DEPRECATION")
            gatt.writeCharacteristic(ch)
        }
    }

    private inline fun post(callback: LockCallback, crossinline block: (LockCallback) -> Unit) {
        mainHandler.post { block(callback) }
    }
}
