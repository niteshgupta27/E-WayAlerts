package com.e_wayalerts.WebService;

import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffRecponce;
import com.e_wayalerts.activity.add_staff.model.UserRollListModel;
import com.e_wayalerts.activity.dropdown.SateResponce;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.activity.loginmodule.Model.ResetPinModel;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;
import com.e_wayalerts.model.AddEwayBillModel;
import com.e_wayalerts.model.AddGroupModel;
import com.e_wayalerts.model.AddStaffModel;
import com.e_wayalerts.model.AddVehicleModel;
import com.e_wayalerts.model.EwayBillRecponce;
import com.e_wayalerts.model.FleetListModel;
import com.e_wayalerts.model.GroupListRecponce;
import com.e_wayalerts.model.StatusResponce;
import com.e_wayalerts.model.VehicleListModel;
import com.e_wayalerts.model.VehicleTypeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {// For POST request
	
	@POST ("user/login")//your api link
	@FormUrlEncoded
	Call<LoginResponse> Login(@Field ("mobile") String mobile, @Field ("pin") String pin, @Field ("language_id") String lang, @Field("os") String os, @Field("version") int version, @Field("model") String model, @Field("fcm_token") String fcm_token);
	
	@POST ("user/register")//your api link
	@FormUrlEncoded
	Call<LoginResponse> Signup(@Field ("first_name") String firstname,
	                           @Field ("last_name") String last_name,
	                           @Field ("mobile") String mobile,@Field ("language_id") String lang, @Field("os") String os, @Field("version") int version, @Field("model") String model, @Field("fcm_token") String fcm_token,@Field("email") String email);
	
	@POST ("user/reset-pin")//your api link
	@FormUrlEncoded
	Call<ResetPinModel> ResetPin(@Field ("mobile") String mobile);

	@POST ("user/otp-send")
	@FormUrlEncoded
	Call<VarifyOTPModel> OTPresend(@Field ("mobile") String user_id);

	@POST ("user/reset/otp-verify")
	@FormUrlEncoded
	Call<VarifyOTPModel> OTPVerify(@Field ("user_id") String user_id, @Field ("otp") String otp);
	
	@POST ("user/pin-set")
	@FormUrlEncoded
	Call<LoginResponse> ChangePin(@Field ("user_id") String user_id, @Field ("pin") String otp, @Field ("language_id") String lang, @Field("os") String os, @Field("version") int version, @Field("model") String model, @Field("fcm_token") String fcm_token);
	@POST ("user/check")
	@FormUrlEncoded
	Call<BusinessListResponse> ChechkUser(@Field ("user_id") String user_id,
											@Field ("status") String status);
	@POST ("business/list")
	@FormUrlEncoded
	Call<BusinessListResponse> BusinessList(@Field ("user_id") String user_id,
	                                        @Field ("status") String status);
	@POST ("business/delete")
	@FormUrlEncoded
	Call<BusinessListResponse> Businessdelete(@Field ("business_id") String user_id);

	@POST ("business/change-status")
	@FormUrlEncoded
	Call<StatusResponce> Businessstatus(@Field ("user_id") String user_id,@Field ("business_id") String business_id,@Field ("status") String status);

	@POST ("staff/change-status")
	@FormUrlEncoded
	Call<StatusResponce> Staffstatus(@Field ("user_id") String user_id, @Field ("staff_id") String business_id, @Field ("status") String status);

	@POST ("fleet/change-status")
	@FormUrlEncoded
	Call<StatusResponce> Fleetstatus(@Field ("user_id") String user_id,@Field ("fleet_id") String business_id,@Field ("status") String status);

	@POST ("alert-group/change-status")
	@FormUrlEncoded
	Call<StatusResponce> AlertGroupstatus(@Field ("user_id") String user_id,@Field ("alert_id") String business_id,@Field ("status") String status);

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
	                             @Field ("mobile") String mobile, @Field ("email") String email,
	                             @Field ("job_role") String job_role,
	                             @Field ("business_access") String business_access,
	                             @Field ("in_app") String in_app,
	                             @Field ("in_email") String in_email,
	                             @Field ("in_sms") String in_sms);

	@POST ("staff/update")
	@FormUrlEncoded
	Call<AddStaffModel> updatestaff(@Field ("user_id") String user_id,
								 @Field ("first_name") String first_name,
								 @Field ("last_name") String last_name,
								 @Field ("mobile") String mobile, @Field ("email") String email,
								 @Field ("job_role") String job_role,
								 @Field ("business_access") String business_access,
								 @Field ("in_app") String in_app,
								 @Field ("in_email") String in_email,
								 @Field ("in_sms") String in_sms,
	@Field("staff_id") String staff_id);

	@POST ("staff/delete")
	@FormUrlEncoded
	Call<AddStaffModel> Staffdelete(@Field ("user_id") String user_id,@Field("staff_id") String staff_id);


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
	@POST ("business/update")
	@FormUrlEncoded
	Call<VarifyOTPModel> UpdateBusiness(@Field ("user_id") String user_id,
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
									 @Field ("owner_email") String owner_email,
										@Field("business_id") String business_id);
	
	@POST ("master/get-make")
	@FormUrlEncoded
	Call<VehicleListModel> getVehicleList(@Field ("user_id") String user_id);
	
	@POST ("master/get-types")
	@FormUrlEncoded
	Call<VehicleTypeModel> getVehicleType(@Field ("user_id") String user_id);
	
	@POST ("fleet/store")
	@FormUrlEncoded
	Call<AddVehicleModel> addVehicle(@Field ("user_id") String user_id,
	                                 @Field ("business_id") int business_id,
	                                 @Field ("fld_number") String fld_number,
	                                 @Field ("fld_make") String fld_make,
	                                 @Field ("fld_type") String fld_type);
	@POST ("fleet/update")
	@FormUrlEncoded
	Call<AddVehicleModel> updateVehicle(@Field ("user_id") String user_id,
									 @Field ("business_id") int business_id,
									 @Field ("fld_number") String fld_number,
									 @Field ("fld_make") String fld_make,
									 @Field ("fld_type") String fld_type,
										@Field("fleet_id") int fleet_id);
	@POST ("staff/list")
	@FormUrlEncoded
	Call<StaffRecponce> StaffList(@Field ("user_id") String userid,
	                              @Field ("role_id") String role_id,
	                              @Field ("business_id") String selectedbusinessID);
	
	@POST ("alert-group/store")
	@FormUrlEncoded
	Call<AddGroupModel> addGroup(@Field ("user_id") String userid,
	                             @Field ("group_name") String role_id,
	                             @Field ("business_id") String selectedbusinessID,
	                             @Field ("u_ids") String UID);

	@POST ("alert-group/update")
	@FormUrlEncoded
	Call<AddGroupModel> updateGroup(@Field ("user_id") String userid,
								 @Field ("group_name") String role_id,
								 @Field ("business_id") String selectedbusinessID,
								 @Field ("u_ids") String UID,@Field("group_id") String group_id);

	@POST ("fleet/list")
	@FormUrlEncoded
	Call<FleetListModel> getFleetList(@Field ("user_id") String user_id,
	                                  @Field ("business_id") String business_id);
	@POST ("staff/delete")
	@FormUrlEncoded
	Call<AddStaffModel> Fleetdelete(@Field ("user_id") String user_id,@Field("fleet_id") String fleet_id);

	@POST ("eway-bills/single/store")
	@FormUrlEncoded
	Call<AddEwayBillModel> addEwayBill(@Field ("user_id") String userid,
									   @Field ("fld_type") String billType,
									   @Field ("user_id") String ewayBillNumber,
									   @Field ("fld_generated_by") String generatedBy,
									   @Field ("user_id") String origin,
									   @Field ("user_id") String delivery,
									   @Field ("user_id") String validFormDate,
									   @Field ("user_id") String validUntilDate,
									   @Field ("user_id") String businessID,
									   @Field ("user_id") String vehicleNumberID,
									   @Field ("user_id") String driverID);

	@POST ("eway-bills/single/list")
	@FormUrlEncoded
	Call<EwayBillRecponce> ListEwayBill(@Field ("user_id") String userid
									   );
	@POST ("eway-bills/single/delete")
	@FormUrlEncoded
	Call<AddEwayBillModel> ewaydelete(@Field ("user_id") String user_id,@Field("eway_bill_id") String group_id);
	@POST ("eway-bills/single/change-status")
	@FormUrlEncoded
	Call<StatusResponce> Ewaystatus(@Field ("user_id") String user_id,@Field ("eway_bill_id") String business_id,@Field ("status") String status);
	@POST ("alert-group/list")
	@FormUrlEncoded
	Call<GroupListRecponce> GroupList(@Field ("user_id") String userid,
									  @Field ("status") String role_id,
									  @Field ("business_id") String selectedbusinessID);

	@POST ("staff/delete")
	@FormUrlEncoded
	Call<AddGroupModel> groupdelete(@Field ("user_id") String user_id,@Field("group_id") String group_id);

}
