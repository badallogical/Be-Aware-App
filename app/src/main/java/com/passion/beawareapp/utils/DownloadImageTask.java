package com.passion.beawareapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageTask extends AsyncTask< String, Void, Bitmap> {

    // logging
    private final static String LOG = DownloadImageTask.class.getName();

    ImageView imgview;

    public DownloadImageTask( ImageView imgView ){
        this.imgview = imgView;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        String urlImage = url[0];
        Bitmap bmp = null;

        try{
            InputStream in = new URL(urlImage).openStream();
            bmp = BitmapFactory.decodeStream( in );
        }
        catch( IOException e ){
            Log.v( LOG , " "+ e.getMessage() );
        }

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imgview.setImageBitmap( bitmap );
    }
}
