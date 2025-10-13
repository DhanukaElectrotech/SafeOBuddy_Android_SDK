package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GuestShutterLockModel implements Serializable {

    @SerializedName("Success")
    String Success;
    @SerializedName("GuestName")
    String GuestName;
    @SerializedName("checkoutdate")
    String checkoutdate;
    @SerializedName("guestPhoneNo")
    String guestPhoneNo;
    @SerializedName("guestEmailId")
    String guestEmailId;
    @SerializedName("GuestMasterId")
    String GuestMasterId;
    @SerializedName("ItemName")
    String ItemName;

    @SerializedName("CustomerName")
    String CustomerName;
    @SerializedName("PhoneNo")
    String PhoneNo;
    @SerializedName("Address")
    String Address;
    @SerializedName("DeviceType")
    String DeviceType;
    @SerializedName("DeviceCode")
    String DeviceCode;
    @SerializedName("DeviceID")
    String DeviceID;
    @SerializedName("VehicleNumber")
    String VehicleNumber;
    @SerializedName("CompanyName")
    String CompanyName;
    @SerializedName("DevicePhoneNumber")
    String DevicePhoneNumber;
    @SerializedName("DeviceSIMNumber")
    String DeviceSIMNumber;
    @SerializedName("fileName")
    String fileName;
    @SerializedName("filepath")
    String filepath;
    @SerializedName("LockCatgeory")
    String LockCatgeory;
    @SerializedName("MACID")
    String MACID;
    @SerializedName("LockData")
    String LockData;
    @SerializedName("Remoteunlock")
    String Remoteunlock;
    @SerializedName("KeyId")
    String KeyId;
    @SerializedName("AutoLock")
    String AutoLock;
    @SerializedName("Access")
    String Access;
    @SerializedName("BatteryPerc")
    String BatteryPerc;
    @SerializedName("Hasgateway")
    String Hasgateway;
    @SerializedName("LockTTAccess")
    String LockTTAccess;
    @SerializedName("isflagui")
    String isflagui;
    @SerializedName("UnitId")
    String UnitId;
    @SerializedName("GateStatus")
    String gateStatus;
    @SerializedName("OnlineStatus")
    String onlineStatus;
    @SerializedName("Startdates")
    String Startdates;
    @SerializedName("Enddates")
    String Enddates;
    @SerializedName("LockID")
    String LockID;
    @SerializedName("LockCode")
    String LockCode;
    @SerializedName("deviceid")
    String deviceid;
    @SerializedName("devicenumber")
    String devicenumber;
    @SerializedName("vehiclenumber")
    String vehiclenumber;
    @SerializedName("MainLockID")
    String MainLockID;
    @SerializedName("MainLockNumber")
    String MainLockNumber;
    @SerializedName("LockType")
    String LockType;
    @SerializedName("MainLocktype")
    String MainLocktype;
    @SerializedName("autotimer")
    String autotimer;
    @SerializedName("btlockid")
    String btlockid;
    Boolean isActive = false;
    int permissionStatus = 0;//0 for not asked, 1 for asked, 2 for permission given

    public int getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(int permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getCheckindate() {
        return Checkindate;
    }

    public void setCheckindate(String Checkindate) {
        Checkindate = Checkindate;
    }

    @SerializedName("Checkindate")
    String Checkindate;

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @SerializedName("Roomstatus")
    String roomStatus;

    public String getLockTTAccess() {
        return LockTTAccess;
    }

    public void setLockTTAccess(String lockTTAccess) {
        LockTTAccess = lockTTAccess;
    }

    public String getHasgateway() {
        return Hasgateway;
    }

    public void setHasgateway(String hasgateway) {
        Hasgateway = hasgateway;
    }

    public String getBatteryPerc() {
        return BatteryPerc;
    }

    public void setBatteryPerc(String batteryPerc) {
        BatteryPerc = batteryPerc;
    }

    public String getAccess() {
        return Access;
    }

    public void setAccess(String access) {
        Access = access;
    }

    public String getAutoLock() {
        return AutoLock;
    }

    public void setAutoLock(String autoLock) {
        AutoLock = autoLock;
    }

    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String keyId) {
        KeyId = keyId;
    }

    public String getRemoteunlock() {
        return Remoteunlock;
    }

    public void setRemoteunlock(String remoteunlock) {
        Remoteunlock = remoteunlock;
    }

    public String getLockCatgeory() {
        return LockCatgeory;
    }

    public void setLockCatgeory(String lockCatgeory) {
        LockCatgeory = lockCatgeory;
    }

    public String getMACID() {
        return MACID;
    }

    public void setMACID(String MACID) {
        this.MACID = MACID;
    }

    public String getLockData() {
        return LockData;
    }

    public void setLockData(String lockData) {
        LockData = lockData;
    }

    public String getDeviceSIMNumber() {
        return DeviceSIMNumber;
    }

    public void setDeviceSIMNumber(String deviceSIMNumber) {
        DeviceSIMNumber = deviceSIMNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getDeviceCode() {
        return DeviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        DeviceCode = deviceCode;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        VehicleNumber = vehicleNumber;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDevicePhoneNumber() {
        return DevicePhoneNumber;
    }

    public void setDevicePhoneNumber(String devicePhoneNumber) {
        DevicePhoneNumber = devicePhoneNumber;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getGuestPhoneNo() {
        return guestPhoneNo;
    }

    public void setGuestPhoneNo(String guestPhoneNo) {
        this.guestPhoneNo = guestPhoneNo;
    }

    public String getGuestEmailId() {
        return guestEmailId;
    }

    public void setGuestEmailId(String guestEmailId) {
        this.guestEmailId = guestEmailId;
    }

    public String getGuestMasterId() {
        return GuestMasterId;
    }

    public void setGuestMasterId(String guestMasterId) {
        GuestMasterId = guestMasterId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getIsflagui() {
        return isflagui;
    }

    public void setIsflagui(String isflagui) {
        this.isflagui = isflagui;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(String gateStatus) {
        this.gateStatus = gateStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getStartdates() {
        return Startdates;
    }

    public void setStartdates(String startdates) {
        Startdates = startdates;
    }

    public String getEnddates() {
        return Enddates;
    }

    public void setEnddates(String enddates) {
        Enddates = enddates;
    }

    public String getLockID() {
        return LockID;
    }

    public void setLockID(String lockID) {
        LockID = lockID;
    }

    public String getLockCode() {
        return LockCode;
    }

    public void setLockCode(String lockCode) {
        LockCode = lockCode;
    }


    public String getDevicenumber() {
        return devicenumber;
    }

    public void setDevicenumber(String devicenumber) {
        this.devicenumber = devicenumber;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getMainLockID() {
        return MainLockID;
    }

    public void setMainLockID(String mainLockID) {
        MainLockID = mainLockID;
    }

    public String getMainLockNumber() {
        return MainLockNumber;
    }

    public void setMainLockNumber(String mainLockNumber) {
        MainLockNumber = mainLockNumber;
    }

    public String getLockType() {
        return LockType;
    }

    public void setLockType(String lockType) {
        LockType = lockType;
    }

    public String getMainLocktype() {
        return MainLocktype;
    }

    public void setMainLocktype(String mainLocktype) {
        MainLocktype = mainLocktype;
    }

    public String getAutotimer() {
        return autotimer;
    }

    public void setAutotimer(String autotimer) {
        this.autotimer = autotimer;
    }

    public String getBtlockid() {
        return btlockid;
    }

    public void setBtlockid(String btlockid) {
        this.btlockid = btlockid;
    }
}
