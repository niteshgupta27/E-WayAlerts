package com.e_wayalerts.activity.loginmodule.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VarifyOTPModel {
	
	@SerializedName ("status")
	@Expose
	private Integer status;
	@SerializedName("success")
	@Expose
	private Boolean success;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("data")
	@Expose
	private VarifyOTPModel.Data data;


	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	public class Data {

//		@SerializedName ("user_id")
//		@Expose
//		private Integer userId;
		@SerializedName("mobile")
		@Expose
		private String mobile;
		@SerializedName("otp")
		@Expose
		private Integer otp;

		public Integer getotp() {
			return otp;
		}

		public void setotp(Integer userId) {
			this.otp = userId;
		}

//		public Integer getUserId() {
//			return userId;
//		}
//
//		public void setUserId(Integer userId) {
//			this.userId = userId;
//		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

	}
	
}
