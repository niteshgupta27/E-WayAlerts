package com.e_wayalerts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EwaybillModal implements  Serializable{
    
    
    @SerializedName("fld_eb_id")
    @Expose
    private Integer fldEbId;
    @SerializedName("fld_business_id")
    @Expose
    private Integer fldBusinessId;
    @SerializedName("fld_user_id")
    @Expose
    private Integer fldUserId;
    @SerializedName("fld_type")
    @Expose
    private Integer fldType;
    @SerializedName("fld_interval")
    @Expose
    private Integer fldInterval;
    @SerializedName("fld_generated_by")
    @Expose
    private String fldGeneratedBy;
    @SerializedName("fld_valid_from")
    @Expose
    private String fldValidFrom;
    @SerializedName("fld_valid_until")
    @Expose
    private String fldValidUntil;
    @SerializedName("fld_vehicle_id")
    @Expose
    private Integer fldVehicleId;
    @SerializedName("fld_driver_name")
    @Expose
    private Integer fldDriverName;
    @SerializedName("fld_place_of_delivery")
    @Expose
    private String fldPlaceOfDelivery;
    @SerializedName("fld_alert_groups")
    @Expose
    private String fldAlertGroups;
    @SerializedName("fld_bill_number")
    @Expose
    private String fldBillNumber;
    @SerializedName("fld_place_of_origin")
    @Expose
    private String fldPlaceOfOrigin;
    @SerializedName("fld_status")
    @Expose
    private Integer fldStatus;
    
    public Integer getFldEbId() {
        return fldEbId;
    }
    
    public void setFldEbId(Integer fldEbId) {
        this.fldEbId = fldEbId;
    }
    
    public Integer getFldBusinessId() {
        return fldBusinessId;
    }
    
    public void setFldBusinessId(Integer fldBusinessId) {
        this.fldBusinessId = fldBusinessId;
    }
    
    public Integer getFldUserId() {
        return fldUserId;
    }
    
    public void setFldUserId(Integer fldUserId) {
        this.fldUserId = fldUserId;
    }
    
    public Integer getFldType() {
        return fldType;
    }
    
    public void setFldType(Integer fldType) {
        this.fldType = fldType;
    }
    
    public Integer getFldInterval() {
        return fldInterval;
    }
    
    public void setFldInterval(Integer fldInterval) {
        this.fldInterval = fldInterval;
    }
    
    public String getFldGeneratedBy() {
        return fldGeneratedBy;
    }
    
    public void setFldGeneratedBy(String fldGeneratedBy) {
        this.fldGeneratedBy = fldGeneratedBy;
    }
    
    public String getFldValidFrom() {
        return fldValidFrom;
    }
    
    public void setFldValidFrom(String fldValidFrom) {
        this.fldValidFrom = fldValidFrom;
    }
    
    public String getFldValidUntil() {
        return fldValidUntil;
    }
    
    public void setFldValidUntil(String fldValidUntil) {
        this.fldValidUntil = fldValidUntil;
    }
    
    public Integer getFldVehicleId() {
        return fldVehicleId;
    }
    
    public void setFldVehicleId(Integer fldVehicleId) {
        this.fldVehicleId = fldVehicleId;
    }
    
    public Integer getFldDriverName() {
        return fldDriverName;
    }
    
    public void setFldDriverName(Integer fldDriverName) {
        this.fldDriverName = fldDriverName;
    }
    
    public String getFldPlaceOfDelivery() {
        return fldPlaceOfDelivery;
    }
    
    public void setFldPlaceOfDelivery(String fldPlaceOfDelivery) {
        this.fldPlaceOfDelivery = fldPlaceOfDelivery;
    }
    
    public String getFldAlertGroups() {
        return fldAlertGroups;
    }
    
    public void setFldAlertGroups(String fldAlertGroups) {
        this.fldAlertGroups = fldAlertGroups;
    }
    
    public String getFldBillNumber() {
        return fldBillNumber;
    }
    
    public void setFldBillNumber(String fldBillNumber) {
        this.fldBillNumber = fldBillNumber;
    }
    
    public String getFldPlaceOfOrigin() {
        return fldPlaceOfOrigin;
    }
    
    public void setFldPlaceOfOrigin(String fldPlaceOfOrigin) {
        this.fldPlaceOfOrigin = fldPlaceOfOrigin;
    }
    
    public Integer getFldStatus() {
        return fldStatus;
    }
    
    public void setFldStatus(Integer fldStatus) {
        this.fldStatus = fldStatus;
    }

}