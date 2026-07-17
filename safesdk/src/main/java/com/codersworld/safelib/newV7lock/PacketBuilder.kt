package com.example.myapplicationclean.presentation.ble

/**
 * Port of the Flutter PacketBuilder.
 *
 * Takes a MAC like "5C:53:10:4F:37:AE", reverses the byte order,
 * keeps the first 6 bytes, then appends the command byte:
 *   0x4C = LOCK, 0x55 = UNLOCK (open)
 * Final packet is 7 bytes.
 */
object PacketBuilder {

    const val CMD_LOCK = "4c"
    const val CMD_UNLOCK = "55" // "open"

    fun buildLockPacket(macAddress: String, isLock: Boolean): ByteArray {
        val cleanMac = macAddress.replace(":", "").replace("-", "")

        // Split into byte-pairs, reverse the order of the pairs, uppercase.
        // 5c53104f37ae -> AE374F10535C
        val reversedMac = cleanMac.chunked(2)
            .reversed()
            .joinToString("")
            .uppercase()

        // Take first 6 bytes (12 hex chars) of the reversed MAC.
        val macPart = reversedMac.take(12)

        val command = if (isLock) CMD_LOCK else CMD_UNLOCK
        val packetHex = macPart + command

        return hexToBytes(packetHex)
    }

    fun hexToBytes(hex: String): ByteArray {
        val clean = hex.replace("0x", "")
            .replace(" ", "")
            .replace("-", "")
        require(clean.length % 2 == 0) { "Hex string must have an even length" }
        return ByteArray(clean.length / 2) { i ->
            clean.substring(i * 2, i * 2 + 2).toInt(16).toByte()
        }
    }

    fun bytesToHex(bytes: ByteArray): String =
        bytes.joinToString(" ") { "%02x".format(it) }
}
