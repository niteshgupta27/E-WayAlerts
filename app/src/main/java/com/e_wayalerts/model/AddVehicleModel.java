package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddVehicleModel {
	
	@SerializedName ("status") @Expose private Integer status;
	
	@SerializedName ("success") @Expose private Boolean success;
	
	@SerializedName ("message") @Expose private String message;
	
	@SerializedName ("data") @Expose private Data data;
	
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
		
		@SerializedName ("fleet_id") @Expose private Integer fleetId;
		
		public Integer getFleetId() {
			return fleetId;
		}
		
		public void setFleetId(Integer fleetId) {
			this.fleetId = fleetId;
		}
		
		
	}
}
