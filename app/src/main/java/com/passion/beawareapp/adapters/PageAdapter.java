package com.passion.beawareapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.passion.beawareapp.R;
import com.passion.beawareapp.fragments.DummyFragment;
import com.passion.beawareapp.models.Developer;
import com.passion.beawareapp.models.News;
import com.passion.beawareapp.utils.ApiUtils;
import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    Context context;

    public PageAdapter(FragmentManager fm){
        super(fm);
    }

    public PageAdapter(FragmentManager fm, Context context ) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if( position == 6 ){
            return new Developer();
        }
        return new DummyFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       switch(position){
           case 1: return context.getString(R.string.business);
           case 2: return context.getString(R.string.health);
           case 3: return context.getString(R.string.sports);
           case 4: return context.getString(R.string.science);
           case 5: return context.getString(R.string.tech);
           case 6: return "Developer Info";
           default: return context.getString(R.string.my_news);
       }
    }

    @Override
    public int getCount() {
        return ApiUtils.apiCount + 1;
    }
}
