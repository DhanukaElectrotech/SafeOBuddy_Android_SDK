package com.dhanuka.safeobuddy.scanner.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codersworld.safelib.SafeLock;
import com.codersworld.safelib.beans.AllLocksBean;
import com.codersworld.safelib.beans.LockRecordsBean;
import com.codersworld.safelib.listeners.OnSafeAuthListener;
import com.codersworld.safelib.utils.CommonMethods;
import com.dhanuka.safeobuddy.R;
import com.google.gson.Gson;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements OnSafeAuthListener {
    SafeLock mSafeLock;
    EditText etId;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        etId = findViewById(R.id.etLockId);
        txtResult = findViewById(R.id.txtResult);
        mSafeLock = new SafeLock(HomeActivity.this, this);
      //  mSafeLock.authUser("uffizio", "uffizio123", "1.0", "Safe SDK demo");
        //mSafeLock.authUser("prashant67", "prashant67", "1.0", "Safe SDK demo");
        //mSafeLock.authUser("prashant67", "prashant67", "1.0", "Safe SDK demo");
        //mSafeLock.authUser("depl", "depl987", "1.0", "Safe SDK demo");
    }
//        mSafeLock.getLockRecords("9605866");
    public void onOpen(View v) {
        if (CommonMethods.isValidString(etId.getText().toString())) {
            mSafeLock.manualLockAction(etId.getText().toString(), 1);
        } else {
            Toast.makeText(this, "Enter device id", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClose(View v) {
        if (CommonMethods.isValidString(etId.getText().toString())) {
            mSafeLock.manualLockAction(etId.getText().toString(), 0);
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
            // mSafeLock.getDeviceList();
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

        }else{
            txtResult.setText(message);

        }
    }

    @Override
    public void onSafeLockAction(String code, String message, String type) {
        Toast.makeText(this, " "+message, Toast.LENGTH_SHORT).show();

        Log.e("action_lock", code + "\n" + message + "\n" + type);
    }
}
