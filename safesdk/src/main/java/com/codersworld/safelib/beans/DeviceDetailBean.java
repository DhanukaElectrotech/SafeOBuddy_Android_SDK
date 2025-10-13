package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceDetailBean implements Serializable {
    @SerializedName("lockId")
    String  lockId;
    @SerializedName("keyId")
    String  keyId;
    @SerializedName("lockMac")
    String  lockMac;
    @SerializedName("lockData")
    String  lockData;
    @SerializedName("remoteEnable")
    String  remoteEnable;

    public String getKeyId() {
        return keyId;
    }

    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getLockMac() {
        return lockMac;
    }

    public void setLockMac(String lockMac) {
        this.lockMac = lockMac;
    }

    public String getLockData() {
        return lockData;
    }

    public void setLockData(String lockData) {
        this.lockData = lockData;
    }

    public String getRemoteEnable() {
        return remoteEnable;
    }

    public void setRemoteEnable(String remoteEnable) {
        this.remoteEnable = remoteEnable;
    }
}
