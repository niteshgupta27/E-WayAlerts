package com.e_wayalerts.activity.add_staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.e_wayalerts.R;

import androidx.fragment.app.Fragment;

public class AddStaffFragment extends Fragment {
	
	EditText staffFirstName, staffLastName, staffMobileNumber, staffEmailAddress;
	
	Spinner jobTitleSpinner;
	
	CheckBox businessName1, businessName2, businessName3, pushCheckboxB1, emailCheckboxB1,
			smsCheckboxB1, pushCheckboxB2, emailCheckboxB2, smsCheckboxB2, pushCheckboxB3,
			emailCheckboxB3, smsCheckboxB3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_staff, container, false);
		
		init(view);
		listner();
		return view;
	}
	
	private void init(View view) {
		
		staffFirstName = view.findViewById(R.id.staffFirstName);
		staffLastName = view.findViewById(R.id.staffLastName);
		staffMobileNumber = view.findViewById(R.id.staffMobileNumber);
		staffEmailAddress = view.findViewById(R.id.staffEmailAddress);
		jobTitleSpinner = view.findViewById(R.id.jobTitleSpinner);
		businessName1 = view.findViewById(R.id.businessName1);
		businessName2 = view.findViewById(R.id.businessName2);
		businessName3 = view.findViewById(R.id.businessName3);
		pushCheckboxB1 = view.findViewById(R.id.pushCheckboxB1);
		emailCheckboxB1 = view.findViewById(R.id.emailCheckboxB1);
		smsCheckboxB1 = view.findViewById(R.id.smsCheckboxB1);
		pushCheckboxB2 = view.findViewById(R.id.pushCheckboxB2);
		emailCheckboxB2 = view.findViewById(R.id.emailCheckboxB2);
		smsCheckboxB2 = view.findViewById(R.id.smsCheckboxB2);
		pushCheckboxB3 = view.findViewById(R.id.pushCheckboxB3);
		emailCheckboxB3 = view.findViewById(R.id.emailCheckboxB3);
		smsCheckboxB3 = view.findViewById(R.id.smsCheckboxB3);
	}
	
	public void listner() {
		pushCheckboxB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (!businessName1.isChecked()) {
					pushCheckboxB1.setChecked(false);
				}
			}
		});
		smsCheckboxB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName1.isChecked()) {
					smsCheckboxB1.setChecked(false);
				}
			}
		});
		
		emailCheckboxB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName1.isChecked()) {
					emailCheckboxB1.setChecked(false);
				}
			}
		});
		
		pushCheckboxB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName2.isChecked()) {
					pushCheckboxB2.setChecked(false);
				}
			}
		});
		
		smsCheckboxB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName2.isChecked()) {
					smsCheckboxB2.setChecked(false);
				}
			}
		});
		
		emailCheckboxB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName2.isChecked()) {
					emailCheckboxB2.setChecked(false);
				}
			}
		});
		
		pushCheckboxB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName3.isChecked()) {
					pushCheckboxB3.setChecked(false);
				}
			}
		});
		smsCheckboxB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName3.isChecked()) {
					smsCheckboxB3.setChecked(false);
				}
			}
		});
		emailCheckboxB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (!businessName3.isChecked()) {
					emailCheckboxB3.setChecked(false);
				}
			}
		});
		
		businessName1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked) {
					businessName1.setChecked(true);
					businessName2.setChecked(false);
					businessName3.setChecked(false);
				}
			}
		});
		
		businessName2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if (isChecked){
				businessName1.setChecked(false);
				businessName2.setChecked(true);
				businessName3.setChecked(false);
			}
			}
		});
		
		businessName3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
				 if (isChecked){
				businessName1.setChecked(false);
				businessName2.setChecked(false);
				businessName3.setChecked(true);
			}
			}
		});
	}
	
}