package com.e_wayalerts.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.alert_group.AlertGroupListFragment;


public class SettingFragment extends Fragment {
RelativeLayout profile_layout,setting_layout,fleet_layout,language_layout,alertgroup_layout,sms_layout;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        profile_layout = view.findViewById(R.id.profile);
        profile_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setting_layout = view.findViewById(R.id.setting);
        setting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fleet_layout = view.findViewById(R.id.fleet);
        fleet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.loadFragment(requireActivity(), new Fleet_List_Fragment(),
                        true,
                        null);
            }
        });
        language_layout = view.findViewById(R.id.language);
        language_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        alertgroup_layout = view.findViewById(R.id.alert_group);
        alertgroup_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.loadFragment(requireActivity(), new AlertGroupListFragment(),
                        true,
                        null);
            }
        });
        sms_layout = view.findViewById(R.id.Sms_cradit);
        sms_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}