
package com.e_wayalerts.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GroupModal implements Serializable {

    @SerializedName("fld_uid")
    @Expose
    private Integer fldUid;
    @SerializedName("staff_id")
    @Expose
    private Integer staffId;
    @SerializedName("fld_grp_id")
    @Expose
    private Integer fldGrpId;
    @SerializedName("fld_business_id")
    @Expose
    private Integer fldBusinessId;
    @SerializedName("fld_group_name")
    @Expose
    private String fldGroupName;
    @SerializedName("fld_status")
    @Expose
    private Integer fldStatus;
    boolean checked;

    public Integer getFldUid() {
        return fldUid;
    }

    public void setFldUid(Integer fldUid) {
        this.fldUid = fldUid;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getFldGrpId() {
        return fldGrpId;
    }

    public void setFldGrpId(Integer fldGrpId) {
        this.fldGrpId = fldGrpId;
    }

    public Integer getFldBusinessId() {
        return fldBusinessId;
    }

    public void setFldBusinessId(Integer fldBusinessId) {
        this.fldBusinessId = fldBusinessId;
    }

    public String getFldGroupName() {
        return fldGroupName;
    }

    public void setFldGroupName(String fldGroupName) {
        this.fldGroupName = fldGroupName;
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
