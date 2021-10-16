package com.e_wayalerts.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_wayalerts.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fleet_List_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fleet_List_Fragment extends Fragment {

    public Fleet_List_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fleet__list_, container, false);
    }

}