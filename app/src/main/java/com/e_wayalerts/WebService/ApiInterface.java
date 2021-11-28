package com.e_wayalerts.WebService;

import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.model.UserRollListModel;
import com.e_wayalerts.activity.dropdown.SateResponce;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.activity.loginmodule.Model.ResetPinModel;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;
import com.e_wayalerts.model.AddStaffModel;
import com.e_wayalerts.model.AddVehicleModel;
import com.e_wayalerts.model.VehicleListModel;
import com.e_wayalerts.model.VehicleTypeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {// For POST request
	
	@POST ("user/login")//your api link
	@FormUrlEncoded
	Call<LoginResponse> Login(@Field ("mobile") String mobile, @Field ("pin") String pin);
	
	@POST ("user/register")//your api link
	@FormUrlEncoded
	Call<LoginResponse> Signup(@Field ("first_name") String firstname,
	                           @Field ("last_name") String last_name,
	                           @Field ("mobile") String mobile);
	
	@POST ("user/reset-pin")//your api link
	@FormUrlEncoded
	Call<ResetPinModel> ResetPin(@Field ("mobile") String mobile);
	
	@POST ("user/reset/otp-verify")
	@FormUrlEncoded
	Call<VarifyOTPModel> OTPVerify(@Field ("user_id") String user_id, @Field ("otp") String otp);
	
	@POST ("user/pin-set")
	@FormUrlEncoded
	Call<VarifyOTPModel> ChangePin(@Field ("user_id") String user_id, @Field ("pin") String otp);
	
	@POST ("business/list")
	@FormUrlEncoded
	Call<BusinessListResponse> BusinessList(@Field ("user_id") String user_id,
	                                        @Field ("status") String status);
	
	@POST ("master/get-roles")
	@FormUrlEncoded
	Call<UserRollListModel> getUserRoll(@Field ("user_id") String user_id);
	
	@POST ("master/get-states")
	@FormUrlEncoded
	Call<SateResponce> StateList(@Field ("user_id") String user_id);
	
	@POST ("staff/add")
	@FormUrlEncoded
	Call<AddStaffModel> addstaff(@Field ("user_id") String user_id,
	                             @Field ("first_name") String first_name,
	                             @Field ("last_name") String last_name,
	                             @Field ("mobile") String mobile,
	                             @Field ("email") String email,
	                             @Field ("job_role") String job_role,
	                             @Field ("business_access") String business_access,
	                             @Field ("in_app") String in_app,
	                             @Field ("in_email") String in_email,
	                             @Field ("in_sms") String in_sms);
	
	@POST ("business/add")
	@FormUrlEncoded
	Call<VarifyOTPModel> AddBusiness(@Field ("user_id") String user_id,
	                                 @Field ("business_name") String business_name,
	                                 @Field ("gst") String gst,
	                                 @Field ("address1") String address1,
	                                 @Field ("address2") String address2,
	                                 @Field ("city") String city,
	                                 @Field ("pincode") String pincode,
	                                 @Field ("state_id") String state_id,
	                                 @Field ("owner_fname") String owner_fname,
	                                 @Field ("owner_lname") String owner_lname,
	                                 @Field ("owner_mobile") String owner_mobile,
	                                 @Field ("owner_email") String owner_email);
	
	@POST ("master/get-make")
	@FormUrlEncoded
	Call<VehicleListModel> getVehicleList(@Field ("user_id") String user_id);
	
	
	@POST ("master/get-types")
	@FormUrlEncoded
	Call<VehicleTypeModel> getVehicleType(@Field ("user_id") String user_id);
	
	@POST ("fleet/store")
	@FormUrlEncoded
	Call<AddVehicleModel> addVehicle(@Field ("user_id") String user_id,
	                                 @Field ("business_id") String business_id,
	                                 @Field ("fld_number") String fld_number,
	                                 @Field ("fld_make") String fld_make,
	                                 @Field ("fld_type") String fld_type);

}
