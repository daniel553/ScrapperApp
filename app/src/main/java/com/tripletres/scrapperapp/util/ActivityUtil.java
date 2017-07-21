package com.tripletres.scrapperapp.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.Toast;

/**
 * Utility class for activities
 * Created by Daniel on 20/07/2017.
 */

public class ActivityUtil {

    /**
     * Adds a fragment to activity
     *
     * @param fragmentManager
     * @param fragment
     * @param id              - Frame id
     * @throws NullPointerException
     */
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int id)
            throws NullPointerException {
        if (fragmentManager == null || fragment == null)
            throw new NullPointerException("fragmentManager == null || fragment == null");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(id, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Shows an error message as a Toast.
     *
     * @param id
     * @param context
     */
    public static void showError(int id, Context context) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }
}
