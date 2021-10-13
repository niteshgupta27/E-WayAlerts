package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e_wayalerts.MainActivity;
import com.e_wayalerts.R;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	LinearLayout Signup;
	
	RelativeLayout ResetPin;
	
	TextView LoginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mContext = this;
		Init();
		listner();
		
	}
	
	private void Init() {
		Signup = findViewById(R.id.signup_linear);
		ResetPin = findViewById(R.id.resetPin);
		LoginBtn = findViewById(R.id.login_Btn);
	}
	
	private void listner() {
		Signup.setOnClickListener(this);
		ResetPin.setOnClickListener(this);
		LoginBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			
			case R.id.signup_linear:
				Intent intent = new Intent(mContext, SignupActivity.class);
				startActivity(intent);
				break;
			
			case R.id.login_Btn:
				Intent intent1 = new Intent(mContext, MainActivity.class);
				startActivity(intent1);

				break;
			
			case R.id.resetPin:
				Intent intent2 = new Intent(mContext, ResetPinActivity.class);
				startActivity(intent2);
				
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}