package com.e_wayalerts.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;

import com.e_wayalerts.MainActivity;
import com.e_wayalerts.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
	
	private long back_pressed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		changeScreen();
	}
	
	private void changeScreen() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
			
		}, 3000);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		int TIME_DELAY = 2000;
		if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
			super.onBackPressed();
			finish();
		} else {
			
			//      Support.ShowToast(this, getResources().getString(R.string.press_Again));
		}
		back_pressed = System.currentTimeMillis();
	}
}