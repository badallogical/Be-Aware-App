package com.passion.beawareapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.passion.beawareapp.R;
import com.passion.beawareapp.models.News;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    String url_string;
    static HashMap<String, Boolean> urlState = new HashMap<String,Boolean>();
    SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getString(R.string.SharedPreference1), Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    Gson gson = new Gson();

    Context dummyContext, mainContext;

    public NewsLoader(Context context, String url_string, Context mainContext ) {
        super(context);
        this.dummyContext = context;
        this.url_string = url_string;
        this.mainContext = mainContext;
    }

    @Override
    protected void onStartLoading() {
        // good practise trigger the loadInBackground
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        ArrayList<News> news = null;
        if (TextUtils.isEmpty(url_string)) {
            return null;
        }

        if( !isConnectedToNetwork() ){
            if( sharedPreferences.contains(url_string) ){
                news = getNewsArray(sharedPreferences.getStringSet(url_string, null) );
            }
            else{
                Toast.makeText(mainContext, "No News Available", Toast.LENGTH_LONG).show();
            }
        }
        else{
            if( urlState.containsKey(url_string) && urlState.get(url_string) == true ){
                    news = getNewsArray(sharedPreferences.getStringSet(url_string, null) );
            }
            else{
                news = ApiUtils.makeHttpRequestFetchResponse(url_string);
                editor.putStringSet( url_string, getSetString(news));
                editor.apply();

                urlState.put(url_string, true);
            }
        }

        return news;
    }

    Set<String> getSetString(ArrayList<News> news) {
        Set<String> newsString = new HashSet<>();

        for (News n : news) {
            newsString.add(gson.toJson(n));
        }

        return newsString;
    }

    ArrayList<News> getNewsArray( Set<String> newsString ){
        ArrayList<News> newsArray = new ArrayList<>();

        for( String s : newsString ){
            newsArray.add( gson.fromJson(s,News.class) );
        }

        return newsArray;
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connMng = (ConnectivityManager) dummyContext.getSystemService(ContextThemeWrapper.CONNECTIVITY_SERVICE);
        if (connMng.getActiveNetworkInfo() != null && connMng.getActiveNetworkInfo().isConnected())
            return true;
        return false;
    }

}
