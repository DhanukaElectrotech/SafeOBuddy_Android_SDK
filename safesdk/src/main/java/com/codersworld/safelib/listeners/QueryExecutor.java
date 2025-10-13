package com.codersworld.safelib.listeners;

import android.database.sqlite.SQLiteDatabase;

public interface QueryExecutor {

    public void run(SQLiteDatabase database);
}
