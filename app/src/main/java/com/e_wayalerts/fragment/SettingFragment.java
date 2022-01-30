package com.e_wayalerts.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.SplashActivity;
import com.e_wayalerts.activity.alert_group.AlertGroupListFragment;
import com.e_wayalerts.activity.loginmodule.LanguageActivity;
import com.e_wayalerts.activity.smscredit.SmsCreditFragment;

import androidx.fragment.app.Fragment;


public class SettingFragment extends Fragment implements View.OnClickListener {
	
	RelativeLayout profile_layout, setting_layout, fleet_layout, language_layout,
            alertgroup_layout,
			sms_layout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_setting, container, false);
		inIt(view);
		listener();
		return view;
	}
 
	private void listener() {
        profile_layout.setOnClickListener(this);
        setting_layout.setOnClickListener(this);
        fleet_layout.setOnClickListener(this);
        language_layout.setOnClickListener(this);
        alertgroup_layout.setOnClickListener(this);
        sms_layout.setOnClickListener(this);
        
    }
    
    private void inIt(View view) {
		profile_layout = view.findViewById(R.id.profile);
		setting_layout = view.findViewById(R.id.setting);
		fleet_layout = view.findViewById(R.id.fleet);
		language_layout = view.findViewById(R.id.language);
		alertgroup_layout = view.findViewById(R.id.alert_group);
		sms_layout = view.findViewById(R.id.Sms_cradit);
	}
    
    @Override
    public void onClick(View v) {
	    switch (v.getId()){
            case R.id.profile:
                
                break;
            case R.id.setting:
            
                break;
            case R.id.fleet:
                Utility.loadFragment(requireActivity(), new Fleet_List_Fragment(),
                        true,
                        null);
                break;
            case R.id.language:
	            /*Intent intent = new Intent(getActivity(), LanguageActivity.class);
	            startActivity(intent);*/
	
	            break;
            case R.id.alert_group:
                Utility.loadFragment(requireActivity(), new AlertGroupListFragment(),
                        true,
                        null);
                break;
            case R.id.Sms_cradit:
                Utility.loadFragment(requireActivity(), new SmsCreditFragment(),
                        true,
                        null);
                break;
        }
    }
    
}