
package com.e_wayalerts.activity.add_business.businessModal;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessListResponse {

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
    public class Datum {

        @SerializedName("fld_bid")
        @Expose
        private Integer fldBid;
        @SerializedName("fld_business_name")
        @Expose
        private String fldBusinessName;
        @SerializedName("fld_city")
        @Expose
        private String fldCity;
        @SerializedName("fld_edate")
        @Expose
        private String fldEdate;
        @SerializedName("fld_role_id")
        @Expose
        private Object fldRoleId;
        @SerializedName("fld_role_name")
        @Expose
        private Object fldRoleName;

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

    }
}


