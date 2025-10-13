package com.codersworld.safelib.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codersworld.safelib.beans.GenerateOTP;

import java.util.ArrayList;

public class OTPDao {

    private static final String TABLE_OTP = "sb_local_otp";
    // Contacts Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_OTP = "otp";
    private static final String COLUMN_RPWD = "rpwd_otp";
    private static final String COLUMN_OTP_GENERATE_TIME = "rpwd_otp_generate_time";
    private static final String COLUMN_CONTACT_ID = "constact_id";
    private static final String COLUMN_DEVICE_ID = "deviceId";
    private static final String COLUMN_VEHICAL_NO = "vehical_no";
    private static final String COLUMN_TYPE = "type";

    private SQLiteDatabase mDatabase;
    private Context mContext;

    public OTPDao(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }

    public static String getCreateTable() {
        String CREATE_WORK_DETAILS_TABLE = "CREATE TABLE " + TABLE_OTP
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_OTP + " TEXT ,"
                + COLUMN_RPWD + " TEXT ,"
                + COLUMN_OTP_GENERATE_TIME + " TEXT ,"
                + COLUMN_CONTACT_ID + " TEXT ,"
                + COLUMN_DEVICE_ID + " TEXT ,"
                + COLUMN_VEHICAL_NO + " TEXT ,"
                + COLUMN_TYPE + " TEXT "
                + ")";
        return CREATE_WORK_DETAILS_TABLE;
    }

    public static String getDropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_OTP;
    }

    public void deleteAll() {
        String delete_all_users = " DELETE "
                + " FROM "
                + TABLE_OTP;
        mDatabase.execSQL(delete_all_users);
    }
    public void deleteOTPById(int id) {
        String deleteSingleRow = " DELETE "
                + " FROM "
                + TABLE_OTP
                + " WHERE "
                + COLUMN_ID
                + " = "
                + id;
        mDatabase.execSQL(deleteSingleRow);
    }

    public void insert(ArrayList<GenerateOTP> workArrayList) {
        for (GenerateOTP address : workArrayList) {
            String[] bindArgs = {
                    address.getOtp(),
                    address.getRpwd(),
                    address.getOtpGenerateTime(),
                    address.getContactId(),
                    address.getDeviceId(),
                    address.getVehicalNo(),
                    address.getType()
            };
            String insertUser = " INSERT INTO "
                    + TABLE_OTP
                    + " ( "
                    + COLUMN_OTP
                    + " , "
                    + COLUMN_RPWD
                    + " , "
                    + COLUMN_OTP_GENERATE_TIME
                    + " , "
                    + COLUMN_CONTACT_ID
                    + " , "
                    + COLUMN_DEVICE_ID
                    + " , "
                    + COLUMN_VEHICAL_NO
                    + " , "
                    + COLUMN_TYPE
                    + " ) "
                    + " VALUES "
                    + " (?,?,?,?,?,?,?)";

            mDatabase.execSQL(insertUser, bindArgs);
        }
    }

    public ArrayList<GenerateOTP> selectAll() {
        String getAllWorkDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_OTP;

        Cursor cursor = mDatabase.rawQuery(getAllWorkDetails, null);
        ArrayList<GenerateOTP> dataList = manageCursor(cursor);
        closeCursor(cursor);
        return dataList;
    }

    protected GenerateOTP cursorToData(Cursor cursor) {
        GenerateOTP work = new GenerateOTP();
        work.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        work.setOtp(cursor.getString(cursor.getColumnIndex(COLUMN_OTP)));
        work.setRpwd(cursor.getString(cursor.getColumnIndex(COLUMN_RPWD)));
        work.setOtpGenerateTime(cursor.getString(cursor.getColumnIndex(COLUMN_OTP_GENERATE_TIME)));
        work.setContactId(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_ID)));
        work.setDeviceId(cursor.getString(cursor.getColumnIndex(COLUMN_DEVICE_ID)));
        work.setVehicalNo(cursor.getString(cursor.getColumnIndex(COLUMN_VEHICAL_NO)));
        work.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));

        return work;
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    protected ArrayList<GenerateOTP> manageCursor(Cursor cursor) {
        ArrayList<GenerateOTP> dataList = new ArrayList<GenerateOTP>();
        //DbBillAbleUnder model=null;
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GenerateOTP model = cursorToData(cursor);
                if (model != null) {
                    dataList.add(model);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }
}
