package com.e_wayalerts.activity.add_staff.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRollListModel {
	
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
		private List<Datum> data = null;
		
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
		
		public List<Datum> getData() {
			return data;
		}
		
		public void setData(List<Datum> data) {
			this.data = data;
		}
		public static class Datum {
			
			@SerializedName ("fld_role_id")
			@Expose
			private Integer fldRoleId;
			@SerializedName("fld_role_name")
			@Expose
			private String fldRoleName;
			@SerializedName("fld_role_code")
			@Expose
			private String fldRoleCode;
			
			public Integer getFldRoleId() {
				return fldRoleId;
			}
			
			public void setFldRoleId(Integer fldRoleId) {
				this.fldRoleId = fldRoleId;
			}
			
			public String getFldRoleName() {
				return fldRoleName;
			}
			
			public void setFldRoleName(String fldRoleName) {
				this.fldRoleName = fldRoleName;
			}
			
			public String getFldRoleCode() {
				return fldRoleCode;
			}
			
			public void setFldRoleCode(String fldRoleCode) {
				this.fldRoleCode = fldRoleCode;
			}
			
		}
	
}
