package com.codersworld.safelib;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.codersworld.safelib.beans.LockRecordsBean;
import com.codersworld.configs.urls.common.Links;
import com.codersworld.configs.urls.tt.tt;
import com.codersworld.configs.urls.vehicletrack.membocool;
import com.codersworld.safelib.beans.DeviceDetailBean;
import com.codersworld.configs.beans.DeviceInfoBean;
import com.codersworld.safelib.beans.LockRecords;
import com.codersworld.safelib.helpers.AESHelpers;
import com.codersworld.safelib.helpers.JKHelper;
import com.codersworld.safelib.helpers.UserSessions;
import com.codersworld.safelib.listeners.OnAuthListener;
import com.codersworld.safelib.listeners.OnSafeAuthListener;
import com.codersworld.safelib.beans.AccountInfo;
import com.codersworld.safelib.beans.AllLocksBean;
import com.codersworld.safelib.beans.GateRecordsBean;
import com.codersworld.safelib.beans.LoginBean;
import com.codersworld.safelib.rest.ApiCall;
import com.codersworld.safelib.rest.ApiRequest;
import com.codersworld.safelib.rest.OnResponse;
import com.codersworld.safelib.rest.RetrofitRequest;
import com.codersworld.safelib.rest.UniverSelObjct;
import com.codersworld.safelib.rest.ttlock.SensitiveInfo;
import com.codersworld.safelib.utils.CommonMethods;
import com.codersworld.safelib.utils.PermissionModule;
import com.codersworld.safelib.utils.SFProgress;
import com.dhanukaelectrotech.safesdk.R;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.ttlock.bl.sdk.api.TTLockClient;
import com.ttlock.bl.sdk.callback.ControlLockCallback;
import com.ttlock.bl.sdk.callback.GetBatteryLevelCallback;
import com.ttlock.bl.sdk.constant.ControlAction;
import com.ttlock.bl.sdk.entity.ControlLockResult;
import com.ttlock.bl.sdk.entity.LockError;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SafeLock implements OnResponse<UniverSelObjct>, OnAuthListener {
    static Activity mActivity;
    static long iniDateTime;
    static String deviceCode;
    static String strLat = "0.0";
    static String strLong = "0.0";
    static ApiCall mApiCall = null;
    OnSafeAuthListener mAuthListener = null;
    SQLiteDatabase database = null;
    ArrayList<SensitiveInfo> mInfo = new ArrayList<>();
    ArrayList<AllLocksBean.InfoBean> mDevices = new ArrayList<>();



    public SafeLock(Activity activity, OnSafeAuthListener listener) {
        this.mActivity = activity;
        this.mAuthListener = listener;
        getInstance(mActivity);
        initApiCall();

    }

    public static void initApiCall() {
        if (mApiCall == null) {
            mApiCall = new ApiCall(mActivity);
        }
    }

    private static void getInstance(Activity activity) {
        mActivity = activity;
    }

    public void checkPermission() {
        //statusCheck();
        try {
            final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
            if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            bAdapter.enable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void statusCheck() {
        final LocationManager manager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
    }

    public static void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton(mActivity.getResources().getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        mActivity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    String strUsername = "";

    public void authUser(String strUsername, String strPassword, String strAppVersion, String strAppName) {
        this.strUsername = strUsername;
        initApiCall();
        new UserSessions().saveAccessToken(mActivity, "");

        AESHelpers mAESHelper = new AESHelpers();
        String encParam = mAESHelper.safeEncryption(mActivity, membocool.getLoginParams(strUsername, strPassword, strAppVersion, strLat, strLong, CommonMethods.getIMEI(
                mActivity)));
        if (CommonMethods.isNetworkAvailable(mActivity)) {
            HashMap mMap = new HashMap<String, String>();
            mMap.put("val", encParam);
            mApiCall.userLogin(this, encParam);
        } else {
            onAuthResult("100", mActivity.getString(R.string.error_internet));
        }
    }

    ArrayList<AllLocksBean.InfoBean> mListLocks = new ArrayList();

    public void getDeviceList() {
        initApiCall();
        mInfo = new ArrayList<>();
        mListLocks = new ArrayList<>();
        //String params = Links.SB_GET_ALL_V3_LOCKS + "&"+ com.codersworld.configs.urls.common.Constants.P_CAT+"=1&"+ com.codersworld.configs.urls.common.Constants.P_CID+"=" + UserSessions.getUserInfo(mActivity).getUid();
        AESHelpers mAESHelper = new AESHelpers();
        String encParam = mAESHelper.safeEncryption(mActivity, membocool.getDevicesparams(UserSessions.getUserInfo(mActivity).getUid()));
        //  mApiCall.getAllV3Locks(this, "1",UserSessions.getUserInfo(mActivity).getUid());

        mApiCall.getAllV3Locks(this, encParam);
    }

    public void updateLockStatus(SensitiveInfo mMap, String msg) {
        initApiCall();
        String strDeviceId = (CommonMethods.isValidString(mMap.getLOCK_ID())) ? mMap.getLOCK_ID() : (CommonMethods.isValidString(mMap.getLOCK_CODE()) ? mMap.getLOCK_CODE() : "");
        mInfo = new ArrayList<>();
        mListLocks = new ArrayList<>();
        mApiCall.saveLockStatus(this, UserSessions.getUserInfo(mActivity).getUid(),
                strDeviceId, UserSessions.getUserInfo(mActivity).getUsername(), msg, "6", "", "TTLock");
    }

    public void getLockRecords(String device_id) {
        actionType = 3;
        getDeviceInfo(device_id);
    }

    private void getLockRecordsData(DeviceInfoBean.InfoBean mMap) {
        initApiCall();
        mListLocks = new ArrayList<>();
        String startDate = CommonMethods.getCalculatedDate("MM/dd/yyyy", -7);
        String endDate = CommonMethods.getCurrentFormatedDate("MM/dd/yyyy");
        mApiCall.getGateRecordsData(this, startDate, endDate, mMap.getVehicleNumber(), mMap.getLockId(), UserSessions.getUserInfo(mActivity).getUid(), "18", "", "");
    }

    public void getDeviceRecords(String startDate, String endDate, String device_id, String device_name) {
        initApiCall();
        mListLocks = new ArrayList<>();
        AESHelpers mAESHelper = new AESHelpers();
        String strPrms = membocool.getRecordsParams(UserSessions.getUserInfo(mActivity).getUid(), device_id, device_name, endDate, startDate);
        String encParam = mAESHelper.safeEncryption(mActivity, strPrms);
        mApiCall.getGateRecords(this, strPrms);
    }

    @Override
    public void onSuccess(UniverSelObjct response) {
        try {
            switch (response.getMethodname()) {
                case Links.SB_API_GET_DEVICE_INFO:
                    try {
                        SFProgress.hideProgressDialog(mActivity);
                    } catch (Exception e) {
                    }
                    try {
                        DeviceInfoBean mDeviceInfoBean = (DeviceInfoBean) response.getResponse();
                        if (mDeviceInfoBean.getSuccess() == 1) {
                            mInfo = new ArrayList<>();
                            for (int a = 0; a < mDeviceInfoBean.getReturnds().size(); a++) {
                                if (CommonMethods.isValidString(mDeviceInfoBean.getReturnds().get(a).getLockdata()) && CommonMethods.isValidString(mDeviceInfoBean.getReturnds().get(a).getMacId())) {
                                    SensitiveInfo mbn = new SensitiveInfo();
                                    mbn.setLockData(mDeviceInfoBean.getReturnds().get(a).getLockdata());
                                    mbn.setMACID(mDeviceInfoBean.getReturnds().get(a).getMacId());
                                    mbn.setLOCK_CODE(mDeviceInfoBean.getReturnds().get(a).getLockCode());
                                    mbn.setLOCK_ID(mDeviceInfoBean.getReturnds().get(a).getLockId());
                                    mbn.setBtlockid(mDeviceInfoBean.getReturnds().get(a).getLockId());
                                    mbn.setBtlockidval(mDeviceInfoBean.getReturnds().get(a).getLockId());
                                    mbn.setGPSDeviceCode(mDeviceInfoBean.getReturnds().get(a).getLockId());
                                    mbn.setGPSDeviceId(mDeviceInfoBean.getReturnds().get(a).getLockId());
                                    mInfo.add(mbn);
                                }
                            }
                            UserSessions.saveMap(mActivity, (mInfo.size() > 0) ? mInfo : new ArrayList<>());
                            if (actionType == 1) {
                                openLock(System.currentTimeMillis(), deviceCode);
                            } else if (actionType == 0) {
                                closeLock(deviceCode);
                            } else if (actionType == 3) {
                                getLockRecordsData(mDeviceInfoBean.getReturnds().get(0));
                            }
                        } else {
                            if (actionType == 0) {
                                onLockAction("101", mActivity.getString(R.string.something_wrong), "close lock");
                            } else {
                                onLockAction("102", mActivity.getString(R.string.something_wrong), "open lock");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (actionType == 0) {
                            onLockAction("101", mActivity.getString(R.string.something_wrong), "close lock");
                        } else {
                            onLockAction("102", mActivity.getString(R.string.something_wrong), "open lock");
                        }
                    }
                    break;
                case Links.SB_LOGIN_API:
                    try {
                        LoginBean mLoginBean = (LoginBean) response.getResponse();
                        if (mLoginBean.getSuccess() == 1) {
                            UserSessions.saveAccessToken(mActivity, (CommonMethods.isValidString(mLoginBean.getLoginvalidation().get(0).getToken())) ? mLoginBean.getLoginvalidation().get(0).getToken() : "");
                            if (CommonMethods.isValidString(mLoginBean.getLoginvalidation().get(0).getUid()) && !mLoginBean.getLoginvalidation().get(0).getUid().equalsIgnoreCase("0")) {
                                mLoginBean.getLoginvalidation().get(0).setUsername(strUsername);
                                UserSessions.saveUserInfo(mActivity, mLoginBean.getLoginvalidation().get(0));
                                new JKHelper().makeTTAuthentication(mActivity, 4, this);
                                //moveToNext(mLoginBean.getLoginvalidation().get(0));
                            } else if (CommonMethods.isValidString(mLoginBean.getLoginvalidation().get(0).getReturnmessage())) {
                                onAuthResult("105", mLoginBean.getLoginvalidation().get(0).getReturnmessage());
                            } else {
                                onAuthResult("104", mActivity.getResources().getString(R.string.error_no_role));
                            }
                        } else if (CommonMethods.isValidString(mLoginBean.getMessage())) {
                            onAuthResult("103", mLoginBean.getMessage());
                        } else if (CommonMethods.isValidArrayList(mLoginBean.getLoginvalidation()) && CommonMethods.isValidString(mLoginBean.getLoginvalidation().get(0).getReturnmessage())) {
                            onAuthResult("102", mLoginBean.getLoginvalidation().get(0).getReturnmessage());
                        } else {
                            onAuthResult("101", mActivity.getString(R.string.something_wrong));
                        }
                    } catch (Exception e) {
                        onAuthResult("100", mActivity.getString(R.string.something_wrong));
                    }
                    break;
                case Links.SB_GET_ALL_V3_LOCKS:
                    try {
                        AllLocksBean mAllLocksBean = (AllLocksBean) response.getResponse();
                        if (mAllLocksBean != null) {
                            Log.e("mAllLocksBean1", new Gson().toJson(mAllLocksBean));
                            if (mAllLocksBean.getSuccess() == 1) {
                                if (CommonMethods.isValidArrayList(mAllLocksBean.getNewusercreation())) {
                                    mInfo = new ArrayList<>();
                                    for (int a = 0; a < mAllLocksBean.getNewusercreation().size(); a++) {
                                        SensitiveInfo mbn = new SensitiveInfo();

                                        mbn.setLockData(mAllLocksBean.getNewusercreation().get(a).getLockData());
                                        mbn.setMACID(mAllLocksBean.getNewusercreation().get(a).getMACID());
                                        mbn.setLOCK_CODE(mAllLocksBean.getNewusercreation().get(a).getDeviceCode());
                                        mbn.setLOCK_ID(mAllLocksBean.getNewusercreation().get(a).getDeviceID());
                                        mbn.setBtlockid(mAllLocksBean.getNewusercreation().get(a).getBtlockid());
                                        mbn.setBtlockidval(mAllLocksBean.getNewusercreation().get(a).getBtlockidval());
                                        mbn.setGPSDeviceCode("");
                                        mbn.setGPSDeviceId("");
                                        mInfo.add(mbn);
                                        mAllLocksBean.getNewusercreation().get(a).setMACID("");
                                        mAllLocksBean.getNewusercreation().get(a).setLockData("");
                                        mAllLocksBean.getNewusercreation().get(a).setDeviceCode(mAllLocksBean.getNewusercreation().get(a).getBtlockidval());
                                        mAllLocksBean.getNewusercreation().get(a).setBtlockid("");
                                        mAllLocksBean.getNewusercreation().get(a).setBtlockidval("");
                                        mAllLocksBean.getNewusercreation().get(a).setOwner_id(UserSessions.getUserInfo(mActivity).getUid());
                                    }
                                    UserSessions.saveMap(mActivity, (mInfo.size() > 0) ? mInfo : new ArrayList<>());
                                    onSafeDevices("106", "success", mAllLocksBean.getNewusercreation());
                                } else {
                                    //no data found
                                    onSafeDevices("103", mActivity.getString(R.string.something_wrong), null);
                                }
                            } else {//message from api
                                onSafeDevices("102", mActivity.getString(R.string.something_wrong), null);
                            }
                        } else {
                            onSafeDevices("101", mActivity.getString(R.string.something_wrong), null);
                        }
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                        onSafeDevices("100", mActivity.getString(R.string.something_wrong), null);
                    }
                    break;
                case Links.SB_API_GET_GATE_RECORDS_DATA:
                    try {
                        JSONObject jsonObjct = new JSONObject(response.getResponse().toString());
                        if (jsonObjct.getInt("success") == 1) {
                            LockRecordsBean gateOpenBean = new Gson().fromJson(response.getResponse().toString(), LockRecordsBean.class);
                            if (gateOpenBean != null && CommonMethods.isValidArrayList(gateOpenBean.getReturnds())) {
                                onSafeRecords("106", "Success", gateOpenBean.getReturnds());
                            } else {
                                onSafeRecords("100", mActivity.getString(R.string.something_wrong), null);
                            }
                        } else {
                            onSafeRecords("100", "No records found for this device.", null);
                        }
                    } catch (Exception ex1) {
                        onSafeRecords("100", mActivity.getString(R.string.something_wrong), null);
                        ex1.printStackTrace();
                    }
                    break;
                case Links.SB_API_TTLOCK_GET_LOCKDATA:
                    try {
                        DeviceDetailBean mDeviceDetailBean = (DeviceDetailBean) response.getResponse();
                        if (mDeviceDetailBean != null) {
                            ArrayList<SensitiveInfo> mMap2 = new ArrayList<>();
                            mMap2 = UserSessions.getMap(mActivity);
                            if (CommonMethods.isValidArrayList(mMap2)) {
                                for (int a = 0; a < mMap2.size(); a++) {
                                    if (mMap2.get(a).getBtlockidval().equalsIgnoreCase(deviceCode)) {
                                        SensitiveInfo mMap3 = mMap2.get(a);
                                        mMap3.setLockData(mDeviceDetailBean.getLockData());
                                        mMap3.setMACID(mDeviceDetailBean.getLockMac());
                                        mMap3.setLOCK_CODE(mMap3.getLOCK_CODE());
                                        mMap3.setLOCK_ID(mMap3.getLOCK_ID());
                                        mMap3.setBtlockid(mMap3.getBtlockid());
                                        mMap3.setBtlockidval(mMap3.getBtlockidval());
                                        mMap3.setGPSDeviceCode(mMap3.getGPSDeviceCode());
                                        mMap3.setGPSDeviceId(mMap3.getGPSDeviceId());
                                        mMap2.set(a, mMap3);
                                        UserSessions.saveMap(mActivity, mMap2);
                                        if (actionType == 1) {
                                            openLock(System.currentTimeMillis(), deviceCode);
                                        } else if (actionType == 0) {
                                            closeLock(deviceCode);
                                        }
                                        new JKHelper().updateLockData(mActivity, mDeviceDetailBean.getLockData(), mDeviceDetailBean.getLockMac(), mDeviceDetailBean.getLockId());
                                    }
                                }
                            }
                        }

                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                        if (actionType == 0) {
                            onLockAction("101", mActivity.getString(R.string.something_wrong), "close lock");
                        } else {
                            onLockAction("102", mActivity.getString(R.string.something_wrong), "open lock");
                        }

                    }
                    break;
            }
        } catch (Exception e) {
            onAuthResult("100", mActivity.getString(R.string.error_internet));
        }
    }


    private void onSafeDevices(String code, String msg, ArrayList<AllLocksBean.InfoBean> mListLocks) {
        if (mAuthListener != null) {
            mAuthListener.onSafeDevices(code, msg, mListLocks);
        }
    }

    private void onSafeRecords(String code, String msg, ArrayList<LockRecordsBean.InfoBean> mListRecords) {
        if (mAuthListener != null) {
            mAuthListener.onSafeRecords(code, msg, mListRecords);
        }
    }

    private void onAuthResult(String code, String msg) {
        if (mAuthListener != null) {
            mAuthListener.onSafeAuth(code, msg);
        }
    }

    private void onLockAction(String code, String msg, String type) {
        if (mAuthListener != null) {
            mAuthListener.onSafeLockAction(code, msg, type);
        }
    }


    @Override
    public void onError(String type, String error) {
        try {
            switch (type) {
                case Links.SB_API_GET_DEVICE_INFO:
                    try {
                        SFProgress.hideProgressDialog(mActivity);
                    } catch (Exception e) {
                    }
                    actionLock();
                    break;
                case Links.SB_API_TTLOCK_USER_KEYLIST:
                    /*if (mAuthListener != null) {
                        onAuthResult("100", error);
                    }*/
                    break;
                case Links.SB_LOGIN_API:
                    if (mAuthListener != null) {
                        onAuthResult("100", error);
                    }
                    break;
                case Links.SB_GET_ALL_V3_LOCKS:
                    if (mAuthListener != null) {
                        onSafeDevices("100", error, null);
                    }
                    break;
                case Links.SB_API_GET_GATE_RECORDS_DATA:
                    if (mAuthListener != null) {
                        onSafeRecords("100", error, null);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onAuth(AccountInfo mInfo) {
        onAuthResult("106", mActivity.getString(R.string.login_success));
    }


    public void manualLockAction(String lockId, int type) {//1 for lock open 0 for lock close
        deviceCode = lockId;
        actionType = type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
           /* try {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", mActivity.getPackageName(), null);
                intent.setData(uri);
                mActivity.startActivityForResult(intent,STORAGE_PERMISSION_CODE);
            }catch (Exception e){
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                mActivity.startActivityForResult(intent,STORAGE_PERMISSION_CODE);
            }*/
        } else {
            //Below android 11
            ActivityCompat.requestPermissions(
                    mActivity,
                    new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    STORAGE_PERMISSION_CODE
            );
        }
        getDeviceInfo(lockId);
    }

    int actionType = -1;

    public void closeLock(String deviceCode/*String lockData, String macID*/) {
        checkPermission();
        final LocationManager manager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            actionType = 0;
            this.deviceCode = deviceCode;
            PermissionModule m = new PermissionModule(mActivity);
            if (m.checkBTPermissions()) {
                actionLock();
            } else {
                m.requestForPermissions();
            }
        }
    }


    private static final int STORAGE_PERMISSION_CODE = 23;

    protected void actionLock() {
        iniDateTime = System.currentTimeMillis() + 1800000;
        long unlockdate = System.currentTimeMillis();
        SensitiveInfo mMap = validateDevice(deviceCode);
        if (mMap == null) {
            onLockAction("100", "Invalid device info", (actionType == 0) ? "close lock" : "open lock");
        } else if (iniDateTime < unlockdate) {
            onLockAction("100", "Please Refresh Page", (actionType == 0) ? "close lock" : "open lock");
            return;
        }

        String lockData = (CommonMethods.isValidString(mMap.getLockData())) ? mMap.getLockData() : "";
        String macID = (CommonMethods.isValidString(mMap.getMACID())) ? mMap.getMACID() : "";
        String btlockid = (CommonMethods.isValidString(mMap.getBtlockid())) ? mMap.getBtlockid() : "";
        if (!CommonMethods.isValidString(lockData) || !CommonMethods.isValidString(macID)) {
            getLockData(btlockid);
        } else {
            SFProgress.showProgressDialog(mActivity, true);
            TTLockClient.getDefault().controlLock((actionType == 0) ? ControlAction.LOCK : ControlAction.UNLOCK, lockData, macID, new ControlLockCallback() {
                @Override
                public void onControlLockSuccess(ControlLockResult controlLockResult) {
                    SFProgress.hideProgressDialog(mActivity);
                    try {
                        if (actionType == 0) {
                            onLockAction("106", "Device is locked successfully.", "close lock");
                        } else {
                            onLockAction("106", "Lock opened successfully.", "open lock");
                        }
                        updateLockStatus(mMap, (actionType == 0) ? "Closed via APP" : "Opened via APP");
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (actionType == 0) {
                            onLockAction("101", mActivity.getString(R.string.something_wrong), "close lock");
                        } else {
                            onLockAction("102", mActivity.getString(R.string.something_wrong), "open lock");
                        }
                        updateLockStatus(mMap, (actionType == 0) ? "Failed to close via APP" : "Failed to open via APP");
                    }
                    //unlockRecordUpload("Locked via App");
                }

                @Override
                public void onFail(LockError error) {
                    SFProgress.hideProgressDialog(mActivity);
                    try {
                        Log.e("Lockerror", error.getErrorMsg() + "\n" + error.getDescription());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (actionType == 0) {
                        onLockAction("100", "Failed to lock the device.", "close lock");
                    } else {
                        onLockAction("102", "failed to open the lock.", "open lock");
                    }
                    updateLockStatus(mMap, (actionType == 0) ? "Failed to close via APP" : "Failed to open via APP");
                }
            });
        }
    }

    int actionCounter = 0;

    public void actionManualLock(String lockData, String macID, int mActionType, String mDeviceCode) {
        actionType = mActionType;
        deviceCode = mDeviceCode;
        checkPermission();
        final LocationManager manager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            PermissionModule m = new PermissionModule(mActivity);
            if (m.checkBTPermissions()) {
                iniDateTime = System.currentTimeMillis() + 1800000;
                long unlockdate = System.currentTimeMillis();
                if (iniDateTime < unlockdate) {
                    onLockAction("100", "Please Refresh Page", (mActionType == 0) ? "close lock" : "open lock");
                    return;
                }

                if (CommonMethods.isValidString(lockData) && CommonMethods.isValidString(macID)) {
                    SFProgress.showProgressDialog(mActivity, true);
                    TTLockClient.getDefault().controlLock((mActionType == 0) ? ControlAction.LOCK : ControlAction.UNLOCK, lockData, macID, new ControlLockCallback() {
                        @Override
                        public void onControlLockSuccess(ControlLockResult controlLockResult) {
                            SFProgress.hideProgressDialog(mActivity);
                            try {
                                if (mActionType == 0) {
                                    onLockAction("106", "Device is locked successfully.", "close lock");
                                } else {
                                    onLockAction("106", "Lock opened successfully.", "open lock");
                                }
                                //updateLockStatus(mMap, (mActionType == 0) ? "Closed via APP" : "Opened via APP");
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (mActionType == 0) {
                                    onLockAction("101", mActivity.getString(R.string.something_wrong), "close lock");
                                } else {
                                    onLockAction("102", mActivity.getString(R.string.something_wrong), "open lock");
                                }
                                //updateLockStatus(mMap, (mActionType == 0) ? "Failed to close via APP" : "Failed to open via APP");
                            }
                            //unlockRecordUpload("Locked via App");
                        }

                        @Override
                        public void onFail(LockError error) {
                            SFProgress.hideProgressDialog(mActivity);
                            if (actionCounter == 0) {
                                actionCounter++;
                                getDeviceInfo(mDeviceCode);
                            } else {
                                try {
                                    Log.e("Lockerror", error.getErrorMsg() + "\n" + error.getDescription());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (mActionType == 0) {
                                    onLockAction("100", "Failed to lock the device.", "close lock");
                                } else {
                                    onLockAction("102", "failed to open the lock.", "open lock");
                                }
                            }
                            // updateLockStatus(mMap, (mActionType == 0) ? "Failed to close via APP" : "Failed to open via APP");
                        }
                    });
                }

            } else {
                m.requestForPermissions();
            }
        }
    }

    public void openLock(long unlockdate, String deviceCode) {
        checkPermission();
        final LocationManager manager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            this.deviceCode = deviceCode;
            actionType = 1;
            PermissionModule m = new PermissionModule(mActivity);
            if (m.checkBTPermissions()) {
                actionLock();
            } else {
                m.requestForPermissions();
            }
        }
    }

    private void getLockData(String lock_code) {
        initApiCall();
        LoginBean.InfoBean mBeanUser = UserSessions.getUserInfo(mActivity);
        AccountInfo mBeanAccountInfo = UserSessions.getTTAccountInfo(mActivity);
        String ClientID = Links.TT_CLIENT_ID;
        String client_secret = Links.TT_CLIENT_SECRET;
        String accessToken = "";
        if (mBeanUser != null) {
            client_secret = (CommonMethods.isValidString(mBeanUser.getClientsecret())) ? mBeanUser.getClientsecret() : Links.TT_CLIENT_SECRET;
            ClientID = (CommonMethods.isValidString(mBeanUser.getClientid())) ? mBeanUser.getClientid() : Links.TT_CLIENT_ID;
        }
        if (mBeanAccountInfo != null) {
            accessToken = (CommonMethods.isValidString(mBeanAccountInfo.getAccess_token())) ? mBeanAccountInfo.getAccess_token() : "";
        }


        HashMap param = new HashMap<String, String>();
        param.put("client_secret", client_secret);
        param.put("clientId", ClientID);
        param.put("accessToken", accessToken);
        param.put("lockId", lock_code);
        param.put("date", System.currentTimeMillis() + "");
        //mApiCall.getUserKeyList(this, param);
        mApiCall.getLockData(this, ClientID, accessToken, lock_code, System.currentTimeMillis() + "");
    }

    private void authenticateTTLock() {
        new JKHelper().makeTTAuthentication(mActivity, 4, (OnAuthListener) mActivity);
    }

    private SensitiveInfo validateDevice(String deviceCode) {
        ArrayList<SensitiveInfo> mMap2 = new ArrayList<>();
        mMap2 = UserSessions.getMap(mActivity);
        SensitiveInfo mBn = null;
        for (int a = 0; a < mMap2.size(); a++) {
            if (mMap2.get(a).getBtlockidval().equalsIgnoreCase(deviceCode)) {
                mBn = mMap2.get(a);
            }
        }
        return mBn;
    }

    public void getDeviceInfo(String... strParams) {
        initApiCall();
        try {
            SFProgress.showProgressDialog(mActivity, true);
            LoginBean.InfoBean mBeanUser = UserSessions.getUserInfo(mActivity);
            mApiCall.getDeviceInfo(this, strParams[0], mBeanUser.getUid(), UserSessions.getAccessToken(mActivity));
        } catch (Exception e) {
            e.printStackTrace();
            if (actionType == 0) {
                onLockAction("100", "Failed to lock the device.", "close lock");
            } else {
                onLockAction("102", "failed to open the lock.", "open lock");
            }
        }
    }


}
