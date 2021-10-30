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
import com.e_wayalerts.activity.loginmodule.Model.ResetPinModel;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPinActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	LinearLayout LogIn;
	
	TextView RequestOtp;
	
	EditText Number1, Number2, Number3, Number4, Number5, Number6, Number7, Number8, Number9,
			Number10;
	ApiInterface apiInterface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pin);
		
		mContext = this;
		Init();
		listner();
		
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
	}
	
	private void Init() {
		LogIn = findViewById(R.id.login_linear);
		RequestOtp = findViewById(R.id.requestOtp_Btn);
		Number1 = findViewById(R.id.number1);
		Number2 = findViewById(R.id.number2);
		Number3 = findViewById(R.id.number3);
		Number4 = findViewById(R.id.number4);
		Number5 = findViewById(R.id.number5);
		Number6 = findViewById(R.id.number6);
		Number7 = findViewById(R.id.number7);
		Number8 = findViewById(R.id.number8);
		Number9 = findViewById(R.id.number9);
		Number10 = findViewById(R.id.number10);
	}
	
	private void listner() {
		LogIn.setOnClickListener(this);
		RequestOtp.setOnClickListener(this);
		Number1.addTextChangedListener(new GenericTextWatcher(Number1));
		Number2.addTextChangedListener(new GenericTextWatcher(Number2));
		Number3.addTextChangedListener(new GenericTextWatcher(Number3));
		Number4.addTextChangedListener(new GenericTextWatcher(Number4));
		Number5.addTextChangedListener(new GenericTextWatcher(Number5));
		Number6.addTextChangedListener(new GenericTextWatcher(Number6));
		Number7.addTextChangedListener(new GenericTextWatcher(Number7));
		Number8.addTextChangedListener(new GenericTextWatcher(Number8));
		Number9.addTextChangedListener(new GenericTextWatcher(Number9));
		Number10.addTextChangedListener(new GenericTextWatcher(Number10));
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			
			case R.id.login_linear:
				onBackPressed();
				break;
			
			case R.id.requestOtp_Btn:
				
				String mobilenumber =
						Number1.getText().toString().trim() + Number2.getText().toString().trim()
								+ Number3.getText().toString().trim() + Number4.getText().toString().trim
								() + Number5.getText().toString().trim() + Number6.getText().toString()
								.trim() + Number7.getText().toString().trim() + Number8.getText().toString
								().trim() + Number9.getText().toString().trim() + Number10.getText()
								.toString().trim();
				
				if (TextUtils.isEmpty(mobilenumber)) {
					Utility.ShowToast(ResetPinActivity.this,getString(R.string.please_enter_mobile_number));
				} else if (!Utility.isValidMobile(mobilenumber)) {
					Utility.ShowToast(ResetPinActivity.this,getString(
							R.string.enter_valid_mobile_number));
				}else {
					ResetPin(mobilenumber);
				}
				break;
			 
			
		}
		
	}
	
	private void ResetPin(String mobilenumber) {
		Call<ResetPinModel> call = apiInterface.ResetPin(mobilenumber);
		call.enqueue(new Callback<ResetPinModel>() {
			@Override
			public void onResponse(Call<ResetPinModel> call, Response<ResetPinModel> response) {
				Log.e("TAG", "response 33: " + response.body().toString());
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						Utility.ShowToast(ResetPinActivity.this, response.body().getMessage());
						
						
						Intent intent = new Intent(mContext,OTPVerificationActivity.class);
						intent.putExtra("UserID",String.valueOf(response.body().getData().getUserId()));
						intent.putExtra("MobileNumber",mobilenumber);
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
			public void onFailure(Call<ResetPinModel> call, Throwable t) {
				Toast.makeText(ResetPinActivity.this, t.toString(),
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
				case R.id.number1:
					if (text.length() == 1)
						Number2.requestFocus();
					break;
				case R.id.number2:
					if (text.length() == 1)
						Number3.requestFocus();
					else if (text.length() == 0)
						Number1.requestFocus();
					break;
				case R.id.number3:
					if (text.length() == 1)
						Number4.requestFocus();
					else if (text.length() == 0)
						Number2.requestFocus();
					break;
				case R.id.number4:
					if (text.length() == 1)
						Number5.requestFocus();
					else if (text.length() == 0)
						Number3.requestFocus();
					
					break;
				
				case R.id.number5:
					if (text.length() == 1)
						Number6.requestFocus();
					else if (text.length() == 0)
						Number4.requestFocus();
					
					break;
				case R.id.number6:
					if (text.length() == 1)
						Number7.requestFocus();
					else if (text.length() == 0)
						Number5.requestFocus();
					
					break;
				case R.id.number7:
					if (text.length() == 1)
						Number8.requestFocus();
					else if (text.length() == 0)
						Number6.requestFocus();
					
					break;
				case R.id.number8:
					if (text.length() == 1)
						Number9.requestFocus();
					else if (text.length() == 0)
						Number7.requestFocus();
					
					break;
				case R.id.number9:
					if (text.length() == 1)
						Number10.requestFocus();
					else if (text.length() == 0)
						Number8.requestFocus();
					
					break;
				case R.id.number10:
					if (text.length() == 0)
						Number9.requestFocus();
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