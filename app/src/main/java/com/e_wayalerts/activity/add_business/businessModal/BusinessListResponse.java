package com.e_wayalerts.activity.add_business.businessModal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BusinessListResponse {
	
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
	
	public static class Datum implements Serializable {
		
		boolean ischecked;
		
		@SerializedName ("fld_bid") @Expose private Integer fldBid;
		
		@SerializedName ("fld_business_name") @Expose private String fldBusinessName;
		
		@SerializedName ("fld_city") @Expose private String fldCity;
		
		@SerializedName ("fld_edate") @Expose private String fldEdate;
		
		@SerializedName ("fld_role_id") @Expose private Object fldRoleId;
		
		@SerializedName ("fld_role_name") @Expose private Object fldRoleName;
		
		@SerializedName ("fld_is_active") @Expose private Integer fld_is_active;
		@SerializedName ("fld_state_id") @Expose private Integer fld_state_id;
		@SerializedName ("fld_pincode") @Expose private Integer fld_pincode;
		@SerializedName ("fld_gst") @Expose private String fld_gst;
		@SerializedName ("fld_address1") @Expose private String fld_address1;
		@SerializedName ("fld_address2") @Expose private String fld_address2;
		@SerializedName ("fld_owner_fname") @Expose private String fld_owner_fname;
		@SerializedName ("fld_owner_lname") @Expose private String fld_owner_lname;
		@SerializedName ("fld_owner_mobile") @Expose private String fld_owner_mobile;
		@SerializedName ("fld_owner_email") @Expose private String fld_owner_email;
		public Integer getfld_state_id() {
			return fld_state_id;
		}

		public void setfld_state_id(Integer fld_is_active) {
			this.fld_state_id = fld_is_active;
		}
		public Integer getfld_pincode() {
			return fld_pincode;
		}

		public void setfld_pincode(Integer fld_is_active) {
			this.fld_pincode = fld_is_active;
		}

		public String getFld_owner_gst() {
			return fld_gst;
		}

		public void setFld_owner_gst(String fld_owner_email) {
			this.fld_gst = fld_owner_email;
		}
		public String getFld_owner_address1() {
			return fld_address1;
		}

		public void setFld_owner_address1(String fld_owner_email) {
			this.fld_address1 = fld_owner_email;
		}
		public String getFld_owner_address2() {
			return fld_address2;
		}

		public void setFld_owner_address2(String fld_owner_email) {
			this.fld_address2 = fld_owner_email;
		}
		public String getFld_owner_lname() {
			return fld_owner_lname;
		}

		public void setFld_owner_lname(String fld_owner_email) {
			this.fld_owner_lname = fld_owner_email;
		}
		public String getFld_owner_fname() {
			return fld_owner_fname;
		}

		public void setFld_owner_fname(String fld_owner_email) {
			this.fld_owner_fname = fld_owner_email;
		}
		public String getFld_owner_mobile() {
			return fld_owner_mobile;
		}

		public void setFld_owner_mobile(String fld_owner_email) {
			this.fld_owner_mobile = fld_owner_email;
		}
		public String getfld_owner_email() {
			return fld_owner_email;
		}

		public void setfld_owner_email(String fld_owner_email) {
			this.fld_owner_email = fld_owner_email;
		}


		public Integer getFldisactive() {
			return fld_is_active;
		}
		
		public void setFldisactive(Integer fld_is_active) {
			this.fld_is_active = fld_is_active;
		}
		
		public Integer getFldBid() {
			return fldBid;
		}
		
		public void setFldBid(Integer fldBid) {
			this.fldBid = fldBid;
		}
		
		public String getFldBusinessName() {
			return fldBusinessName;
		}
		
		public void setFldBusinessName(String fldBusinessName) {
			this.fldBusinessName = fldBusinessName;
		}
		
		public String getFldCity() {
			return fldCity;
		}
		
		public void setFldCity(String fldCity) {
			this.fldCity = fldCity;
		}
		
		public String getFldEdate() {
			return fldEdate;
		}
		
		public void setFldEdate(String fldEdate) {
			this.fldEdate = fldEdate;
		}
		
		public Object getFldRoleId() {
			return fldRoleId;
		}
		
		public void setFldRoleId(Object fldRoleId) {
			this.fldRoleId = fldRoleId;
		}
		
		public Object getFldRoleName() {
			return fldRoleName;
		}
		
		public void setFldRoleName(Object fldRoleName) {
			this.fldRoleName = fldRoleName;
		}
		
		public boolean isIschecked() {
			return ischecked;
		}
		
		public void setIschecked(boolean ischecked) {
			this.ischecked = ischecked;
		}
		
	}
}


