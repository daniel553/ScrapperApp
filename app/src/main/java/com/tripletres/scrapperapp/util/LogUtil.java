package com.tripletres.scrapperapp.util;

import android.util.Log;

/**
 * Debugger
 * Created by Daniel on 20/07/2017.
 */

public class LogUtil {
    /**
     * Debug
     * @param tag
     * @param msg
     */
    public static void d(final String tag, final String msg) {
        if (AppUtil.DEBUG)
            Log.d(tag, msg);
    }

    /**
     * Error
     * @param tag
     * @param msg
     * @param tr
     */
    public static void e(final String tag, final String msg, Throwable tr) {
        if (AppUtil.DEBUG)
            Log.e(tag, msg, tr);
    }
}
