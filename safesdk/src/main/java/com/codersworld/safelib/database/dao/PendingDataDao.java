package com.codersworld.safelib.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codersworld.safelib.beans.PendingDataBean;

import java.util.ArrayList;

public class PendingDataDao {

    private static final String TABLE_PENDINGDATA_UPLOAD = "sb_pending_data";
    // Contacts Table Columns names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_APINAME = "apiname";
    private static final String COLUMN_FIRSTCOLUMN = "first_column";
    private static final String COLUMN_DETAILS = "details";
    private static final String COLUMN_UPLOAD_STATUS = "upload_status";
    private static final String COLUMN_SERVER_ID = "server_id";
    private static final String COLUMN_DATE = "date";

    private SQLiteDatabase mDatabase;
    private Context mContext;

    public PendingDataDao(SQLiteDatabase database, Context context) {
        mDatabase = database;
        mContext = context;
    }
    public static String getCreateTable() {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PENDINGDATA_UPLOAD
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_APINAME + " TEXT ,"
                + COLUMN_FIRSTCOLUMN + " TEXT ,"
                + COLUMN_DETAILS + " TEXT ,"
                + COLUMN_UPLOAD_STATUS + " TEXT ,"
                + COLUMN_SERVER_ID + " TEXT ,"
                + COLUMN_DATE + " DATETIME)";
        return CREATE_TABLE;
    }

    public static String getDropTable() {
        return "DROP TABLE IF EXISTS " + TABLE_PENDINGDATA_UPLOAD;
    }

    public void deleteAll() {
        String delete_all_users = " DELETE "
                + " FROM "
                + TABLE_PENDINGDATA_UPLOAD;
        mDatabase.execSQL(delete_all_users);
    }

    public void insert(ArrayList<PendingDataBean> workArrayList) {
        for (PendingDataBean address : workArrayList) {
            String[] bindArgs = {
                    address.getApiname(),
                    address.getFirstcolumn(),
                    address.getDetail(),
                    address.getUploadStatus(),
                    address.getServerId(),
                    address.getDate()
            };
            String insertUser = " INSERT INTO "
                    + TABLE_PENDINGDATA_UPLOAD
                    + " ( "
                    + COLUMN_APINAME
                    + " , "
                    + COLUMN_FIRSTCOLUMN
                    + " , "
                    + COLUMN_DETAILS
                    + " , "
                    + COLUMN_UPLOAD_STATUS
                    + " , "
                    + COLUMN_SERVER_ID
                    + " , "
                    + COLUMN_DATE
                    + " ) "
                    + " VALUES "
                    + " (?,?,?,?,?,?)";

            mDatabase.execSQL(insertUser, bindArgs);
        }
    }

    public void setIdToTable(String serverId, String masterId) {
        //int checkMark = result ? 1 : 0;
        String[] bindArgs = {
                serverId,
                String.valueOf(masterId)
        };
        String update = " UPDATE "
                + TABLE_PENDINGDATA_UPLOAD
                + " SET "
                + COLUMN_SERVER_ID
                + " = ? WHERE " + COLUMN_ID + "= ?";
        mDatabase.execSQL(update, bindArgs);
    }

    public void setServerDetails(int id, String serverId, int isUploaded) {
        //int checkMark = result ? 1 : 0;
        String[] bindArgs = {
                serverId,
                String.valueOf(isUploaded),
                String.valueOf(id)
        };
        String update = " UPDATE "
                + TABLE_PENDINGDATA_UPLOAD
                + " SET "
                + COLUMN_SERVER_ID
                + " = ?, "
                + COLUMN_UPLOAD_STATUS
                + " = ? WHERE " + COLUMN_ID + "= ?";
        mDatabase.execSQL(update, bindArgs);

    }

    public int getCurrentPemdingDataCount(String id) {
        String[] bindArgs = {
                id
        };
        String countQuery = " SELECT  * FROM "
                + TABLE_PENDINGDATA_UPLOAD
                + " WHERE "
                + COLUMN_ID
                + "= ?";
        Cursor cursor = mDatabase.rawQuery(countQuery, bindArgs);
        return cursor.getCount();
    }
    public int getMasterTableItems(int id) {
        int count = 0;

        String countQuery = " SELECT  count(*) FROM "
                + TABLE_PENDINGDATA_UPLOAD
                + " WHERE "
                + COLUMN_ID
                + "= "+id;

        Cursor cursor = mDatabase.rawQuery(countQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
        }
        closeCursor(cursor);
        return count;

    }
    public ArrayList<PendingDataBean> selectAll() {
        String getAllDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_PENDINGDATA_UPLOAD;

        Cursor cursor = mDatabase.rawQuery(getAllDetails, null);
        ArrayList<PendingDataBean> dataList = manageCursor(cursor);
        closeCursor(cursor);

        return dataList;
    }
    public ArrayList<PendingDataBean> selectAllLastIdPendingData(String id) {
        String[] bindArgs = {
                id
        };
        String getAllDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_PENDINGDATA_UPLOAD
                + " WHERE "
                + COLUMN_ID
                + " = ?";
        ;
        Cursor cursor = mDatabase.rawQuery(getAllDetails, bindArgs);
        ArrayList<PendingDataBean> dataList = manageCursor(cursor);
        closeCursor(cursor);

        return dataList;
    }
    public ArrayList<PendingDataBean> selectUploadData() {

        String getAllDetails = " SELECT "
                + " * "
                + " FROM "
                + TABLE_PENDINGDATA_UPLOAD
                + " WHERE "
                + COLUMN_UPLOAD_STATUS
                + " = 0 order by _id asc";
        Cursor cursor = mDatabase.rawQuery(getAllDetails, null);
        ArrayList<PendingDataBean> dataList = manageCursor(cursor);
        closeCursor(cursor);
        return dataList;
    }
    protected PendingDataBean cursorToData(Cursor cursor) {
        PendingDataBean model = new PendingDataBean();
        model.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        model.setApiname(cursor.getString(cursor.getColumnIndex(COLUMN_APINAME)));
        model.setFirstcolumn(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTCOLUMN)));
        model.setDetail(cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS)));
        model.setUploadStatus(cursor.getString(cursor.getColumnIndex(COLUMN_UPLOAD_STATUS)));
        model.setServerId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVER_ID)));
        model.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
        return model;
    }
    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
    protected ArrayList<PendingDataBean> manageCursor(Cursor cursor) {
        ArrayList<PendingDataBean> dataList = new ArrayList<PendingDataBean>();

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                PendingDataBean singleModel = cursorToData(cursor);
                if (singleModel != null) {
                    dataList.add(singleModel);
                }
                cursor.moveToNext();
            }
        }
        return dataList;
    }

}
