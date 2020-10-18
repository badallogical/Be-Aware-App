package com.passion.beawareapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;


public class DummyFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    String text;
    ViewPager2 viewPager2;
    PageStateAdapter pageStateAdapter;
    int category;

    final  int Loader_id = 1;

    public DummyFragment(int category) {
        this.category = category;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initiate loader
        getLoaderManager().initLoader(Loader_id, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dummy, container, false);
        return v;
    }


    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id,  Bundle args) {
        switch (category) {
            case 1:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_BUSINESS);
            case 2:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_HEALTH);
            case 3:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_SPORTS);
            case 4:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_SCIENCE);
            case 5:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_TECH);
            default:
                return new NewsLoader(getContext(), ApiUtils.URL_INDIA_NEWS);

        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<News>> loader, ArrayList<News> data) {
        // done progess
        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progress);
        progressBar.setVisibility(ProgressBar.GONE);

        // setup View Pager
        viewPager2 = (ViewPager2) getView().findViewById(R.id.viewpager2);
        pageStateAdapter = new PageStateAdapter(this, data);
        viewPager2.setAdapter(pageStateAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<News>> loader) {
        viewPager2.setAdapter(new PageStateAdapter(this));
    }
}
