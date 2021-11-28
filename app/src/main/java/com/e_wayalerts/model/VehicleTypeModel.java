package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehicleTypeModel {
	
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
		
		@SerializedName ("fld_type_id") @Expose private Integer fldTypeId;
		
		@SerializedName ("fld_type_name") @Expose private String fldTypeName;
		
		public Integer getFldTypeId() {
			return fldTypeId;
		}
		
		public void setFldTypeId(Integer fldTypeId) {
			this.fldTypeId = fldTypeId;
		}
		
		public String getFldTypeName() {
			return fldTypeName;
		}
		
		public void setFldTypeName(String fldTypeName) {
			this.fldTypeName = fldTypeName;
		}
		
	}
	
}
