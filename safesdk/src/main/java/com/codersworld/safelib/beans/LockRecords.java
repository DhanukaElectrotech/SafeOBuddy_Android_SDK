package com.codersworld.safelib.beans;

import java.io.Serializable;

public class LockRecords implements Serializable {

    private String keyName;
    private int recordType;
    private long lockDate;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public long getLockDate() {
        return lockDate;
    }

    public void setLockDate(long lockDate) {
        this.lockDate = lockDate;
    }
}
