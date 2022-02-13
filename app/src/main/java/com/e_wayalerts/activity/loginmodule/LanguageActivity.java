package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.languagechange.LocaleHelper;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	ApiInterface apiInterface;
	
	RelativeLayout EnglishRelative, HindiRelative, PunjabiRelative, GujratiRelative, TamilRelative,
			TelguRelative, KanadaRelative, MalyalamRelative, EnglishImgRelative, HindiImgRelative,
			PunjabiImgRelative, GujratiImgRelative, TamilImgRelative, TelguImgRelative,
			KanadaImgRelative, MalyalamImgRelative;
	
	TextView EnglishTxt, HindiTxt, PunjabiTxt, GujratiTxt, TamilTxt, TelguTxt, KanadaTxt,
			MalyalamTxt, continue_Btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language);
		
		mContext = this;
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		Init();
		listner();
		String Language= Utility.getSharedPreferences(mContext,Constant.Language);
		if(Language.equals("")){
			Utility.setSharedPreference(mContext,
					Constant.Language,"1");
		}

	}
	
	private void Init() {
		EnglishRelative = findViewById(R.id.english_relative);
		HindiRelative = findViewById(R.id.hindi_relative);
		PunjabiRelative = findViewById(R.id.punjabi_relative);
		GujratiRelative = findViewById(R.id.gujrati_relative);
		TamilRelative = findViewById(R.id.tamil_relative);
		TelguRelative = findViewById(R.id.telgu_relative);
		KanadaRelative = findViewById(R.id.kanada_relative);
		MalyalamRelative = findViewById(R.id.malyalam_relative);
		EnglishTxt = findViewById(R.id.english_txt);
		HindiTxt = findViewById(R.id.hindi_txt);
		PunjabiTxt = findViewById(R.id.punjabi_txt);
		GujratiTxt = findViewById(R.id.gujrati_txt);
		TamilTxt = findViewById(R.id.tamil_txt);
		TelguTxt = findViewById(R.id.telgu_txt);
		KanadaTxt = findViewById(R.id.kanada_txt);
		MalyalamTxt = findViewById(R.id.malyalam_txt);
		EnglishImgRelative = findViewById(R.id.english_img_relative);
		HindiImgRelative = findViewById(R.id.hindi_img_relative);
		PunjabiImgRelative = findViewById(R.id.punjabi_img_relative);
		GujratiImgRelative = findViewById(R.id.gujrati_img_relative);
		TamilImgRelative = findViewById(R.id.tamil_img_relative);
		TelguImgRelative = findViewById(R.id.telgu_img_relative);
		KanadaImgRelative = findViewById(R.id.kanada_img_relative);
		MalyalamImgRelative = findViewById(R.id.malyalam_img_relative);
		continue_Btn = findViewById(R.id.continue_Btn);
	}
	
	private void listner() {
		EnglishRelative.setOnClickListener(this);
		HindiRelative.setOnClickListener(this);
		PunjabiRelative.setOnClickListener(this);
		GujratiRelative.setOnClickListener(this);
		TamilRelative.setOnClickListener(this);
		TelguRelative.setOnClickListener(this);
		KanadaRelative.setOnClickListener(this);
		MalyalamRelative.setOnClickListener(this);
		continue_Btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			
			case R.id.english_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.green));
				EnglishTxt.setTextColor(getResources().getColor(R.color.white_color));
				EnglishImgRelative.setVisibility(View.VISIBLE);
				updateViews("en");
				Utility.setSharedPreference(mContext,
						Constant.Language,"1");
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.hindi_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.green));
				HindiTxt.setTextColor(getResources().getColor(R.color.white_color));
				HindiImgRelative.setVisibility(View.VISIBLE);
				updateViews("hi");
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.punjabi_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.green));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.white_color));
				PunjabiImgRelative.setVisibility(View.VISIBLE);
				updateViews("pa");
				Utility.setSharedPreference(mContext,
						Constant.Language,"2");
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.gujrati_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.green));
				GujratiTxt.setTextColor(getResources().getColor(R.color.white_color));
				GujratiImgRelative.setVisibility(View.VISIBLE);
				updateViews("gu");
				Utility.setSharedPreference(mContext,
						Constant.Language,"3");
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.tamil_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.green));
				TamilTxt.setTextColor(getResources().getColor(R.color.white_color));
				TamilImgRelative.setVisibility(View.VISIBLE);
				updateViews("tm");
				Utility.setSharedPreference(mContext,
						Constant.Language,"4");
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.telgu_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.green));
				TelguTxt.setTextColor(getResources().getColor(R.color.white_color));
				TelguImgRelative.setVisibility(View.VISIBLE);
				Utility.setSharedPreference(mContext,
						Constant.Language,"5");
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.kanada_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.green));
				KanadaTxt.setTextColor(getResources().getColor(R.color.white_color));
				KanadaImgRelative.setVisibility(View.VISIBLE);
				Utility.setSharedPreference(mContext,
						Constant.Language,"6");
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.black));
				MalyalamImgRelative.setVisibility(View.GONE);
				
				break;
			
			case R.id.malyalam_relative:
				
				EnglishRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				EnglishTxt.setTextColor(getResources().getColor(R.color.black));
				EnglishImgRelative.setVisibility(View.GONE);
				
				HindiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				HindiTxt.setTextColor(getResources().getColor(R.color.black));
				HindiImgRelative.setVisibility(View.GONE);
				
				PunjabiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				PunjabiTxt.setTextColor(getResources().getColor(R.color.black));
				PunjabiImgRelative.setVisibility(View.GONE);
				
				GujratiRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				GujratiTxt.setTextColor(getResources().getColor(R.color.black));
				GujratiImgRelative.setVisibility(View.GONE);
				
				TamilRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TamilTxt.setTextColor(getResources().getColor(R.color.black));
				TamilImgRelative.setVisibility(View.GONE);
				
				TelguRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				TelguTxt.setTextColor(getResources().getColor(R.color.black));
				TelguImgRelative.setVisibility(View.GONE);
				
				KanadaRelative.setBackgroundColor(getResources().getColor(R.color.light_white));
				KanadaTxt.setTextColor(getResources().getColor(R.color.black));
				KanadaImgRelative.setVisibility(View.GONE);
				
				MalyalamRelative.setBackgroundColor(getResources().getColor(R.color.green));
				MalyalamTxt.setTextColor(getResources().getColor(R.color.white_color));
				MalyalamImgRelative.setVisibility(View.VISIBLE);
				Utility.setSharedPreference(mContext,
						Constant.Language,"7");
				break;
			
			case R.id.continue_Btn:
				
				Utility.setSharedPreference(mContext, Constant.LanguageSelected, "1");
				Intent intent = new Intent(mContext, LoginActivity.class);
				startActivity(intent);
				break;
		}
		
	}
	
	private void updateViews(String languageCode) {
		Context context = LocaleHelper.setLocale(mContext, languageCode);
		Resources resources = context.getResources();
		DisplayMetrics dm = resources.getDisplayMetrics();
		android.content.res.Configuration conf = resources.getConfiguration();
		conf.setLocale(new Locale(languageCode.toLowerCase())); // API 17+ only.
		resources.updateConfiguration(conf, dm);
	}
	
}