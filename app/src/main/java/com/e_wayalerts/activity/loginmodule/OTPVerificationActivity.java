package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	TextView ResetPinBtn;
	
	LinearLayout login_linear,resend_linear;
	
	String MobileNumber,UserID;
	
	TextView MobileNumberTxt;
	
	EditText Pin1, Pin2, Pin3, Pin4;
	
	ApiInterface apiInterface;
	String reciveOtp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otpverification);
		
		mContext = this;
		apiInterface = ApiClient.getClient().create(ApiInterface.class);

		Init();
		lintner();
	}
	
	private void Init() {
		ResetPinBtn = findViewById(R.id.resetpin_Btn);
		login_linear = findViewById(R.id.login_linear);
		MobileNumberTxt = findViewById(R.id.mobile_number_txt);
		resend_linear = findViewById(R.id.resend_linear);
		Pin1 = findViewById(R.id.Pin1);
		Pin2 = findViewById(R.id.Pin2);
		Pin3 = findViewById(R.id.Pin3);
		Pin4 = findViewById(R.id.Pin4);
		
		if (getIntent().getExtras() != null) {
			MobileNumber = getIntent().getStringExtra("MobileNumber");
			UserID = getIntent().getStringExtra("UserID");
			reciveOtp = getIntent().getStringExtra("otp");
			MobileNumberTxt.setText(MobileNumber);
			
		}
	}
	
	private void lintner() {
		ResetPinBtn.setOnClickListener(this);
		login_linear.setOnClickListener(this);
		resend_linear.setOnClickListener(this);
		Pin1.addTextChangedListener(new GenericTextWatcher(Pin1));
		Pin2.addTextChangedListener(new GenericTextWatcher(Pin2));
		Pin3.addTextChangedListener(new GenericTextWatcher(Pin3));
		Pin4.addTextChangedListener(new GenericTextWatcher(Pin4));
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			
			case R.id.resetpin_Btn:
				String OTP =
						Pin1.getText().toString().trim() + Pin2.getText().toString().trim() + Pin3.getText().toString().trim() + Pin4.getText().toString().trim();
				if (TextUtils.isEmpty(OTP)) {
					Utility.ShowToast(OTPVerificationActivity.this, getString(R.string.enter_pin_number));
				} else if (OTP.length() != 4) {
					Utility.ShowToast(OTPVerificationActivity.this,
							getString(R.string.enter_valid_pin_number));
					
				}
				else  if (!reciveOtp.equals(OTP)){
					Utility.ShowToast(OTPVerificationActivity.this,
							getString(R.string.enter_valid_pin_number));
				}
				else {
					//RequestOTP(UserID,OTP);
					Intent intent = new Intent(mContext, SetPinActivity.class);
					intent.putExtra("UserID",UserID);
					startActivity(intent);
				}
				
				
				break;
			
			case R.id.login_linear:
				Intent intent1 = new Intent(mContext, LoginActivity.class);
				startActivity(intent1);
				break;
			case  R.id.resend_linear:
				ResendOTP(MobileNumber);
				break;
		}
		
	}
	private void ResendOTP(String mobile) {
		Call<VarifyOTPModel> call = apiInterface.OTPresend(mobile);
		call.enqueue(new Callback<VarifyOTPModel>() {
			@Override
			public void onResponse(Call<VarifyOTPModel> call, Response<VarifyOTPModel> response) {
				Log.e("TAG", "response 33: " + response.body().toString());

				if (response.isSuccessful()) {

					if (String.valueOf(response.body().getStatus()).equals("200")) {
reciveOtp = String.valueOf(response.body().getData().getotp());
//						Utility.ShowToast(OTPVerificationActivity.this, response.body().getMessage());
//						Intent intent = new Intent(mContext, SetPinActivity.class);
//						intent.putExtra("UserID",UserID);
//						startActivity(intent);
					}else {
						Utility.ShowToast(mContext,response.body().getMessage());
					}
				} else {
					Utility.ShowToast(mContext,response.body().getMessage());
					Log.e("Error===>", response.errorBody().toString());
				}
			}

			@Override
			public void onFailure(Call<VarifyOTPModel> call, Throwable t) {
				Toast.makeText(OTPVerificationActivity.this, t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

			}
		});
	}
	private void RequestOTP(String UserID, String OTP) {
		Call<VarifyOTPModel> call = apiInterface.OTPVerify(UserID, OTP);
		call.enqueue(new Callback<VarifyOTPModel>() {
			@Override
			public void onResponse(Call<VarifyOTPModel> call, Response<VarifyOTPModel> response) {
				Log.e("TAG", "response 33: " + response.body().toString());
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						Utility.ShowToast(OTPVerificationActivity.this, response.body().getMessage());
						Intent intent = new Intent(mContext, SetPinActivity.class);
						intent.putExtra("UserID",UserID);
						startActivity(intent);
					}else {
						Utility.ShowToast(mContext,response.body().getMessage());
					}
				} else {
					Utility.ShowToast(mContext,response.body().getMessage());
					Log.e("Error===>", response.errorBody().toString());
				}
			}
			
			@Override
			public void onFailure(Call<VarifyOTPModel> call, Throwable t) {
				Toast.makeText(OTPVerificationActivity.this, t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
	}
	
	
	private class GenericTextWatcher implements TextWatcher {
		
		private final View view;
		
		private GenericTextWatcher(View view) {
			this.view = view;
		}
		
		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}
		
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
		}
		
		public void afterTextChanged(Editable editable) {
			String text = editable.toString();
			switch (view.getId()) {
				case R.id.Pin1:
					if (text.length() == 1)
						Pin2.requestFocus();
					break;
				
				case R.id.Pin2:
					if (text.length() == 1)
						Pin3.requestFocus();
					else if (text.length() == 0)
						Pin1.requestFocus();
					break;
				
				case R.id.Pin3:
					if (text.length() == 1)
						Pin4.requestFocus();
					else if (text.length() == 0)
						Pin2.requestFocus();
					break;
				
				case R.id.Pin4:
					if (text.length() == 0)
						Pin3.requestFocus();
					break;
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
}