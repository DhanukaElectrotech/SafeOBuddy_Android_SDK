package com.codersworld.safelib.beans;

import java.io.Serializable;

public class GuestLockData implements Serializable {



            String GuestName;
            String LockId;
            String LockNumber;
            String checkindate;
            String checkoutdate;
            String Lockkey;
            String PassCode;

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getLockId() {
        return LockId;
    }

    public void setLockId(String lockId) {
        LockId = lockId;
    }

    public String getLockNumber() {
        return LockNumber;
    }

    public void setLockNumber(String lockNumber) {
        LockNumber = lockNumber;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getLockkey() {
        return Lockkey;
    }

    public void setLockkey(String lockkey) {
        Lockkey = lockkey;
    }

    public String getPassCode() {
        return PassCode;
    }

    public void setPassCode(String passCode) {
        PassCode = passCode;
    }
}
