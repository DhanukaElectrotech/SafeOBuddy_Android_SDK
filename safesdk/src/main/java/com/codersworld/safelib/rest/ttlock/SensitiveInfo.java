package com.codersworld.safelib.rest.ttlock;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SensitiveInfo implements Serializable {

    @SerializedName("LockData")
    String LockData;
    @SerializedName("MACID")
    String MACID;
    @SerializedName("LOCK_CODE")
    String LOCK_CODE;
    @SerializedName("LOCK_ID")
    String LOCK_ID;
    @SerializedName("btlockid")
    String btlockid;
    @SerializedName("btlockidval")
    String btlockidval;
   @SerializedName("GPSDeviceId")
    String GPSDeviceId;
   @SerializedName("GPSDeviceCode")
    String GPSDeviceCode;

    public String getGPSDeviceId() {
        return GPSDeviceId;
    }

    public void setGPSDeviceId(String GPSDeviceId) {
        this.GPSDeviceId = GPSDeviceId;
    }

    public String getGPSDeviceCode() {
        return GPSDeviceCode;
    }

    public void setGPSDeviceCode(String GPSDeviceCode) {
        this.GPSDeviceCode = GPSDeviceCode;
    }

    public String getLockData() {
        return LockData;
    }

    public void setLockData(String lockData) {
        LockData = lockData;
    }

    public String getMACID() {
        return MACID;
    }

    public void setMACID(String MACID) {
        this.MACID = MACID;
    }

    public String getLOCK_CODE() {
        return LOCK_CODE;
    }

    public void setLOCK_CODE(String LOCK_CODE) {
        this.LOCK_CODE = LOCK_CODE;
    }

    public String getLOCK_ID() {
        return LOCK_ID;
    }

    public void setLOCK_ID(String LOCK_ID) {
        this.LOCK_ID = LOCK_ID;
    }

    public String getBtlockid() {
        return btlockid;
    }

    public void setBtlockid(String btlockid) {
        this.btlockid = btlockid;
    }

    public String getBtlockidval() {
        return btlockidval;
    }

    public void setBtlockidval(String btlockidval) {
        this.btlockidval = btlockidval;
    }
}
