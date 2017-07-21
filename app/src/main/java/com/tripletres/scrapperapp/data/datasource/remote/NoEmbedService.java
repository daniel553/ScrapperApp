package com.tripletres.scrapperapp.data.datasource.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface definition for get embedded data from
 * <p>
 * Created by Daniel on 20/07/2017.
 */

public interface NoEmbedService {
    String BASE_URL = "http://noembed.com/";

    @GET("embed")
    Call<Embedded> getEmbedded(@Query("url") String url);
}
