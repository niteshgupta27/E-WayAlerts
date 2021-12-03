package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FleetListModel {
	
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
	
	public class Datum {
		
		@SerializedName ("fld_flt_id") @Expose private Integer fldFltId;
		
		@SerializedName ("fld_user_id") @Expose private Integer fldUserId;
		
		@SerializedName ("fld_business_id") @Expose private Integer fldBusinessId;
		
		@SerializedName ("fld_number") @Expose private String fldNumber;
		
		@SerializedName ("fld_make") @Expose private String fldMake;
		
		@SerializedName ("fld_type") @Expose private String fldType;
		
		@SerializedName ("fld_status") @Expose private Integer fldStatus;
		
		public Integer getFldFltId() {
			return fldFltId;
		}
		
		public void setFldFltId(Integer fldFltId) {
			this.fldFltId = fldFltId;
		}
		
		public Integer getFldUserId() {
			return fldUserId;
		}
		
		public void setFldUserId(Integer fldUserId) {
			this.fldUserId = fldUserId;
		}
		
		public Integer getFldBusinessId() {
			return fldBusinessId;
		}
		
		public void setFldBusinessId(Integer fldBusinessId) {
			this.fldBusinessId = fldBusinessId;
		}
		
		public String getFldNumber() {
			return fldNumber;
		}
		
		public void setFldNumber(String fldNumber) {
			this.fldNumber = fldNumber;
		}
		
		public String getFldMake() {
			return fldMake;
		}
		
		public void setFldMake(String fldMake) {
			this.fldMake = fldMake;
		}
		
		public String getFldType() {
			return fldType;
		}
		
		public void setFldType(String fldType) {
			this.fldType = fldType;
		}
		
		public Integer getFldStatus() {
			return fldStatus;
		}
		
		public void setFldStatus(Integer fldStatus) {
			this.fldStatus = fldStatus;
		}
		
	}
}
