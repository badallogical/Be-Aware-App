package com.passion.beawareapp.utils;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.passion.beawareapp.models.News;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    String url_string;

    public NewsLoader( Context context, String url_string) {
        super(context);
        this.url_string = url_string;
    }

    @Override
    protected void onStartLoading() {
        // good practise trigger the loadInBackground
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        ArrayList<News> news = null;
        if( TextUtils.isEmpty(url_string) ){
            return null;
        }

        news = ApiUtils.makeHttpRequestFetchResponse(url_string);
        return news;
    }
}
