package com.e_wayalerts.activity.smscredit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;

import androidx.fragment.app.Fragment;


public class SmsCreditFragment extends Fragment implements View.OnClickListener {
	
	LinearLayout onethousandSMS, twothousandSMS, threethousandSMS, fourthousandSMS,
			fivethousandSMS;
	
	TextView onethousandSmsTxt, twothousandSmsTxt, threethousandSmsTxt, fourthousandSmsTxt,
			fivethousandSmsTxt;
	
	View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_sms_credit, container, false);
		inIt();
		listner();
		return view;
	}
	
	private void inIt() {
		onethousandSMS = view.findViewById(R.id.onethousandSMS);
		twothousandSMS = view.findViewById(R.id.twothousandSMS);
		threethousandSMS = view.findViewById(R.id.threethousandSMS);
		fourthousandSMS = view.findViewById(R.id.fourthousandSMS);
		fivethousandSMS = view.findViewById(R.id.fivethousandSMS);
		onethousandSmsTxt = view.findViewById(R.id.onethousandSMSTxt);
		twothousandSmsTxt = view.findViewById(R.id.twothousandSMSTXT);
		threethousandSmsTxt = view.findViewById(R.id.threethousandSMSTXT);
		fourthousandSmsTxt = view.findViewById(R.id.fourthousandSMSTXT);
		fivethousandSmsTxt = view.findViewById(R.id.fivethousandSMSTXT);
	}
	
	private void listner() {
		onethousandSMS.setOnClickListener(this);
		twothousandSMS.setOnClickListener(this);
		threethousandSMS.setOnClickListener(this);
		fourthousandSMS.setOnClickListener(this);
		fivethousandSMS.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.onethousandSMS:
				selectCreaditSMS(1);
				break;
			case R.id.twothousandSMS:
				selectCreaditSMS(2);
				break;
			case R.id.threethousandSMS:
				selectCreaditSMS(3);
				break;
			case R.id.fourthousandSMS:
				selectCreaditSMS(4);
				break;
			case R.id.fivethousandSMS:
				selectCreaditSMS(5);
				break;
		}
	}
	
	private void selectCreaditSMS(int i) {
		switch (i) {
			case 1:
				onethousandSmsTxt.setBackgroundResource(R.drawable.green_roundedcorner);
				onethousandSmsTxt.setTextColor(getResources().getColor(R.color.white_color));
				twothousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				twothousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				threethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				threethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fourthousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fourthousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fivethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fivethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				break;
			case 2:
				onethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				onethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				twothousandSmsTxt.setBackgroundResource(R.drawable.green_roundedcorner);
				twothousandSmsTxt.setTextColor(getResources().getColor(R.color.white_color));
				threethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				threethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fourthousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fourthousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fivethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fivethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				break;
			case 3:
				onethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				onethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				twothousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				twothousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				threethousandSmsTxt.setBackgroundResource(R.drawable.green_roundedcorner);
				threethousandSmsTxt.setTextColor(getResources().getColor(R.color.white_color));
				fourthousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fourthousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fivethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fivethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				break;
			case 4:
				onethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				onethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				twothousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				twothousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				threethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				threethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fourthousandSmsTxt.setBackgroundResource(R.drawable.green_roundedcorner);
				fourthousandSmsTxt.setTextColor(getResources().getColor(R.color.white_color));
				fivethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fivethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				break;
			case 5:
				onethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				onethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				twothousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				twothousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				threethousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				threethousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fourthousandSmsTxt.setBackgroundResource(R.drawable.light_nanbg);
				fourthousandSmsTxt.setTextColor(getResources().getColor(R.color.black));
				fivethousandSmsTxt.setBackgroundResource(R.drawable.green_roundedcorner);
				fivethousandSmsTxt.setTextColor(getResources().getColor(R.color.white_color));
				break;
		}
	}
	
}