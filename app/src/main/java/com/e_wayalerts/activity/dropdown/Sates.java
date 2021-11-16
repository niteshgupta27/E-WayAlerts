
package com.e_wayalerts.activity.dropdown;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sates {

    @SerializedName("fld_state_id")
    @Expose
    private Integer fldStateId;
    @SerializedName("fld_state_name")
    @Expose
    private String fldStateName;
    @SerializedName("fld_state_abbr")
    @Expose
    private String fldStateAbbr;

    public Integer getFldStateId() {
        return fldStateId;
    }

    public void setFldStateId(Integer fldStateId) {
        this.fldStateId = fldStateId;
    }

    public String getFldStateName() {
        return fldStateName;
    }

    public void setFldStateName(String fldStateName) {
        this.fldStateName = fldStateName;
    }

    public String getFldStateAbbr() {
        return fldStateAbbr;
    }

    public void setFldStateAbbr(String fldStateAbbr) {
        this.fldStateAbbr = fldStateAbbr;
    }

}
