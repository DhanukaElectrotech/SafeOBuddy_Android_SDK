package com.dhanuka.safeobuddy.scanner.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codersworld.safelib.SafeLock;
import com.codersworld.safelib.beans.AllLocksBean;
import com.codersworld.safelib.beans.GateRecordsBean;
import com.codersworld.safelib.beans.LockRecordsBean;
import com.codersworld.safelib.listeners.OnSafeAuthListener;
import com.codersworld.safelib.utils.CommonMethods;
import com.dhanuka.safeobuddy.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends AppCompatActivity implements OnSafeAuthListener {
    SafeLock mSafeLock;
    EditText etId;
    TextView txtResult;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        etId = findViewById(R.id.etLockId);
        txtResult = findViewById(R.id.txtResult);
        recyclerView =
                findViewById(R.id.rvDevices);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSafeLock = new SafeLock(SplashActivity.this, this);
        //  mSafeLock.authUser("uffizio", "uffizio123", "1.0", "Safe SDK demo");
        //mSafeLock.authUser("prashant67", "prashant67", "1.0", "Safe SDK demo");
        //mSafeLock.authUser("prashant67", "prashant67", "1.0", "Safe SDK demo");
        mSafeLock.authUser("gun028484", "255830", "1.0", "Safe SDK demo");

    }

    //        mSafeLock.getLockRecords("9605866");
    public void onOpen(View v) {
        if (CommonMethods.isValidString(etId.getText().toString())) {
//            mSafeLock.manualLockAction(etId.getText().toString(), 1);
//            mSafeLock.manualPadLockAction(etId.getText().toString(), 2);
//            //v7
            mSafeLock.manualPadLockAction(etId.getText().toString(), 71);
        } else {
            Toast.makeText(this, "Enter device id", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClose(View v) {
        if (CommonMethods.isValidString(etId.getText().toString())) {
//            mSafeLock.manualLockAction(etId.getText().toString(), 0);
            //v7
            mSafeLock.manualLockAction(etId.getText().toString(), 70);
        } else {
            Toast.makeText(this, "Enter device id", Toast.LENGTH_SHORT).show();
        }
    }

    public void getRecords(View v) {
        if (CommonMethods.isValidString(etId.getText().toString())) {
            mSafeLock.getLockRecords(etId.getText().toString());
        } else {
            Toast.makeText(this, "Enter device id", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onSafeAuth(String errorCode, String message) {
        Log.e("onSafeAuth", errorCode + "\n" + message);
        if (errorCode.equalsIgnoreCase("106")) {
            Toast.makeText(this, "Authenticated successfully.", Toast.LENGTH_SHORT).show();
            mSafeLock.getDeviceList();
            //startActivity(new Intent(SplashActivity.this, HomeActivity.class));

            // mSafeLock.actionManualLock("","",1);
        }
    }

    @Override
    public void onSafeDevices(String errorCode, String message, ArrayList<AllLocksBean.InfoBean> mListLocks) {
        Log.e("onSafeDevices", errorCode + "\n" + message);
        if (errorCode.equalsIgnoreCase("106")) {
            if (CommonMethods.isValidArrayList(mListLocks)) {
                Log.e("mListLocks", new Gson().toJson(mListLocks));

                for (int a = 0; a < mListLocks.size(); a++) {
                    Log.e("locakname", mListLocks.get(a).getVehicleNumber());
                    if (mListLocks.get(a).getVehicleNumber().equalsIgnoreCase("FRANCHISE LOCK")) {
                        mSafeLock.openLock(System.currentTimeMillis(), mListLocks.get(a).getDeviceCode());
                    }
                }
            }
        }
    }

    @Override
    public void onSafeRecords(String errorCode, String message, ArrayList<LockRecordsBean.InfoBean> mListRecords) {
        Log.e("onSafeRecords", errorCode + "\n" + message);
        if (errorCode.equalsIgnoreCase("106")) {
            if (CommonMethods.isValidArrayList(mListRecords)) {
                Log.e("mListRecords", new Gson().toJson(mListRecords));
                txtResult.setText(new Gson().toJson(mListRecords));
            }

        } else {
            txtResult.setText(message);

        }
    }

    @Override
    public void onSafeLockAction(String code, String message, String type) {
        Toast.makeText(this, " " + message, Toast.LENGTH_SHORT).show();

        Log.e("action_lock", code + "\n" + message + "\n" + type);
    }



    // =========================
    // TOAST
    // =========================

    private void showToast(
            String message
    ) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }




    // =========================
    // BLUETOOTH PERMISSION
    // =========================

    private boolean hasBluetoothPermission() {

        if (
                Build.VERSION.SDK_INT >=
                        Build.VERSION_CODES.S
        ) {

            return ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED;

        } else {

            return true;
        }
    }

    // =========================
    // REQUEST PERMISSION
    // =========================

    private void requestPermissions() {

        List<String> permissions =
                new ArrayList<>();

        if (
                Build.VERSION.SDK_INT >=
                        Build.VERSION_CODES.S
        ) {

            permissions.add(
                    Manifest.permission.BLUETOOTH_SCAN
            );

            permissions.add(
                    Manifest.permission.BLUETOOTH_CONNECT
            );

            permissions.add(
                    Manifest.permission.ACCESS_FINE_LOCATION
            );

        } else {

            permissions.add(
                    Manifest.permission.ACCESS_FINE_LOCATION
            );
        }

        ActivityCompat.requestPermissions(
                this,
                permissions.toArray(new String[0]),
                100
        );
    }

}
