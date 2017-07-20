package com.tripletres.scrapperapp.util;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Realm utility class
 * Created by Daniel on 20/07/2017.
 */

public class RealmUtil {

    /**
     * Sets the default configuration
     *
     * @param context App context
     */
    public static void setConfig(Context context) {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }


    /**
     * Default instance
     *
     * @return
     */
    public static Realm getInstance() {
        return Realm.getDefaultInstance();
    }
}
