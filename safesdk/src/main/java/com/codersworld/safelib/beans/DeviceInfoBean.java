package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DeviceInfoBean implements Serializable {
    @SerializedName("success")
    int success;
    @SerializedName("returnds")
    ArrayList<InfoBean> returnds;

    public ArrayList<InfoBean> getReturnds() {
        return returnds;
    }

    public void setReturnds(ArrayList<InfoBean> returnds) {
        this.returnds = returnds;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public static class InfoBean implements Serializable {
        @SerializedName("LockId")
        String LockId;
        @SerializedName("LockCode")
        String LockCode;
        @SerializedName("MacId")
        String MacId;
        @SerializedName("Lockdata")
        String Lockdata;
        @SerializedName("VehicleNumber")
        String VehicleNumber;

        public String getLockId() {
            return LockId;
        }

        public void setLockId(String lockId) {
            LockId = lockId;
        }

        public String getLockCode() {
            return LockCode;
        }

        public void setLockCode(String lockCode) {
            LockCode = lockCode;
        }

        public String getMacId() {
            return MacId;
        }

        public void setMacId(String macId) {
            MacId = macId;
        }

        public String getLockdata() {
            return Lockdata;
        }

        public void setLockdata(String lockdata) {
            Lockdata = lockdata;
        }

        public String getVehicleNumber() {
            return VehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            VehicleNumber = vehicleNumber;
        }
    }
}
