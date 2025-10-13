package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class KeyListObj implements Serializable {

@SerializedName("total")
int total;
    @SerializedName("pages")
    int pages;
    @SerializedName("pageNo")
    int pageNo;
    @SerializedName("pageSize")
    int pageSize;

    ArrayList<KeyObj> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<KeyObj> getList() {
        return list;
    }

    public void setList(ArrayList<KeyObj> list) {
        this.list = list;
    }

    public static class KeyObj implements Serializable{
        @SerializedName("keyId")
        int keyId;
        @SerializedName("lockId")
        int lockId;
        @SerializedName("userType")
        String userType;
        @SerializedName("keyStatus")
        String keyStatus;
        @SerializedName("lockName")
        String lockName;
        @SerializedName("lockAlias")
        String lockAlias;
        @SerializedName("lockData")
        String lockData;
        @SerializedName("lockKey")
        String lockKey;
        @SerializedName("lockMac")
        String lockMac;
        @SerializedName("lockFlagPos")
        int lockFlagPos;
        @SerializedName("adminPwd")
        String adminPwd;
        @SerializedName("noKeyPwd")
        String noKeyPwd;
        @SerializedName("deletePwd")
        String deletePwd;
        @SerializedName("electricQuantity")
        int electricQuantity;
        @SerializedName("aesKeyStr")
        String aesKeyStr;
        @SerializedName("lockVersion")
        LockVersionObj lockVersion;
        @SerializedName("startDate")
        long startDate;
        @SerializedName("endDate")
        long endDate;
        @SerializedName("timezoneRawOffset")
        long timezoneRawOffset;
        @SerializedName("remarks")
        String remarks;
        @SerializedName("keyRight")
        int keyRight;
        @SerializedName("keyboardPwdVersion")
        int keyboardPwdVersion;
        @SerializedName("specialValue")
        int specialValue;
        @SerializedName("remoteEnable")
        int remoteEnable;

        public int getKeyId() {
            return keyId;
        }

        public void setKeyId(int keyId) {
            this.keyId = keyId;
        }

        public int getLockId() {
            return lockId;
        }

        public void setLockId(int lockId) {
            this.lockId = lockId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getKeyStatus() {
            return keyStatus;
        }

        public void setKeyStatus(String keyStatus) {
            this.keyStatus = keyStatus;
        }

        public String getLockName() {
            return lockName;
        }

        public void setLockName(String lockName) {
            this.lockName = lockName;
        }

        public String getLockAlias() {
            return lockAlias;
        }

        public void setLockAlias(String lockAlias) {
            this.lockAlias = lockAlias;
        }

        public String getLockData() {
            return lockData;
        }

        public void setLockData(String lockData) {
            this.lockData = lockData;
        }

        public String getLockKey() {
            return lockKey;
        }

        public void setLockKey(String lockKey) {
            this.lockKey = lockKey;
        }

        public String getLockMac() {
            return lockMac;
        }

        public void setLockMac(String lockMac) {
            this.lockMac = lockMac;
        }

        public int getLockFlagPos() {
            return lockFlagPos;
        }

        public void setLockFlagPos(int lockFlagPos) {
            this.lockFlagPos = lockFlagPos;
        }

        public String getAdminPwd() {
            return adminPwd;
        }

        public void setAdminPwd(String adminPwd) {
            this.adminPwd = adminPwd;
        }

        public String getNoKeyPwd() {
            return noKeyPwd;
        }

        public void setNoKeyPwd(String noKeyPwd) {
            this.noKeyPwd = noKeyPwd;
        }

        public String getDeletePwd() {
            return deletePwd;
        }

        public void setDeletePwd(String deletePwd) {
            this.deletePwd = deletePwd;
        }

        public int getElectricQuantity() {
            return electricQuantity;
        }

        public void setElectricQuantity(int electricQuantity) {
            this.electricQuantity = electricQuantity;
        }

        public String getAesKeyStr() {
            return aesKeyStr;
        }

        public void setAesKeyStr(String aesKeyStr) {
            this.aesKeyStr = aesKeyStr;
        }

        public LockVersionObj getLockVersion() {
            return lockVersion;
        }

        public void setLockVersion(LockVersionObj lockVersion) {
            this.lockVersion = lockVersion;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }

        public long getTimezoneRawOffset() {
            return timezoneRawOffset;
        }

        public void setTimezoneRawOffset(long timezoneRawOffset) {
            this.timezoneRawOffset = timezoneRawOffset;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getKeyRight() {
            return keyRight;
        }

        public void setKeyRight(int keyRight) {
            this.keyRight = keyRight;
        }

        public int getKeyboardPwdVersion() {
            return keyboardPwdVersion;
        }

        public void setKeyboardPwdVersion(int keyboardPwdVersion) {
            this.keyboardPwdVersion = keyboardPwdVersion;
        }

        public int getSpecialValue() {
            return specialValue;
        }

        public void setSpecialValue(int specialValue) {
            this.specialValue = specialValue;
        }

        public int getRemoteEnable() {
            return remoteEnable;
        }

        public void setRemoteEnable(int remoteEnable) {
            this.remoteEnable = remoteEnable;
        }
    }

}
