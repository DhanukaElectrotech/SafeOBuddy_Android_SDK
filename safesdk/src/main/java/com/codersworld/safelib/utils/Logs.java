package com.codersworld.safelib.utils;

import android.util.Log;
import com.dhanukaelectrotech.safesdk.BuildConfig;
/**
 * Helper methods that make logging more consistent throughout the app.
 * To change global log tag: L.LOG_TAG = "My Application";
 * @author Dmytro Danylyk
 * @version 1.0
 */
@SuppressWarnings("UnusedDeclaration")
public final class Logs {

    public static String LOG_TAG = "SAFEOBUDDY";

    // ERROR logging
    public static void e(String message, Throwable cause) {
        Log.e(LOG_TAG, "[" + message + "]", cause);
    }

    public static void e(String msg) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        String callerClassName = elements[1].getFileName();
        Log.e(LOG_TAG, "[" + callerClassName + "] " + msg);
    }

    // WARNING logging
    public static void w(String message, Throwable cause) {
        Log.w(LOG_TAG, "[" + message + "]", cause);
    }

    public static void w(String msg) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        String callerClassName = elements[1].getFileName();
        Log.w(LOG_TAG, "[" + callerClassName + "] " + msg);
    }

    // INFO logging
    public static void i(String message, Throwable cause) {
        Log.i(LOG_TAG, "[" + message + "]", cause);
    }

    public static void i(String msg) {
        Throwable t = new Throwable();
        StackTraceElement[] elements = t.getStackTrace();
        String callerClassName = elements[1].getFileName();
        Log.i(LOG_TAG, "[" + callerClassName + "] " + msg);
    }

    // DEBUG logging (uses BuildConfig from your namespace)
    public static void d(String msg, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, msg, cause);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String callerClassName = elements[1].getFileName();
            Log.d(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }

    // VERBOSE logging
    public static void v(String msg, Throwable cause) {
        if (BuildConfig.DEBUG) {
            Log.v(LOG_TAG, msg, cause);
        }
    }

    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String callerClassName = elements[1].getFileName();
            Log.v(LOG_TAG, "[" + callerClassName + "] " + msg);
        }
    }
}
