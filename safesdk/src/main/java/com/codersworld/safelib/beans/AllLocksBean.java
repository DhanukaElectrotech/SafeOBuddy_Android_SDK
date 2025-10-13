package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AllLocksBean implements Serializable {
    @SerializedName("success")
    int success;
   @SerializedName("loginvalidation")
    int loginvalidation;
    @SerializedName("message")
    String message;
    @SerializedName("newusercreation")
    ArrayList<InfoBean> newusercreation;

    public int getLoginvalidation() {
        return loginvalidation;
    }

    public void setLoginvalidation(int loginvalidation) {
        this.loginvalidation = loginvalidation;
    }

    public ArrayList<InfoBean> getNewusercreation() {
        return newusercreation;
    }

    public void setNewusercreation(ArrayList<InfoBean> newusercreation) {
        this.newusercreation = newusercreation;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class InfoBean implements Serializable {
        @SerializedName("id")
        int id;
        @SerializedName("Success")
        String Success;
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
        @SerializedName("LockData")
        String LockData;
        @SerializedName("MACID")
        String MACID;
        @SerializedName("KeyId")
        String KeyId;
        @SerializedName("LockCatgeory")
        String LockCatgeory;
        @SerializedName("Access")
        String Access;
        @SerializedName("Remoteunlock")
        String Remoteunlock;
        @SerializedName("AutoLock")
        String AutoLock;
        @SerializedName("Roomstatus")
        String Roomstatus;
        @SerializedName("GuestName")
        String GuestName;
        @SerializedName("Checkindate")
        String Checkindate;
        @SerializedName("checkoutdate")
        String checkoutdate;
        @SerializedName("guestPhoneNo")
        String guestPhoneNo;
        @SerializedName("guestEmailId")
        String guestEmailId;
        @SerializedName("GuestMasterId")
        String GuestMasterId;
        @SerializedName("BatteryPerc")
        String BatteryPerc;
        @SerializedName("GatewayID")
        String GatewayID;
        @SerializedName("Gatewayname")
        String Gatewayname;
        @SerializedName("gatewaymac")
        String gatewaymac;
        @SerializedName("Hasgateway")
        String Hasgateway;
        @SerializedName("LockTTAccess")
        String LockTTAccess;
        @SerializedName("lockBuildingNumber")
        String lockBuildingNumber;
        @SerializedName("lockFloorNumber")
        String lockFloorNumber;
        @SerializedName("GuestContactId")
        String GuestContactId;
        @SerializedName("Lastaccesseddate")
        String Lastaccesseddate;
        @SerializedName("TotalAccessByuser")
        String TotalAccessByuser;
        @SerializedName("TotalAccessAdmin")
        String TotalAccessAdmin;
        @SerializedName("Totalaccess")
        String Totalaccess;
        @SerializedName("ItemName")
        String ItemName;
        @SerializedName("ItemDescription")
        String ItemDescription;
        @SerializedName("Status")
        String Status;
        @SerializedName("lockSocietyName")
        String lockSocietyName;
        @SerializedName("lockClusterName")
        String lockClusterName;
        @SerializedName("Val1")
        String Val1;
        @SerializedName("Val2")
        String Val2;
        @SerializedName("Val3")
        String Val3;
        @SerializedName("Val4")
        String Val4;
        @SerializedName("Val5")
        String Val5;
        @SerializedName("Val6")
        String Val6;
        @SerializedName("isflagui")
        String isflagui;
        @SerializedName("currentbalance")
        String currentbalance;
        @SerializedName("UnitId")
        String UnitId;
        @SerializedName("GateStatus")
        String GateStatus;
        @SerializedName("OnlineStatus")
        String OnlineStatus;
       @SerializedName("owner_id")
        String owner_id;
       @SerializedName("btlockid")
        String btlockid;
       @SerializedName("btlockidval")
        String btlockidval;

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

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public String getSuccess() {
            return Success;
        }

        public void setSuccess(String success) {
            Success = success;
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

        public String getKeyId() {
            return KeyId;
        }

        public void setKeyId(String keyId) {
            KeyId = keyId;
        }

        public String getLockCatgeory() {
            return LockCatgeory;
        }

        public void setLockCatgeory(String lockCatgeory) {
            LockCatgeory = lockCatgeory;
        }

        public String getAccess() {
            return Access;
        }

        public void setAccess(String access) {
            Access = access;
        }

        public String getRemoteunlock() {
            return Remoteunlock;
        }

        public void setRemoteunlock(String remoteunlock) {
            Remoteunlock = remoteunlock;
        }

        public String getAutoLock() {
            return AutoLock;
        }

        public void setAutoLock(String autoLock) {
            AutoLock = autoLock;
        }

        public String getRoomstatus() {
            return Roomstatus;
        }

        public void setRoomstatus(String roomstatus) {
            Roomstatus = roomstatus;
        }

        public String getGuestName() {
            return GuestName;
        }

        public void setGuestName(String guestName) {
            GuestName = guestName;
        }

        public String getCheckindate() {
            return Checkindate;
        }

        public void setCheckindate(String checkindate) {
            Checkindate = checkindate;
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

        public String getBatteryPerc() {
            return BatteryPerc;
        }

        public void setBatteryPerc(String batteryPerc) {
            BatteryPerc = batteryPerc;
        }

        public String getGatewayID() {
            return GatewayID;
        }

        public void setGatewayID(String gatewayID) {
            GatewayID = gatewayID;
        }

        public String getGatewayname() {
            return Gatewayname;
        }

        public void setGatewayname(String gatewayname) {
            Gatewayname = gatewayname;
        }

        public String getGatewaymac() {
            return gatewaymac;
        }

        public void setGatewaymac(String gatewaymac) {
            this.gatewaymac = gatewaymac;
        }

        public String getHasgateway() {
            return Hasgateway;
        }

        public void setHasgateway(String hasgateway) {
            Hasgateway = hasgateway;
        }

        public String getLockTTAccess() {
            return LockTTAccess;
        }

        public void setLockTTAccess(String lockTTAccess) {
            LockTTAccess = lockTTAccess;
        }

        public String getLockBuildingNumber() {
            return lockBuildingNumber;
        }

        public void setLockBuildingNumber(String lockBuildingNumber) {
            this.lockBuildingNumber = lockBuildingNumber;
        }

        public String getLockFloorNumber() {
            return lockFloorNumber;
        }

        public void setLockFloorNumber(String lockFloorNumber) {
            this.lockFloorNumber = lockFloorNumber;
        }

        public String getGuestContactId() {
            return GuestContactId;
        }

        public void setGuestContactId(String guestContactId) {
            GuestContactId = guestContactId;
        }

        public String getLastaccesseddate() {
            return Lastaccesseddate;
        }

        public void setLastaccesseddate(String lastaccesseddate) {
            Lastaccesseddate = lastaccesseddate;
        }

        public String getTotalAccessByuser() {
            return TotalAccessByuser;
        }

        public void setTotalAccessByuser(String totalAccessByuser) {
            TotalAccessByuser = totalAccessByuser;
        }

        public String getTotalAccessAdmin() {
            return TotalAccessAdmin;
        }

        public void setTotalAccessAdmin(String totalAccessAdmin) {
            TotalAccessAdmin = totalAccessAdmin;
        }

        public String getTotalaccess() {
            return Totalaccess;
        }

        public void setTotalaccess(String totalaccess) {
            Totalaccess = totalaccess;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String itemName) {
            ItemName = itemName;
        }

        public String getItemDescription() {
            return ItemDescription;
        }

        public void setItemDescription(String itemDescription) {
            ItemDescription = itemDescription;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getLockSocietyName() {
            return lockSocietyName;
        }

        public void setLockSocietyName(String lockSocietyName) {
            this.lockSocietyName = lockSocietyName;
        }

        public String getLockClusterName() {
            return lockClusterName;
        }

        public void setLockClusterName(String lockClusterName) {
            this.lockClusterName = lockClusterName;
        }

        public String getVal1() {
            return Val1;
        }

        public void setVal1(String val1) {
            Val1 = val1;
        }

        public String getVal2() {
            return Val2;
        }

        public void setVal2(String val2) {
            Val2 = val2;
        }

        public String getVal3() {
            return Val3;
        }

        public void setVal3(String val3) {
            Val3 = val3;
        }

        public String getVal4() {
            return Val4;
        }

        public void setVal4(String val4) {
            Val4 = val4;
        }

        public String getVal5() {
            return Val5;
        }

        public void setVal5(String val5) {
            Val5 = val5;
        }

        public String getVal6() {
            return Val6;
        }

        public void setVal6(String val6) {
            Val6 = val6;
        }

        public String getIsflagui() {
            return isflagui;
        }

        public void setIsflagui(String isflagui) {
            this.isflagui = isflagui;
        }

        public String getCurrentbalance() {
            return currentbalance;
        }

        public void setCurrentbalance(String currentbalance) {
            this.currentbalance = currentbalance;
        }

        public String getUnitId() {
            return UnitId;
        }

        public void setUnitId(String unitId) {
            UnitId = unitId;
        }

        public String getGateStatus() {
            return GateStatus;
        }

        public void setGateStatus(String gateStatus) {
            GateStatus = gateStatus;
        }

        public String getOnlineStatus() {
            return OnlineStatus;
        }

        public void setOnlineStatus(String onlineStatus) {
            OnlineStatus = onlineStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
