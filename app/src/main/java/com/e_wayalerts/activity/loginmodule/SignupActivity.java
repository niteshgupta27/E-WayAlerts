package com.e_wayalerts.activity.loginmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	TextView CreateAccount;
	LinearLayout LoginBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		mContext = this;
		
		Init();
		listner();
	}
	
	
	
	private void Init() {
		CreateAccount = findViewById(R.id.createAccount);
		LoginBtn = findViewById(R.id.login_linear);
	}
	private void listner() {
		LoginBtn.setOnClickListener(this);
		CreateAccount.setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		
		switch (view.getId()){
			case R.id.login_linear:
				onBackPressed();
				break;
				
			case R.id.createAccount:
				
				break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
		
	}
}