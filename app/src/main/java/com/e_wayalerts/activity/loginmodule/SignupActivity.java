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
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.MainActivity;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
	
	Context mContext;
	
	TextView CreateAccount;
	
	LinearLayout LoginBtn;
	
	EditText FirstName_ext, LastName_ext, MobileNumber_ext, Pin1, Pin2, Pin3, Pin4;
	
	ApiInterface apiInterface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		mContext = this;
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		Init();
		listner();
	}
	
	private void Init() {
		CreateAccount = findViewById(R.id.createAccount);
		LoginBtn = findViewById(R.id.login_linear);
		FirstName_ext = findViewById(R.id.FirstName_ext);
		LastName_ext = findViewById(R.id.LastName_ext);
		MobileNumber_ext = findViewById(R.id.MobileNumber_ext);
		Pin1 = findViewById(R.id.pin1);
		Pin2 = findViewById(R.id.pin2);
		Pin3 = findViewById(R.id.pin3);
		Pin4 = findViewById(R.id.pin4);
	}
	
	private void listner() {
		LoginBtn.setOnClickListener(this);
		CreateAccount.setOnClickListener(this);
		
		Pin1.addTextChangedListener(new GenericTextWatcher(Pin1));
		Pin2.addTextChangedListener(new GenericTextWatcher(Pin2));
		Pin3.addTextChangedListener(new GenericTextWatcher(Pin3));
		Pin4.addTextChangedListener(new GenericTextWatcher(Pin4));
	}
	
	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			case R.id.login_linear:
				onBackPressed();
				break;
			
			case R.id.createAccount:
				String PinNumber =
						Pin1.getText().toString().trim() + Pin2.getText().toString().trim() + Pin3.getText().toString().trim() + Pin4.getText().toString().trim();
				
				if (FirstName_ext.getText().toString().isEmpty()) {
					Utility.ShowToast(SignupActivity.this,
							getString(R.string.please_enter_first_name));
				} else if (LastName_ext.getText().toString().isEmpty()) {
					Utility.ShowToast(SignupActivity.this,
							getString(R.string.please_enter_last_name));
				} else if (MobileNumber_ext.getText().toString().isEmpty()) {
					Utility.ShowToast(SignupActivity.this,
							getString(R.string.please_enter_mobile_number));
					
				} else if (!Utility.isValidMobile(MobileNumber_ext.getText().toString())) {
					Utility.ShowToast(SignupActivity.this,
							getString(R.string.enter_valid_mobile_number));
					
				} else if (TextUtils.isEmpty(PinNumber)) {
					Utility.ShowToast(SignupActivity.this, getString(R.string.enter_pin_number));
				} else if (PinNumber.length() != 4) {
					Utility.ShowToast(SignupActivity.this,
							getString(R.string.enter_valid_pin_number));
					
				} else {
					Signup(FirstName_ext.getText().toString().trim(),
							LastName_ext.getText().toString().trim(),
							MobileNumber_ext.getText().toString().trim(),
							PinNumber);
				}
				
				
				break;
		}
		
	}
	
	private void Signup(String firstname, String lastname, String mobilenumber, String pinNumber) {
		Call<LoginResponse> call = apiInterface.Signup(firstname,lastname,mobilenumber, pinNumber);
		call.enqueue(new Callback<LoginResponse>() {
			@Override
			public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
				Log.e("TAG", "response 33: " + response.body().toString());
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						Utility.ShowToast(SignupActivity.this, response.body().getMessage());
						onBackPressed();
					}else {
						Utility.ShowToast(mContext,getString(R.string.mobile_number_already_registerd));
					}
				} else {
					Utility.ShowToast(mContext,getString(R.string.mobile_number_already_registerd));
					Log.e("Error===>", response.errorBody().toString());
				}
			}
			
			@Override
			public void onFailure(Call<LoginResponse> call, Throwable t) {
				Toast.makeText(SignupActivity.this, t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
		
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
				case R.id.pin1:
					if (text.length() == 1)
						Pin2.requestFocus();
					break;
				
				case R.id.pin2:
					if (text.length() == 1)
						Pin3.requestFocus();
					else if (text.length() == 0)
						Pin1.requestFocus();
					break;
				
				case R.id.pin3:
					if (text.length() == 1)
						Pin4.requestFocus();
					else if (text.length() == 0)
						Pin2.requestFocus();
					break;
				
				case R.id.pin4:
					if (text.length() == 0)
						Pin3.requestFocus();
					break;
			}
		}
	}
	
	
}