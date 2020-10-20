package com.passion.beawareapp.models;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.passion.beawareapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Developer extends Fragment {

    public Developer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.developer_info, container, false);
    }
}
