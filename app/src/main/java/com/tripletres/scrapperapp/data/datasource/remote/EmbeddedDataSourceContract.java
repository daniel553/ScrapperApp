package com.tripletres.scrapperapp.data.datasource.remote;

/**
 * Embedded data source interface.
 * Created by Daniel on 20/07/2017.
 */

public interface EmbeddedDataSourceContract {
    interface GetCallback {
        void onSuccess(Embedded url);

        void onError();
    }

    /**
     * Gets embedded data from URL
     *
     * @param url
     * @param callback
     */
    void getEmbedded(String url, GetCallback callback);
}
