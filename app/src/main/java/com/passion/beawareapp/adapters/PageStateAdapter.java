package com.passion.beawareapp.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.passion.beawareapp.fragments.ChildFragment;
import com.passion.beawareapp.models.News;

import java.util.ArrayList;

public class PageStateAdapter extends FragmentStateAdapter {

    ArrayList<News> news;

    public PageStateAdapter( Fragment fragment ){
        super(fragment);
    }

    public PageStateAdapter( Fragment fragment, ArrayList<News> news ) {
        super(fragment);
        this.news = news;
    }

    @Override
    public Fragment createFragment(int position) {
       Fragment f = new ChildFragment(news.get(position));
       return f;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
