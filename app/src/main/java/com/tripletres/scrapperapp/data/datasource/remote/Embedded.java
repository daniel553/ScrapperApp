package com.tripletres.scrapperapp.data.datasource.remote;

import io.realm.RealmObject;

/**
 * Created by Daniel on 20/07/2017.
 */

public class Embedded extends RealmObject {
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
