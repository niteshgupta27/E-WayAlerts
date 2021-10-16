package com.e_wayalerts.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e_wayalerts.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaffListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaffListFragment extends Fragment {


    public StaffListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_list, container, false);
    }

}