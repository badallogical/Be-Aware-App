package com.passion.beawareapp.utils;

import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.passion.beawareapp.models.News;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiUtils {
    // Logging
    private final static String LOG = ApiUtils.class.getName();


    // News API
    public static final String URL_INDIA_NEWS = "https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final String URL_INDIA_BUSINESS = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final String URL_INDIA_SCIENCE = "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final String URL_INDIA_HEALTH = "https://newsapi.org/v2/top-headlines?country=in&category=health&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final String URL_INDIA_TECH = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final String URL_INDIA_SPORTS = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=c9f6b354d59f483ca473c71976365692";
    public static final int apiCount = 6;

    public static ArrayList<News> makeHttpRequestFetchResponse(String url_string ){

        Log.v(LOG, "entered");
        String jsonResponse = "";

        ArrayList<News> news = null;

        // get url
        URL url = createURL( url_string );

        // get Response
        try {
            jsonResponse = makeHttpRequest(url);
            Log.v(LOG, jsonResponse);
        }
        catch( IOException e ){
            Log.v( LOG + "makeHttp", " "+e.getMessage() );
        }


        // parse json to get ArrayList of news
        news = parseNewsFromJson( jsonResponse );
        return news;


    }


    public static URL createURL(String url_string) {
        URL url = null;
        try {
            url = new URL(url_string);
        }
        catch( MalformedURLException e ){
            Log.v( LOG + "createURL",  " " + e.getMessage() );
        }

        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        // get url response with okhttp
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try( Response response  = client.newCall(request).execute()){
            jsonResponse = response.body().string();
        };

        return jsonResponse;
    }

    public static String convertToString(InputStream inputStream) {
        InputStreamReader iReader = null;
        BufferedReader br = null;
        StringBuilder jsonResponse = new StringBuilder("");

        if( inputStream == null ){
            return jsonResponse.toString();
        }

        try{
            iReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            br = new BufferedReader(iReader);

            String line = br.readLine();
            while( line != null ){
                Log.v(LOG, line);
                jsonResponse.append(line);
                line = br.readLine();
            }
        }
        catch( IOException e){
            Log.v( LOG + "convert to string ", " "+ e.getMessage());
        }

        return jsonResponse.toString();
    }

    public static ArrayList<News> parseNewsFromJson(String jsonResponse) {
        ArrayList<News> newsList = new ArrayList<News>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray articles = jsonObject.getJSONArray("articles");
            JSONObject article = null;
            News news = null;

            for( int i = 0 ;i < articles.length(); i++ ){
                article = articles.getJSONObject(i);

                // prepare news
                news = new News();
                news.setTitle( article.getString("title"));
                news.setDesc( article.getString("description"));
                news.setUrl_to_src( article.getString("url") );
                news.setUtl_to_img( article.getString("urlToImage"));
                news.setContent( article.getString( "content"));

                // validata news
                if( news.validate() ){
                    // collect
                    newsList.add(news);
                }
            }
        }
        catch(JSONException e ){
            Log.v( LOG, " "+e.getMessage() );
        }

        return newsList;
    }
}
