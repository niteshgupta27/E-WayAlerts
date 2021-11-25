
package com.e_wayalerts.activity.add_staff.StaffModal;
import java.util.List;


import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaffRecponce {

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
    private List<StaffModal> data = null;

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

    public List<StaffModal> getData() {
        return data;
    }

    public void setData(List<StaffModal> data) {
        this.data = data;
    }

}
