package com.e_wayalerts.activity.loginmodule.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
	
	@SerializedName("status")
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
	private Datum data;
	
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
	
	public Datum getData() {
		return data;
	}
	
	public void setData(Datum data) {
		this.data = data;
	}

	public class Datum {
		
		@SerializedName("fld_uid")
		@Expose
		private Integer fldUid;
		@SerializedName("fld_fname")
		@Expose
		private String fldFname;
		@SerializedName("fld_lname")
		@Expose
		private String fldLname;
		@SerializedName("fld_mobile")
		@Expose
		private String fldMobile;
		
		@SerializedName("token")
		@Expose
		private String UserToken;
		@SerializedName("role_id")
		@Expose
		private String rolid;
		public String getrolid() {
			return rolid;
		}

		public void setrolid(String userToken) {
			rolid = userToken;
		}
		public String getUserToken() {
			return UserToken;
		}
		
		public void setUserToken(String userToken) {
			UserToken = userToken;
		}
		
		public Integer getFldUid() {
			return fldUid;
		}
		
		public void setFldUid(Integer fldUid) {
			this.fldUid = fldUid;
		}
		
		public String getFldFname() {
			return fldFname;
		}
		
		public void setFldFname(String fldFname) {
			this.fldFname = fldFname;
		}
		
		public String getFldLname() {
			return fldLname;
		}
		
		public void setFldLname(String fldLname) {
			this.fldLname = fldLname;
		}
		
		public String getFldMobile() {
			return fldMobile;
		}
		
		public void setFldMobile(String fldMobile) {
			this.fldMobile = fldMobile;
		}
		
	}
}
