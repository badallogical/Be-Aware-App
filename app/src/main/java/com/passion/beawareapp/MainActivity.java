package com.passion.beawareapp;

import androidx.lifecycle.ViewModelProviders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SpinnerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.passion.beawareapp.adapters.PageAdapter;
import com.passion.beawareapp.models.News;
import com.passion.beawareapp.room.NewsViewModel;
import com.passion.beawareapp.utils.ApiUtils;
import com.passion.beawareapp.utils.NewsLoader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    final int Loader_id = 1;

    ViewPager vp;
    PageAdapter pAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        if( !isConnectedToNetwork() ){
            // notify
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("No Internet Connection");
            alert.setMessage("Check your internet connection or continue with Offline Mode");
            alert.setPositiveButton("Offline Mode", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alert.setIcon(R.drawable.ic_error_outline_black_24dp);
            AlertDialog dialog = alert.create();
            dialog.show();

            //TODO:  offline mode


        }

        // setup View Pager
        vp = (ViewPager) findViewById(R.id.viewpager);
        pAdapter = new PageAdapter( getSupportFragmentManager(), this );
        vp.setAdapter(pAdapter);

        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.setupWithViewPager(vp);
    }


    public void setFav(View v) {
        Log.v("message", "called");
        ImageView imgview = (ImageView) v;
        if (imgview.getTag() == "false") {
            imgview.setBackground(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
            imgview.setTag("true");
        } else {
            imgview.setBackground(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
            imgview.setTag("false");
        }
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connMng = (ConnectivityManager) this.getSystemService(ContextThemeWrapper.CONNECTIVITY_SERVICE);
        if (connMng.getActiveNetworkInfo() != null && connMng.getActiveNetworkInfo().isConnected())
            return true;
        return false;
    }

}
