package com.codersworld.safelib.utils;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;

import java.util.ArrayList;

public class PermissionModule {
    ArrayList<String> permissionsNeeded = new ArrayList<>();

    private final Context mContext;

    public PermissionModule(Context context) {
        mContext = context;
    }

    public Boolean checkBTPermissions() {
        permissionsNeeded = new ArrayList<>();

        // Check for Bluetooth permissions based on the SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // For Android 12 and above
            try {
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    permissionsNeeded.add(Manifest.permission.BLUETOOTH_CONNECT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH_SCAN);
            }

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH_ADVERTISE);
            }
        } else {
            // For Android 11 (API level 30) and below
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH);
            }

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH_ADMIN);
            }

            // Optional: BLUETOOTH_PRIVILEGED is only needed for privileged apps
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_PRIVILEGED) != PackageManager.PERMISSION_GRANTED) {
                //permissionsNeeded.add(Manifest.permission.BLUETOOTH_PRIVILEGED);
                String arr[] = new String[]{Manifest.permission.BLUETOOTH_PRIVILEGED};
                requestPermission(arr);

            }
        }

        // Log permissions that are still needed
        Log.e("permissionsNeeded", new Gson().toJson(permissionsNeeded));

        // If any permissions are needed, request them
        if (permissionsNeeded.size() > 0) {
            requestForPermissions(); // Implement this method to request the necessary permissions
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkBTPermissionsOld() {
        permissionsNeeded = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.BLUETOOTH);
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.BLUETOOTH_ADMIN);
        }
        try {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH_CONNECT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.BLUETOOTH_SCAN);
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.BLUETOOTH_ADVERTISE);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_PRIVILEGED) != PackageManager.PERMISSION_GRANTED) {
                permissionsNeeded.add(Manifest.permission.BLUETOOTH_PRIVILEGED);
            }
        }
        Log.e("permissionsNeeded", new Gson().toJson(permissionsNeeded));
        if (permissionsNeeded.size() > 0) {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
            requestForPermissions();
            return false;
        } else {
            return true;
        }
    }

    LocationManager locationManager;
    boolean GpsStatus;

    public boolean CheckGpsStatus(Context ctx) {
        locationManager = (LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return GpsStatus;
    }

    public void checkTTLockPermissions() {
        ArrayList<String> permissionsNeeded = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.BLUETOOTH);
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionsNeeded.size() > 0) {
            String arr[] = new String[permissionsNeeded.size()];
            for (int a = 0; a < permissionsNeeded.size(); a++) {
                arr[a] = permissionsNeeded.get(a);
            }

            requestPermission(arr);
        }


    }

    public void checkLocationPermissions() {
        ArrayList<String> permissionsNeeded = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionsNeeded.size() > 0) {
            String arr[] = new String[permissionsNeeded.size()];
            for (int a = 0; a < permissionsNeeded.size(); a++) {
                arr[a] = permissionsNeeded.get(a);
            }

            requestPermission(arr);
        }


    }


    public void requestForPermissions() {
        if (permissionsNeeded.size() > 0) {
            String arr[] = new String[permissionsNeeded.size()];
            for (int a = 0; a < permissionsNeeded.size(); a++) {
                arr[a] = permissionsNeeded.get(a);
            }
            requestPermission(arr);
        }
    }

    private void requestPermission(String[] permissions) {
        ActivityCompat.requestPermissions((AppCompatActivity) mContext, permissions, 125);
    }

}