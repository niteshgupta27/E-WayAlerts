package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleListModel {
	
	@SerializedName ("status") @Expose private Integer status;
	
	@SerializedName ("success") @Expose private Boolean success;
	
	@SerializedName ("message") @Expose private String message;
	
	@SerializedName ("data") @Expose private List<Datum> data = null;
	
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
		
		@SerializedName ("fld_make_id") @Expose private Integer fldMakeId;
		
		@SerializedName ("fld_make_name") @Expose private String fldMakeName;
		
		public Integer getFldMakeId() {
			return fldMakeId;
		}
		
		public void setFldMakeId(Integer fldMakeId) {
			this.fldMakeId = fldMakeId;
		}
		
		public String getFldMakeName() {
			return fldMakeName;
		}
		
		public void setFldMakeName(String fldMakeName) {
			this.fldMakeName = fldMakeName;
		}
		
	}
	
	
}
