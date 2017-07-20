package com.tripletres.scrapperapp.data.datasource.remote;

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
        Call<Result> results = service.getEmbedded(url);
        results.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                callback.onError();
            }
        });
    }

    /**
     * Result for embedded api
     */
    public class Result {
        public int width;
        public String author_name;
        public String author_url;
        public String version;
        public String provider_url;
        public String provider_name;
        public String thumbnail_width;
        public String thumbnail_url;
        public int height;
        public String thumbnail_height;
        public String html;
        public String url;
        public String type;
        public String title;
    }
}
