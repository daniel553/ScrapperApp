package com.tripletres.scrapperapp.util;

import android.text.TextUtils;
import android.webkit.URLUtil;

/**
 * Format utility class.
 * <p>
 * Created by Daniel on 20/07/2017.
 */

public class FormatUtil {

    /**
     * Gets an URL from a string
     *
     * @param s - Target string
     * @return an url string, null otherwise
     */
    public static String getUrl(String s) {
        //Should I contradict Google? https://developer.android.com/reference/android/webkit/URLUtil.html
        if (s != null && !TextUtils.isEmpty(s)) {
            String[] split = s.split("\\s+");
            for (String part : split) {
                if (URLUtil.isHttpUrl(part) || URLUtil.isHttpsUrl(part)) {
                    return part;
                }
            }
        }
        return null;
    }
}
