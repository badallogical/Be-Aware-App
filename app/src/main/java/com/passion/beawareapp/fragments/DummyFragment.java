package com.passion.beawareapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.passion.beawareapp.adapters.PageAdapter;
import com.passion.beawareapp.adapters.PageStateAdapter;
import com.passion.beawareapp.R;
import com.passion.beawareapp.models.News;
import com.passion.beawareapp.utils.ApiUtils;
import com.passion.beawareapp.utils.NewsLoader;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DummyFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    String text;
    ViewPager2 viewPager2;
    PageStateAdapter pageStateAdapter;
    int category;
    ArrayList<News> data = null;
    Context context;

    // child fragment last checkpoint
    public static Map<Integer, Integer> lastChildAt = new HashMap<>();


    final int Loader_id = 1;

    public DummyFragment(int category, Context context) {
        this.category = category;
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Loading the news on to the corresponding category ( online or offline depend on connection )
        DummyFragment.this.getLoaderManager().initLoader(Loader_id, null, DummyFragment.this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dummy, container, false);
        return v;
    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connMng = (ConnectivityManager) getContext().getSystemService(ContextThemeWrapper.CONNECTIVITY_SERVICE);
        if (connMng.getActiveNetworkInfo() != null && connMng.getActiveNetworkInfo().isConnected())
            return true;
        return false;
    }


    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        switch (category) {
            case 1:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_BUSINESS, context);
            case 2:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_HEALTH, context);
            case 3:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_SPORTS, context);
            case 4:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_SCIENCE, context);
            case 5:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_TECH, context);
            default:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_NEWS, context);

        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<News>> loader, ArrayList<News> data) {
        // done progess
        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progress);
        progressBar.setVisibility(ProgressBar.GONE);

        if (data == null) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(getContext());
            alertbox.setTitle("API Request Failed");
            alertbox.setIcon(R.drawable.ic_error_outline_black_24dp);
            alertbox.setMessage("Something went wrong with API request");
            alertbox.create().show();
            return;
        } else {
            this.data = data;
        }

        // setup View Pager
        viewPager2 = (ViewPager2) getView().findViewById(R.id.viewpager2);
        pageStateAdapter = new PageStateAdapter(this, data, category);
        viewPager2.setAdapter(pageStateAdapter);

        // restore last child state
        if (DummyFragment.lastChildAt.containsKey(category))
            viewPager2.setCurrentItem(DummyFragment.lastChildAt.get(category) - 1, false);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<News>> loader) {
        viewPager2.setAdapter(new PageStateAdapter(this));
    }
}
