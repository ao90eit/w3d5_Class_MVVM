package com.aoinc.w3d5_class_mvvm.util;

import android.util.Log;

public class DebugLogger {

    public static final String TAG_DEBUG = "TAG_DEBUG";
    public static final String TAG_ERROR = "TAG_ERROR";

    public static void logDebug(String debugMessage) {
        Log.d(TAG_DEBUG, debugMessage);
    }

    public static void logError(String errorMessage) {
        Log.d(TAG_ERROR, errorMessage);
    }
}
