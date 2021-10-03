package com.e_wayalerts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.fragment.BusinessListFragment;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Utility.loadFragment(MainActivity.this, new BusinessListFragment(),
				false, null);
	}
}