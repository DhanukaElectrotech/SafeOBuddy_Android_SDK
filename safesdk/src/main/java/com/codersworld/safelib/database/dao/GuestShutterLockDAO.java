package com.codersworld.safelib.database.dao;

/**
 * Created by Jatin Sharma on 13-11-2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.codersworld.safelib.beans.GuestShutterLockModel;

import java.util.ArrayList;

public class GuestShutterLockDAO {
    private static final String TABLE_Guest_Shutter_Lock = "Guest_Shutter_LOck";

    // Contacts Table Columns names
    private static final String COLUMN_KEY_ID = "_id";
    private static final String COLUMN_SUCCESS = "Success";
    private static final String COLUMN_CustomerName = "CustomerName";
    private static final String COLUMN_PhoneNo = "PhoneNo";
    private static final String COLUMN_Address = "Address";
    private static final String COLUMN_DeviceType = "DeviceType";
    private static final String COLUMN_DeviceCode = "DeviceCode";
    private static final String COLUMN_DeviceID = "DeviceID";
    private static final String COLUMN_VehicleNumber = "VehicleNumber";
    private static final String COLUMN_DevicePhoneNumber = "DevicePhoneNumber";
    private static final String COLUMN_DeviceSIMNumber = "DeviceSIMNumber";
    private static final String COLUMN_fileName = "fileName";
    private static final String COLUMN_filepath = "filepath";
    private static final String COLUMN_LockData = "LockData";
    private static final String COLUMN_MACID = "MACID";
    private static final String COLUMN_LockCatgeory = "LockCatgeory";
    private static final String COLUMN_Access = "Access";
    private static final String COLUMN_Remoteunlock = "Remoteunlock";
    private static final String COLUMN_AutoLock = "AutoLock";
    private static final String COLUMN_Roomstatus = "Roomstatus";
    private static final String COLUMN_GuestName = "GuestName";
    private static final String COLUMN_Checkindate = "Checkindate";
    private static final String COLUMN_checkoutdate = "checkoutdate";
    private static final String COLUMN_guestPhoneNo = "guestPhoneNo";
    private static final String COLUMN_guestEmailId = "guestEmailId";
    private static final String COLUMN_GuestMasterId = "GuestMasterId";
    private static final String COLUMN_BatteryPerc = "BatteryPerc";
    private static final String COLUMN_Hasgateway = "Hasgateway";
    private static final String COLUMN_LockTTAccess = "LockTTAccess";
    private static final String COLUMN_Isflagui = "isflagui";
    private static final String COLUMN_ItemName = "ItemName";
    private static final String COLUMN_CompanyName = "CompanyName";
    private static final String COLUMN_UnitId = "UnitId";
    private static final String COLUMN_OnlineStatus = "OnlineStatus";
    private static final String COLUMN_Enddates = "Enddates";
    private static final String COLUMN_KeyId = "KeyId";
    private static final String COLUMN_GateStatus = "GateStatus";
    private static final String COLUMN_LockID = "LockID";
    private static final String COLUMN_LockCode = "LockCode";
    private static final String COLUMN_devicenumber = "devicenumber";
    private static final String COLUMN_MainLockID = "MainLockID";
    private static final String COLUMN_MainLockNumber = "MainLockNumber";
    private static final String COLUMN_LockType = "LockType";
    private static final String COLUMN_autotimer = "autotimer";
    private static final String COLUMN_btlockid = "btlockid";
    private static final String COLUMN_MainLocktype = "MainLocktype";

    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public GuestShutterLockDAO(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }

    public static String getCreateTableUpload() {
        getDropTableUpload();
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Guest_Shutter_Lock
                + "("
                + COLUMN_KEY_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_SUCCESS + " TEXT ,"
                + COLUMN_CustomerName + " TEXT ,"
                + COLUMN_PhoneNo + " TEXT ,"
                + COLUMN_Address + " TEXT ,"
                + COLUMN_DeviceType + " TEXT ,"
                + COLUMN_DeviceCode + " TEXT ,"
                + COLUMN_DeviceID + " TEXT ,"
                + COLUMN_VehicleNumber + " TEXT ,"
                + COLUMN_DevicePhoneNumber + " TEXT ,"
                + COLUMN_DeviceSIMNumber + " TEXT ,"
                + COLUMN_fileName + " TEXT ,"
                + COLUMN_filepath + " TEXT ,"
                + COLUMN_LockData + " TEXT ,"
                + COLUMN_MACID + " TEXT ,"
                + COLUMN_LockCatgeory + " TEXT ,"
                + COLUMN_Access + " TEXT ,"
                + COLUMN_Remoteunlock + " TEXT,"
                + COLUMN_AutoLock + " TEXT ,"
                + COLUMN_Roomstatus + " TEXT ,"
                + COLUMN_GuestName + " TEXT ,"
                + COLUMN_Checkindate + " TEXT ,"
                + COLUMN_checkoutdate + " TEXT ,"
                + COLUMN_guestPhoneNo + " TEXT ,"
                + COLUMN_guestEmailId + " TEXT ,"
                + COLUMN_GuestMasterId + " TEXT ,"
                + COLUMN_BatteryPerc + " TEXT ,"
                + COLUMN_Hasgateway + " TEXT ,"
                + COLUMN_LockTTAccess + " TEXT ,"
                + COLUMN_Isflagui + " TEXT ,"
                + COLUMN_ItemName + " TEXT ,"
                + COLUMN_CompanyName + " TEXT ,"
                + COLUMN_UnitId + " TEXT ,"
                + COLUMN_OnlineStatus + " TEXT ,"
                + COLUMN_Enddates + " TEXT ,"
                + COLUMN_GateStatus + " TEXT ,"
                + COLUMN_LockID + " TEXT ,"
                + COLUMN_LockCode + " TEXT ,"
                + COLUMN_devicenumber + " TEXT ,"
                + COLUMN_MainLockID + " TEXT ,"
                + COLUMN_MainLockNumber + " TEXT ,"
                + COLUMN_LockType + " TEXT ,"
                + COLUMN_autotimer + " TEXT ,"
                + COLUMN_btlockid + " TEXT ,"
                + COLUMN_MainLocktype + " TEXT ,"
                + COLUMN_KeyId + " TEXT)";
         return CREATE_TABLE;
    }

    public static String getDropTableUpload() {
        return "DROP TABLE IF EXISTS " + TABLE_Guest_Shutter_Lock;
    }

    public void deleteAll() {

        String delete_all = " DELETE "
                + " FROM "
                + TABLE_Guest_Shutter_Lock;
        mDatabase.execSQL(delete_all);
    }

    public void insert(ArrayList<GuestShutterLockModel> arrayList) {

        for (GuestShutterLockModel singleInput : arrayList) {


            String[] bindArgs = {
                    String.valueOf(singleInput.getSuccess()),
                    String.valueOf(singleInput.getCustomerName()),
                    String.valueOf(singleInput.getPhoneNo()),
                    String.valueOf(singleInput.getAddress()),
                    String.valueOf(singleInput.getDeviceType()),
                    String.valueOf(singleInput.getDeviceCode()),
                    String.valueOf(singleInput.getDeviceID()),
                    String.valueOf(singleInput.getVehicleNumber()),
                    String.valueOf(singleInput.getDevicePhoneNumber()),
                    String.valueOf(singleInput.getDeviceSIMNumber()),
                    String.valueOf(singleInput.getFileName()),
                    String.valueOf(singleInput.getFilepath()),
                    String.valueOf(singleInput.getLockData()),
                    String.valueOf(singleInput.getMACID()),
                    String.valueOf(singleInput.getLockCatgeory()),
                    String.valueOf(singleInput.getAccess()),
                    String.valueOf(singleInput.getRemoteunlock()),
                    String.valueOf(singleInput.getAutoLock()),
                    String.valueOf(singleInput.getRoomStatus()),
                    String.valueOf(singleInput.getGuestName()),
                    String.valueOf(singleInput.getCheckindate()),
                    String.valueOf(singleInput.getCheckoutdate()),
                    String.valueOf(singleInput.getGuestPhoneNo()),
                    String.valueOf(singleInput.getGuestEmailId()),
                    String.valueOf(singleInput.getGuestMasterId()),
                    String.valueOf(singleInput.getBatteryPerc()),
                    String.valueOf(singleInput.getHasgateway()),
                    String.valueOf(singleInput.getLockTTAccess()),
                    String.valueOf(singleInput.getIsflagui()),
                    String.valueOf(singleInput.getItemName()),
                    String.valueOf(singleInput.getCompanyName()),
                    String.valueOf(singleInput.getUnitId()),
                    String.valueOf(singleInput.getOnlineStatus()),
                    String.valueOf(singleInput.getEnddates()),
                    String.valueOf(singleInput.getGateStatus()),
                    String.valueOf(singleInput.getLockID()),
                    String.valueOf(singleInput.getLockCode()),
                    String.valueOf(singleInput.getDevicenumber()),
                    String.valueOf(singleInput.getMainLockID()),
                    String.valueOf(singleInput.getMainLockNumber()),
                    String.valueOf(singleInput.getLockType()),
                    String.valueOf(singleInput.getAutotimer()),
                    String.valueOf(singleInput.getBtlockid()),
                    String.valueOf(singleInput.getMainLocktype()),
                    String.valueOf(singleInput.getKeyId()),
            };
            String insertUser = " INSERT INTO "
                    + TABLE_Guest_Shutter_Lock
                    + " ( "
                    + COLUMN_SUCCESS
                    + " , "
                    + COLUMN_CustomerName
                    + " , "
                    + COLUMN_PhoneNo
                    + " , "
                    + COLUMN_Address
                    + " , "
                    + COLUMN_DeviceType
                    + " , "
                    + COLUMN_DeviceCode
                    + " , "
                    + COLUMN_DeviceID
                    + " , "
                    + COLUMN_VehicleNumber
                    + " , "
                    + COLUMN_DevicePhoneNumber
                    + " , "
                    + COLUMN_DeviceSIMNumber
                    + " , "
                    + COLUMN_fileName
                    + " , "
                    + COLUMN_filepath
                    + " , "
                    + COLUMN_LockData
                    + " , "
                    + COLUMN_MACID
                    + " , "
                    + COLUMN_LockCatgeory
                    + " , "
                    + COLUMN_Access
                    + " , "
                    + COLUMN_Remoteunlock
                    + " , "
                    + COLUMN_AutoLock
                    + " , "
                    + COLUMN_Roomstatus
                    + " , "
                    + COLUMN_GuestName
                    + " , "
                    + COLUMN_Checkindate
                    + " , "
                    + COLUMN_checkoutdate
                    + " , "
                    + COLUMN_guestPhoneNo
                    + " , "
                    + COLUMN_guestEmailId
                    + " , "
                    + COLUMN_GuestMasterId
                    + " , "
                    + COLUMN_BatteryPerc
                    + " , "
                    + COLUMN_Hasgateway
                    + " , "
                    + COLUMN_LockTTAccess
                    + " , "
                    + COLUMN_Isflagui
                    + " , "
                    + COLUMN_ItemName
                    + " , "
                    + COLUMN_CompanyName
                    + " , "
                    + COLUMN_UnitId
                    + " , "
                    + COLUMN_OnlineStatus
                    + " , "
                    + COLUMN_Enddates
                    + " , "
                    + COLUMN_GateStatus
                    + " , "
                    + COLUMN_LockID
                    + " , "
                    + COLUMN_LockCode
                    + " , "
                    + COLUMN_devicenumber
                    + " , "
                    + COLUMN_MainLockID
                    + " , "
                    + COLUMN_MainLockNumber
                    + " , "
                    + COLUMN_LockType
                    + " , "
                    + COLUMN_autotimer
                    + " , "
                    + COLUMN_btlockid
                    + " , "
                    + COLUMN_MainLocktype
                    + " , "
                    + COLUMN_KeyId
                    + " ) "
                    + " VALUES "
                    + " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            mDatabase.execSQL(insertUser, bindArgs);
        }
    }


    public void updateSignBitmap(String id, String Name, String ACGroup, String BillbyBill, String CostCenter, String DefaultCostCenter, String TaxInfo, String ShowMobile, String UploadStatus, String datetime) {

        String[] bindArgs = {
                Name,
                ACGroup,
                BillbyBill,
                CostCenter,
                DefaultCostCenter,
                TaxInfo,
                ShowMobile,
                UploadStatus,
                datetime,
                id,
        };

        String update = " UPDATE "
                + TABLE_Guest_Shutter_Lock
                + " SET "
                + COLUMN_SUCCESS
                + " = ?, "
                + COLUMN_CustomerName
                + " = ?, "
                + COLUMN_PhoneNo
                + " = ?, "
                + COLUMN_Address
                + " = ?, "
                + COLUMN_DeviceType
                + " = ?, "
                + COLUMN_DeviceCode
                + " = ?, "
                + COLUMN_DeviceID
                + " = ?, "
                + COLUMN_VehicleNumber
                + " = ?, "
                + COLUMN_CompanyName
                + " = ?, "
                + COLUMN_DevicePhoneNumber
                + " = ?, "
                + COLUMN_DeviceSIMNumber
                + " = ?, "
                + COLUMN_fileName
                + " = ?, "
                + COLUMN_filepath
                + " = ?, "
                + COLUMN_LockData
                + " = ?, "
                + COLUMN_MACID
                + " = ?, "
                + COLUMN_LockCatgeory
                + " = ?, "
                + COLUMN_Access
                + " = ?, "
                + COLUMN_Remoteunlock
                + " = ?, "
                + COLUMN_AutoLock
                + " = ?, "
                + COLUMN_Roomstatus
                + " = ?, "
                + COLUMN_GuestName
                + " = ?, "
                + COLUMN_Checkindate
                + " = ?, "
                + COLUMN_checkoutdate
                + " = ?, "
                + COLUMN_guestPhoneNo
                + " = ?, "
                + COLUMN_guestEmailId
                + " = ?, "
                + COLUMN_GuestMasterId
                + " = ?, "
                + COLUMN_BatteryPerc
                + " = ?, "
                + COLUMN_Hasgateway
                + " = ?, "
                + COLUMN_LockTTAccess
                + " = ?, "
                + COLUMN_Isflagui
                + " = ?, "
                + COLUMN_ItemName
                + " = ?, "
                + COLUMN_CompanyName
                + " = ?, "
                + COLUMN_UnitId
                + " = ?, "
                + COLUMN_OnlineStatus
                + " = ?, "
                + COLUMN_Enddates
                + " = ?, "
                + COLUMN_GateStatus
                + " = ?, "
                + COLUMN_LockID
                + " = ?, "
                + COLUMN_LockCode
                + " = ?, "
                + COLUMN_devicenumber
                + " = ?, "
                + COLUMN_MainLockID
                + " = ?, "
                + COLUMN_MainLockNumber
                + " = ?, "
                + COLUMN_LockType
                + " = ?, "
                + COLUMN_autotimer
                + " = ?, "
                + COLUMN_btlockid
                + " = ?, "
                + COLUMN_MainLocktype
                + " = ?, "
                + COLUMN_KeyId
                + " = ? WHERE "
                + COLUMN_KEY_ID + "= ?";

        mDatabase.execSQL(update, bindArgs);

    }

    public void deleteUploadedPhotoById(int id) {

        String deleteSingleRow = " DELETE "
                + " FROM "
                + TABLE_Guest_Shutter_Lock
                + " WHERE "
                + COLUMN_KEY_ID
                + " = "
                + id;
        mDatabase.execSQL(deleteSingleRow);
    }


//    public Queue<DbImageUpload> getNewUploadList(String id) {
//        Queue<DbImageUpload> queue = new LinkedList<>();
//        String getAllPhotoDetails = " SELECT "
//                + " * "
//                + " FROM "
//                + TABLE_Guest_Shutter_Lock + " WHERE " + COLUMN_UploadStatus + " = 0 AND " + COLUMN_KEY_ID + " =" + id;
//        Cursor cursor = mDatabase.rawQuery(getAllPhotoDetails, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                DBAccountsMasterUpload modle = cursorToData(cursor);
//                if (modle != null) {
//
//                }
//                cursor.moveToNext();
//            }
//        }
//        closeCursor(cursor);
//        return queue;
//    }


    public int getCurrentAccountsCount(String id) {
        Log.e("id in database==" , ""+id);
        String[] bindArgs = {
                id
        };

        String countQuery = " SELECT  * FROM "
                + TABLE_Guest_Shutter_Lock
                + " WHERE "
                + COLUMN_KEY_ID
                + "= ?";

        Cursor cursor = mDatabase.rawQuery(countQuery, bindArgs);

        return cursor.getCount();
    }

    public int getMasterTableItems(int id) {
        int count = 0;

        String countQuery = " SELECT  count(*) FROM "
                + TABLE_Guest_Shutter_Lock
                + " WHERE "
                + COLUMN_KEY_ID
                + "= " + id;

        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }
        closeCursor(cursor);
        return count;

    }


    public ArrayList<GuestShutterLockModel> selectAll() {
        String getAllDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_Guest_Shutter_Lock + " where CustomerName <>''";

        Cursor cursor = mDatabase.rawQuery(getAllDetails, null);
        ArrayList<GuestShutterLockModel> dataList = manageCursor(cursor);
        closeCursor(cursor);

        return dataList;
    }


    public boolean isDevIdExists(String devId) {
        boolean exists = false;
        //check dev Id exists or not
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_Guest_Shutter_Lock + " WHERE DeviceCode='" + devId + "'", null);
        exists = cursor.moveToNext();
        closeCursor(cursor);
        return exists;
    }

    public ArrayList<GuestShutterLockModel> selectAllLastIdPhotos(String id) {

        String[] bindArgs = {
                id
        };

        String getAllDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_Guest_Shutter_Lock
                + " WHERE "
                + COLUMN_KEY_ID
                + " = ?";
        ;

        Cursor cursor = mDatabase.rawQuery(getAllDetails, bindArgs);
        ArrayList<GuestShutterLockModel> dataList = manageCursor(cursor);
        closeCursor(cursor);

        return dataList;
    }

//    public ArrayList<DBAccountsMasterUpload> selectUploadPhotos() {
//
//        String getAllDetails = " SELECT "
//                + " * "
//                + " FROM "
//                + TABLE_Guest_Shutter_Lock
//                + " WHERE "
//                + COLUMN_UploadStatus
//                + " = 0";
//        Cursor cursor = mDatabase.rawQuery(getAllDetails, null);
//        ArrayList<DBAccountsMasterUpload> dataList = manageCursor(cursor);
//        closeCursor(cursor);
//
//        return dataList;
//    }

    protected GuestShutterLockModel cursorToData(Cursor cursor) {
        GuestShutterLockModel model = new GuestShutterLockModel();
        model.setSuccess(cursor.getString(cursor.getColumnIndex(COLUMN_SUCCESS)));
        model.setCustomerName(cursor.getString(cursor.getColumnIndex(COLUMN_CustomerName)));
        model.setPhoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_PhoneNo)));
        model.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_Address)));
        model.setDeviceType(cursor.getString(cursor.getColumnIndex(COLUMN_DeviceType)));
        model.setDeviceCode(cursor.getString(cursor.getColumnIndex(COLUMN_DeviceCode)));
        model.setDeviceID(cursor.getString(cursor.getColumnIndex(COLUMN_DeviceID)));
        model.setVehicleNumber(cursor.getString(cursor.getColumnIndex(COLUMN_VehicleNumber)));
        model.setCompanyName(cursor.getString(cursor.getColumnIndex(COLUMN_CompanyName)));
        model.setDevicePhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_DevicePhoneNumber)));
        model.setDeviceSIMNumber(cursor.getString(cursor.getColumnIndex(COLUMN_DeviceSIMNumber)));
        model.setFileName(cursor.getString(cursor.getColumnIndex(COLUMN_fileName)));
        model.setFilepath(cursor.getString(cursor.getColumnIndex(COLUMN_filepath)));
        model.setLockData(cursor.getString(cursor.getColumnIndex(COLUMN_LockData)));
        model.setMACID(cursor.getString(cursor.getColumnIndex(COLUMN_MACID)));
        model.setLockCatgeory(cursor.getString(cursor.getColumnIndex(COLUMN_LockCatgeory)));
        model.setAccess(cursor.getString(cursor.getColumnIndex(COLUMN_Access)));
        model.setRemoteunlock(cursor.getString(cursor.getColumnIndex(COLUMN_Remoteunlock)));
        model.setAutoLock(cursor.getString(cursor.getColumnIndex(COLUMN_AutoLock)));
        model.setRoomStatus(cursor.getString(cursor.getColumnIndex(COLUMN_Roomstatus)));
        model.setGuestName(cursor.getString(cursor.getColumnIndex(COLUMN_GuestName)));
        model.setCheckindate(cursor.getString(cursor.getColumnIndex(COLUMN_Checkindate)));
        model.setCheckoutdate(cursor.getString(cursor.getColumnIndex(COLUMN_checkoutdate)));
        model.setGuestPhoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_guestPhoneNo)));
        model.setGuestEmailId(cursor.getString(cursor.getColumnIndex(COLUMN_guestEmailId)));
        model.setGuestMasterId(cursor.getString(cursor.getColumnIndex(COLUMN_GuestMasterId)));
        model.setBatteryPerc(cursor.getString(cursor.getColumnIndex(COLUMN_BatteryPerc)));
        model.setHasgateway(cursor.getString(cursor.getColumnIndex(COLUMN_Hasgateway)));
        model.setLockTTAccess(cursor.getString(cursor.getColumnIndex(COLUMN_LockTTAccess)));
        model.setIsflagui(cursor.getString(cursor.getColumnIndex(COLUMN_Isflagui)));
        model.setUnitId(cursor.getString(cursor.getColumnIndex(COLUMN_UnitId)));
        model.setOnlineStatus(cursor.getString(cursor.getColumnIndex(COLUMN_OnlineStatus)));
        model.setEnddates(cursor.getString(cursor.getColumnIndex(COLUMN_Enddates)));
        model.setGateStatus(cursor.getString(cursor.getColumnIndex(COLUMN_GateStatus)));
        model.setLockID(cursor.getString(cursor.getColumnIndex(COLUMN_LockID)));
        model.setLockCode(cursor.getString(cursor.getColumnIndex(COLUMN_LockCode)));
        model.setDevicenumber(cursor.getString(cursor.getColumnIndex(COLUMN_devicenumber)));
        model.setMainLockID(cursor.getString(cursor.getColumnIndex(COLUMN_MainLockID)));
        model.setMainLockNumber(cursor.getString(cursor.getColumnIndex(COLUMN_MainLockNumber)));
        model.setLockType(cursor.getString(cursor.getColumnIndex(COLUMN_LockType)));
        model.setAutotimer(cursor.getString(cursor.getColumnIndex(COLUMN_autotimer)));
        model.setBtlockid(cursor.getString(cursor.getColumnIndex(COLUMN_btlockid)));
        model.setMainLocktype(cursor.getString(cursor.getColumnIndex(COLUMN_MainLocktype)));
        model.setItemName(cursor.getString(cursor.getColumnIndex(COLUMN_ItemName)));
        model.setKeyId(cursor.getString(cursor.getColumnIndex(COLUMN_KeyId)));
        return model;
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected ArrayList<GuestShutterLockModel> manageCursor(Cursor cursor) {
        ArrayList<GuestShutterLockModel> dataList = new ArrayList<GuestShutterLockModel>();
        //DbBillAbleUnder model=null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GuestShutterLockModel model = cursorToData(cursor);
                if (model != null) {
                    dataList.add(model);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }

}
