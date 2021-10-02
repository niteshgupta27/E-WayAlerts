package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPinActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	LinearLayout LogIn;
	
	TextView RequestOtp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pin);
		
		mContext = this;
		Init();
		listner();
	}
	
	private void Init() {
		LogIn = findViewById(R.id.login_linear);
		RequestOtp = findViewById(R.id.requestOtp_Btn);
	}
	
	private void listner() {
		LogIn.setOnClickListener(this);
		RequestOtp.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			
			case R.id.login_linear:
				onBackPressed();
				break;
			
			case R.id.requestOtp_Btn:
				Intent intent = new Intent(mContext,OTPVerificationActivity.class);
				startActivity(intent);
				break;
			 
			
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
}