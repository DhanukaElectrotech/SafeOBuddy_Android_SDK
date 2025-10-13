package com.codersworld.safelib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codersworld.safelib.database.dao.GuestShutterLockDAO;
import com.codersworld.safelib.database.dao.OTPDao;
import com.codersworld.safelib.database.dao.PendingDataDao;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "safelock_sdk_2023.db";
    public static final int DATABASE_VERSION = 1;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create all tables
        sqLiteDatabase.execSQL(OTPDao.getCreateTable());
        sqLiteDatabase.execSQL(PendingDataDao.getCreateTable());
        sqLiteDatabase.execSQL(GuestShutterLockDAO.getCreateTableUpload());

    }

    public void drop(SQLiteDatabase sqLiteDatabase) {
        // drop all tables
        sqLiteDatabase.execSQL(OTPDao.getDropTable());
        sqLiteDatabase.execSQL(PendingDataDao.getDropTable());
        sqLiteDatabase.execSQL(GuestShutterLockDAO.getDropTableUpload());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion >= oldVersion) {
            drop(sqLiteDatabase);
            onCreate(sqLiteDatabase);
        }
    }
}
