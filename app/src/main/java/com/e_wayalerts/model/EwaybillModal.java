package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EwaybillModal implements  Serializable{


    @SerializedName("fld_eb_id")
    @Expose
    private Integer fld_eb_id;
    @SerializedName("fld_user_id")
    @Expose
    private Integer staffId;
    @SerializedName("fld_type")
    @Expose
    private Integer fld_type;
    @SerializedName("fld_business_id")
    @Expose
    private Integer fldBusinessId;
    @SerializedName("fld_interval")
    @Expose
    private Integer fld_interval;
    @SerializedName("fld_status")
    @Expose
    private Integer fldStatus;
    @SerializedName("fld_generated_by")
    @Expose
    private String fld_generated_by;

    @SerializedName("fld_valid_from")
    @Expose
    private String fld_valid_from;
    @SerializedName("fld_valid_until")
    @Expose
    private String fld_valid_until;
    @SerializedName("fld_vehicle_id")
    @Expose
    private Integer fld_vehicle_id;

    @SerializedName("fld_driver_name")
    @Expose
    private Integer fld_driver_name;
    @SerializedName("fld_place_of_delivery")
    @Expose
    private String fld_place_of_delivery;
    @SerializedName("fld_alert_groups")
    @Expose
    private String fld_alert_groups;
    @SerializedName("fld_bill_number")
    @Expose
    private String fld_bill_number;
    @SerializedName("fld_place_of_origin")
    @Expose
    private String fld_place_of_origin;

    boolean checked;

    public Integer getfld_eb_id() {
        return fld_eb_id;
    }

    public void setfld_eb_id(Integer fldUid) {
        this.fld_eb_id = fldUid;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getfld_type() {
        return fld_type;
    }

    public void setfld_type(Integer fldGrpId) {
        this.fld_type = fldGrpId;
    }

    public Integer getFldBusinessId() {
        return fldBusinessId;
    }

    public void setFldBusinessId(Integer fldBusinessId) {
        this.fldBusinessId = fldBusinessId;
    }

    public Integer getfld_interval() {
        return fld_interval;
    }

    public void setfld_interval(Integer fldGroupName) {
        this.fld_interval = fldGroupName;
    }

    public Integer getFldStatus() {
        return fldStatus;
    }

    public void setFldStatus(Integer fldStatus) {
        this.fldStatus = fldStatus;
    }
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


}