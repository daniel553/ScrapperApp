package com.tripletres.scrapperapp.data.datasource.remote;

/**
 * Created by Daniel on 20/07/2017.
 */

public interface EmbeddedDataSourceContract {
    interface GetCallback {
        void onSuccess(EmbeddedDataSource.Result url);
        void onError();
    }

    void getEmbedded(String url, GetCallback callback);
}
