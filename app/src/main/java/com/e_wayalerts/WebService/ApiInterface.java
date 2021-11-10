package com.e_wayalerts.WebService;

import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.activity.loginmodule.Model.ResetPinModel;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {// For POST request
	

	@POST("user/login")//your api link
	@FormUrlEncoded
	Call<LoginResponse>Login(@Field("mobile") String mobile,
	                       @Field("pin") String pin);
	
	@POST("user/register")//your api link
	@FormUrlEncoded
	Call<LoginResponse>Signup(@Field("first_name") String firstname,
	                          @Field("last_name") String last_name,
	                          @Field("mobile") String mobile,
	                         @Field("pin") String pin);
	
	@POST("user/reset-pin")//your api link
	@FormUrlEncoded
	Call<ResetPinModel>ResetPin(@Field("mobile") String mobile);
	
	@POST("user/reset/otp-verify")
	@FormUrlEncoded
	Call<VarifyOTPModel> OTPVerify(@Field("user_id") String user_id,
	                               @Field("otp") String otp);
	
	
	@POST("user/pin-set")
	@FormUrlEncoded
	Call<VarifyOTPModel> ChangePin(@Field("user_id") String user_id,
	                               @Field("pin") String otp);
	
	@Headers ("token:EHGdXaxNPtDrUTmyAdbGV5EHgbSBLFXv")
	@POST("business/list")
	@FormUrlEncoded
	Call<BusinessListResponse> BusinessList(@Field("user_id") String user_id);
	
}
