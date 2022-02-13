package com.e_wayalerts.activity.loginmodule;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.MainActivity;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetPinActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	String UserID;
	
	TextView Submit;
	
	EditText SetPin1, SetPin2, SetPin3, SetPin4, ConfirmPin1, ConfirmPin2, ConfirmPin3,
			ConfirmPin4;
	
	ApiInterface apiInterface;
	String model = Build.MODEL;
	int osver ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_pin);
		osver =  Build.VERSION.SDK_INT;
		mContext = this;
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		Init();
		listner();
	}
	
	private void Init() {
		
		if (getIntent().getExtras() != null) {
			UserID = getIntent().getStringExtra("UserID");
		}
		
		Submit = findViewById(R.id.submit_Btn);
		SetPin1 = findViewById(R.id.setpin1);
		SetPin2 = findViewById(R.id.setpin2);
		SetPin3 = findViewById(R.id.setpin3);
		SetPin4 = findViewById(R.id.setpin4);
		ConfirmPin1 = findViewById(R.id.confirmpin1);
		ConfirmPin2 = findViewById(R.id.confirmpin2);
		ConfirmPin3 = findViewById(R.id.confirmpin3);
		ConfirmPin4 = findViewById(R.id.confirmpin4);
		
	}
	
	private void listner() {
		Submit.setOnClickListener(this);
		SetPin1.addTextChangedListener(new GenericTextWatcher(SetPin1));
		SetPin2.addTextChangedListener(new GenericTextWatcher(SetPin2));
		SetPin3.addTextChangedListener(new GenericTextWatcher(SetPin3));
		SetPin4.addTextChangedListener(new GenericTextWatcher(SetPin4));
		ConfirmPin1.addTextChangedListener(new GenericTextWatcher(ConfirmPin1));
		ConfirmPin2.addTextChangedListener(new GenericTextWatcher(ConfirmPin2));
		ConfirmPin3.addTextChangedListener(new GenericTextWatcher(ConfirmPin3));
		ConfirmPin4.addTextChangedListener(new GenericTextWatcher(ConfirmPin4));
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			
			case R.id.submit_Btn:
				
				String SetPin =
						SetPin1.getText().toString().trim() + SetPin2.getText().toString().trim() + SetPin3.getText().toString().trim() + SetPin4.getText().toString().trim();
				
				String ConfirmPin =
						ConfirmPin1.getText().toString().trim() + ConfirmPin2.getText().toString().trim() + ConfirmPin3.getText().toString().trim() + ConfirmPin4.getText().toString().trim();
				if (TextUtils.isEmpty(SetPin)) {
					Utility.ShowToast(SetPinActivity.this, getString(R.string.enter_pin_number));
				} else if (SetPin.length() != 4) {
					Utility.ShowToast(SetPinActivity.this,
							getString(R.string.enter_valid_pin_number));
					
				} else if (!SetPin.equals(ConfirmPin)) {
					Utility.ShowToast(mContext, getString(R.string.confirmpin_notequal));
				} else {
					PinChange(UserID,ConfirmPin);
				}
				//Intent intent = new Intent(mContext, LoginActivity.class);
				//startActivity(intent);
				break;
		}
		
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
	
	private void PinChange(String UserID, String ConfirmPin) {
		String Language= Utility.getSharedPreferences(mContext,Constant.Language);
		String FCmtoken= Utility.getSharedPreferences(mContext,Constant.FCmtoken);
		Call<LoginResponse> call = apiInterface.ChangePin(UserID, ConfirmPin,Language,"android",osver,model,FCmtoken);
		call.enqueue(new Callback<LoginResponse>() {
			@Override
			public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
				Log.e("TAG", "response 33: " + response.body().toString());
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						Utility.ShowToast(SetPinActivity.this,
								response.body().getMessage());
						Utility.setSharedPreference(mContext,
								Constant.User_id,String.valueOf(response.body().getData().getFldUid()));

						Utility.setSharedPreference(mContext,
								Constant.UserFirstName,response.body().getData().getFldFname());
						Utility.setSharedPreference(mContext,
								Constant.UserLastName,response.body().getData().getFldLname());
						Utility.setSharedPreference(mContext,
								Constant.UserMobile,response.body().getData().getFldMobile());
						Utility.setSharedPreference(mContext,
								Constant.Usertoken,response.body().getData().getUserToken());
						Utility.setSharedPreference(mContext,
								Constant.role_ID,response.body().getData().getrolid());
						Utility.ShowToast(SetPinActivity.this, response.body().getMessage());

						Intent intent = new Intent(mContext, MainActivity.class);
						startActivity(intent);
						finish();
					} else {
						Utility.ShowToast(mContext, response.body().getMessage());
					}
				} else {
					Utility.ShowToast(mContext, response.body().getMessage());
					Log.e("Error===>", response.errorBody().toString());
				}
			}
			
			@Override
			public void onFailure(Call<LoginResponse> call, Throwable t) {
				Toast.makeText(SetPinActivity.this, t.toString(),
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
				case R.id.setpin1:
					if (text.length() == 1)
						SetPin2.requestFocus();
					break;
				case R.id.setpin2:
					if (text.length() == 1)
						SetPin3.requestFocus();
					else if (text.length() == 0)
						SetPin1.requestFocus();
					break;
				case R.id.setpin3:
					if (text.length() == 1)
						SetPin4.requestFocus();
					else if (text.length() == 0)
						SetPin2.requestFocus();
					break;
				case R.id.setpin4:
					if (text.length() == 0)
						SetPin3.requestFocus();
					
					break;
				
				case R.id.confirmpin1:
					if (text.length() == 1)
						ConfirmPin2.requestFocus();
					break;
				case R.id.confirmpin2:
					if (text.length() == 1)
						ConfirmPin3.requestFocus();
					else if (text.length() == 0)
						ConfirmPin1.requestFocus();
					break;
				case R.id.confirmpin3:
					if (text.length() == 1)
						ConfirmPin4.requestFocus();
					else if (text.length() == 0)
						ConfirmPin2.requestFocus();
					break;
				case R.id.confirmpin4:
					if (text.length() == 0)
						ConfirmPin3.requestFocus();
					
					break;
				
				
			}
		}
	}
	
	
}