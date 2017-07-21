package com.tripletres.scrapperapp.data.datasource.remote;

import io.realm.RealmObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Embedded datasource class.
 * Gets embedded content from noembed
 * See https://noembed.com/
 * <p>
 * Created by Daniel on 20/07/2017.
 */

public class EmbeddedDataSource implements EmbeddedDataSourceContract {
    @Override
    public void getEmbedded(String url, final GetCallback callback) {
        if (url == null)
            callback.onError();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NoEmbedService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NoEmbedService service = retrofit.create(NoEmbedService.class);
        Call<Embedded> results = service.getEmbedded(url);
        results.enqueue(new Callback<Embedded>() {
            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                callback.onError();
            }
        });
    }

}
