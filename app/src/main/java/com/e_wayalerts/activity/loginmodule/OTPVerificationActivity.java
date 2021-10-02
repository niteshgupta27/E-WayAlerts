package com.e_wayalerts.activity.loginmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	TextView ResetPinBtn;
	LinearLayout login_linear;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otpverification);
		
		mContext = this;
		Init();
		lintner();
	}
	
	
	
	private void Init() {
		ResetPinBtn =  findViewById(R.id.resetpin_Btn);
		login_linear = findViewById(R.id.login_linear);
	}
	
	
	private void lintner() {
		ResetPinBtn.setOnClickListener(this);
		login_linear.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()){
			
			case R.id.resetpin_Btn:
				Intent intent  = new Intent(mContext,SetPinActivity.class);
				startActivity(intent);
				break;
			
			case R.id.login_linear:
				Intent intent1  = new Intent(mContext,LoginActivity.class);
				startActivity(intent1);
				break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
}