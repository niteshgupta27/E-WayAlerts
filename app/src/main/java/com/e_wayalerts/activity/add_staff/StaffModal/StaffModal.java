
package com.e_wayalerts.activity.add_staff.StaffModal;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaffModal {

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
    @SerializedName("fld_email")
    @Expose
    private String fldEmail;
    @SerializedName("fld_n_in_app")
    @Expose
    private Integer fldNInApp;
    @SerializedName("fld_n_email")
    @Expose
    private Integer fldNEmail;
    @SerializedName("fld_n_in_sms")
    @Expose
    private Integer fldNInSms;
    @SerializedName("fld_role_id")
    @Expose
    private Integer fldRoleId;
    
    @SerializedName("fld_business_id")
    @Expose
    private Integer fldBusinessId;
    
    boolean checked;

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

    public String getFldEmail() {
        return fldEmail;
    }

    public void setFldEmail(String fldEmail) {
        this.fldEmail = fldEmail;
    }

    public Integer getFldNInApp() {
        return fldNInApp;
    }

    public void setFldNInApp(Integer fldNInApp) {
        this.fldNInApp = fldNInApp;
    }

    public Integer getFldNEmail() {
        return fldNEmail;
    }

    public void setFldNEmail(Integer fldNEmail) {
        this.fldNEmail = fldNEmail;
    }

    public Integer getFldNInSms() {
        return fldNInSms;
    }

    public void setFldNInSms(Integer fldNInSms) {
        this.fldNInSms = fldNInSms;
    }

    public Integer getFldRoleId() {
        return fldRoleId;
    }

    public void setFldRoleId(Integer fldRoleId) {
        this.fldRoleId = fldRoleId;
    }

    public Integer getFldBusinessId() {
        return fldBusinessId;
    }

    public void setFldBusinessId(Integer fldBusinessId) {
        this.fldBusinessId = fldBusinessId;
    }
    
    public boolean isChecked() {
        return checked;
    }
    
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    
}
