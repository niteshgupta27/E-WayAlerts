package com.e_wayalerts.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.loginmodule.LanguageActivity;
import com.e_wayalerts.activity.loginmodule.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {
	
	String languageSelected;
	
	Context mContext;
	
	private long back_pressed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mContext = this;
		languageSelected =
				Utility.getSharedPreferences(SplashActivity.this, Constant.LanguageSelected);
		changeScreen();
	}
	
	private void changeScreen() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				String userid = Utility.getSharedPreferences(mContext, Constant.User_id);
				if (TextUtils.isEmpty(languageSelected)) {
					Intent intent = new Intent(SplashActivity.this, LanguageActivity.class);
					startActivity(intent);
					finish();
				} else {
					if (!TextUtils.isEmpty(userid)) {
						Intent intent = new Intent(SplashActivity.this, MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
						startActivity(intent);
						finish();
					}
				}
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