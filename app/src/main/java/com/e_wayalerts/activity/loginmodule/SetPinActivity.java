package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.e_wayalerts.R;

import androidx.appcompat.app.AppCompatActivity;

public class SetPinActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	TextView Submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_pin);
		
		mContext = this;
		Init();
		listner();
	}
	
	private void Init() {
		Submit = findViewById(R.id.submit_Btn);
	}
	
	private void listner() {
		Submit.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			
			case R.id.submit_Btn:
				Intent intent = new Intent(mContext, LoginActivity.class);
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