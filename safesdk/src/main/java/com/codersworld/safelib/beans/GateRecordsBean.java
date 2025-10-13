package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GateRecordsBean implements Serializable {
    @SerializedName("success")
    int success;
    @SerializedName("message")
    String message;
    @SerializedName("GetCOmmandSent")
    public ArrayList<InfoBean> GetCOmmandSent;
    @SerializedName("GetGateOpendStatus")
    public ArrayList<InfoBean> GetGateOpendStatus;

    @SerializedName("GetFieldworks")
    public ArrayList<InfoBean> GetFieldworks;

    @SerializedName("GetLockSummary")
    public ArrayList<InfoBean> GetLockSummary;
    //
    @SerializedName("GetPowerCut")
    public ArrayList<InfoBean> GetPowerCut;

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

    public ArrayList<InfoBean> getGetCOmmandSent() {
        return GetCOmmandSent;
    }

    public void setGetCOmmandSent(ArrayList<InfoBean> getCOmmandSent) {
        GetCOmmandSent = getCOmmandSent;
    }

    public ArrayList<InfoBean> getGetGateOpendStatus() {
        return GetGateOpendStatus;
    }

    public void setGetGateOpendStatus(ArrayList<InfoBean> getGateOpendStatus) {
        GetGateOpendStatus = getGateOpendStatus;
    }

    public ArrayList<InfoBean> getGetFieldworks() {
        return GetFieldworks;
    }

    public void setGetFieldworks(ArrayList<InfoBean> getFieldworks) {
        GetFieldworks = getFieldworks;
    }

    public ArrayList<InfoBean> getGetLockSummary() {
        return GetLockSummary;
    }

    public void setGetLockSummary(ArrayList<InfoBean> getLockSummary) {
        GetLockSummary = getLockSummary;
    }

    public ArrayList<InfoBean> getGetPowerCut() {
        return GetPowerCut;
    }

    public void setGetPowerCut(ArrayList<InfoBean> getPowerCut) {
        GetPowerCut = getPowerCut;
    }

    public static class InfoBean implements Serializable {
        @SerializedName("date")
        String date;
        @SerializedName("unixTime")
        private long unixTime;
        @SerializedName("SetupTime")
        public String SetupTime;
        @SerializedName("SentTime")
        public String SentTime;
        @SerializedName("OpenedThrough")
        public String OpenedThrough;
        @SerializedName("trackingtimes")
        public String trackingtimes;
        @SerializedName("CreatedBy")
        public String CreatedBy;
        @SerializedName("PhoneNumber")
        public String PhoneNumber;
        @SerializedName("Latitude")
        public String Latitude;
        @SerializedName("CurrentLocation")
        public String CurrentLocation;
        @SerializedName("CommandID")
        public String CommandID;
        @SerializedName("Status")
        public String Status;
        @SerializedName("Command")
        public String Command;
        @SerializedName("Status1")
        public String Status1;
        @SerializedName("Lat")
        public String Lat;
        @SerializedName("lat")
        public String lat;
        @SerializedName("AlartDateTime")
        public String AlartDateTime;
        @SerializedName("CardOwnerName")
        String CardOwnerName;
        @SerializedName("AlartDatetime")
        public String AlartDatetime;
        @SerializedName("LocationName")
        public String LocationName;
        @SerializedName("Long")
        public String Long;
        @SerializedName("Longitude")
        public String Longitude;
        @SerializedName("Speed")
        public String Speed;
        @SerializedName("InsertDatetime")
        public String InsertDatetime;
        @SerializedName("AckTime")
        public String AckTime;
        @SerializedName("AckComment")
        public String AckComment;
        @SerializedName("NotificationSent")
        public String NotificationSent;
        @SerializedName("WorkType")
        public String WorkType;
        @SerializedName("Fieldwork_Date")
        public String Fieldwork_Date;
        @SerializedName("ClientName")
        public String ClientName;
        @SerializedName("EmployeeName")
        public String EmployeeName;
        @SerializedName("VehicleNumber")
        public String VehicleNumber;
        @SerializedName("VehicleType")
        public String VehicleType;
        @SerializedName("Comment")
        public String Comment;
        @SerializedName("SIMNumber")
        public String SIMNumber;
        @SerializedName("BillingID")
        public String BillingID;
        @SerializedName("CurrentStatus")
        public String CurrentStatus;
        @SerializedName("FieldworkID")
        public String FieldworkID;
        @SerializedName("Updated_By")
        public String Updated_By;
        @SerializedName("DriverNumberAssignbytech")
        public String DriverNumberAssignbytech;
        @SerializedName("OwnerPhoneNo")
        public String OwnerPhoneNo;
        @SerializedName("DriverPhoneNo")
        public String DriverPhoneNo;
        @SerializedName("UnlockTime")
        public String UnlockTime;
        @SerializedName("LockedTime")
        public String LockedTime;
        @SerializedName("UnlockedDuration")
        public String UnlockedDuration;
        @SerializedName("FirstGateOpenedTime")
        public String FirstGateOpenedTime;
        @SerializedName("LastGateClosedTime")
        public String LastGateClosedTime;
        @SerializedName("AuthorisedBy")
        public String AuthorisedBy;
        @SerializedName("Alarkpid")
        public String Alarkpid;
        @SerializedName("devicecode")
        public String devicecode;
        @SerializedName("DeviceID")
        public String DeviceID;
        @SerializedName("DeviceCode")
        public String DeviceCode;
        @SerializedName("longs")
        public String longs;

        public long getUnixTime() {
            return unixTime;
        }

        public void setUnixTime(long unixTime) {
            this.unixTime = unixTime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSetupTime() {
            return SetupTime;
        }

        public void setSetupTime(String setupTime) {
            SetupTime = setupTime;
        }

        public String getSentTime() {
            return SentTime;
        }

        public void setSentTime(String sentTime) {
            SentTime = sentTime;
        }

        public String getOpenedThrough() {
            return OpenedThrough;
        }

        public void setOpenedThrough(String openedThrough) {
            OpenedThrough = openedThrough;
        }

        public String getTrackingtimes() {
            return trackingtimes;
        }

        public void setTrackingtimes(String trackingtimes) {
            this.trackingtimes = trackingtimes;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public void setCreatedBy(String createdBy) {
            CreatedBy = createdBy;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            PhoneNumber = phoneNumber;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getCurrentLocation() {
            return CurrentLocation;
        }

        public void setCurrentLocation(String currentLocation) {
            CurrentLocation = currentLocation;
        }

        public String getCommandID() {
            return CommandID;
        }

        public void setCommandID(String commandID) {
            CommandID = commandID;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getCommand() {
            return Command;
        }

        public void setCommand(String command) {
            Command = command;
        }

        public String getStatus1() {
            return Status1;
        }

        public void setStatus1(String status1) {
            Status1 = status1;
        }

        public String getLat() {
            return Lat;
        }

        public void setLat(String lat) {
            Lat = lat;
        }

        public String getAlartDateTime() {
            return AlartDateTime;
        }

        public void setAlartDateTime(String alartDateTime) {
            AlartDateTime = alartDateTime;
        }

        public String getCardOwnerName() {
            return CardOwnerName;
        }

        public void setCardOwnerName(String cardOwnerName) {
            CardOwnerName = cardOwnerName;
        }

        public String getAlartDatetime() {
            return AlartDatetime;
        }

        public void setAlartDatetime(String alartDatetime) {
            AlartDatetime = alartDatetime;
        }

        public String getLocationName() {
            return LocationName;
        }

        public void setLocationName(String locationName) {
            LocationName = locationName;
        }

        public String getLong() {
            return Long;
        }

        public void setLong(String aLong) {
            Long = aLong;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getSpeed() {
            return Speed;
        }

        public void setSpeed(String speed) {
            Speed = speed;
        }

        public String getInsertDatetime() {
            return InsertDatetime;
        }

        public void setInsertDatetime(String insertDatetime) {
            InsertDatetime = insertDatetime;
        }

        public String getAckTime() {
            return AckTime;
        }

        public void setAckTime(String ackTime) {
            AckTime = ackTime;
        }

        public String getAckComment() {
            return AckComment;
        }

        public void setAckComment(String ackComment) {
            AckComment = ackComment;
        }

        public String getNotificationSent() {
            return NotificationSent;
        }

        public void setNotificationSent(String notificationSent) {
            NotificationSent = notificationSent;
        }

        public String getWorkType() {
            return WorkType;
        }

        public void setWorkType(String workType) {
            WorkType = workType;
        }

        public String getFieldwork_Date() {
            return Fieldwork_Date;
        }

        public void setFieldwork_Date(String fieldwork_Date) {
            Fieldwork_Date = fieldwork_Date;
        }

        public String getClientName() {
            return ClientName;
        }

        public void setClientName(String clientName) {
            ClientName = clientName;
        }

        public String getEmployeeName() {
            return EmployeeName;
        }

        public void setEmployeeName(String employeeName) {
            EmployeeName = employeeName;
        }

        public String getVehicleNumber() {
            return VehicleNumber;
        }

        public void setVehicleNumber(String vehicleNumber) {
            VehicleNumber = vehicleNumber;
        }

        public String getVehicleType() {
            return VehicleType;
        }

        public void setVehicleType(String vehicleType) {
            VehicleType = vehicleType;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String comment) {
            Comment = comment;
        }

        public String getSIMNumber() {
            return SIMNumber;
        }

        public void setSIMNumber(String SIMNumber) {
            this.SIMNumber = SIMNumber;
        }

        public String getBillingID() {
            return BillingID;
        }

        public void setBillingID(String billingID) {
            BillingID = billingID;
        }

        public String getCurrentStatus() {
            return CurrentStatus;
        }

        public void setCurrentStatus(String currentStatus) {
            CurrentStatus = currentStatus;
        }

        public String getFieldworkID() {
            return FieldworkID;
        }

        public void setFieldworkID(String fieldworkID) {
            FieldworkID = fieldworkID;
        }

        public String getUpdated_By() {
            return Updated_By;
        }

        public void setUpdated_By(String updated_By) {
            Updated_By = updated_By;
        }

        public String getDriverNumberAssignbytech() {
            return DriverNumberAssignbytech;
        }

        public void setDriverNumberAssignbytech(String driverNumberAssignbytech) {
            DriverNumberAssignbytech = driverNumberAssignbytech;
        }

        public String getOwnerPhoneNo() {
            return OwnerPhoneNo;
        }

        public void setOwnerPhoneNo(String ownerPhoneNo) {
            OwnerPhoneNo = ownerPhoneNo;
        }

        public String getDriverPhoneNo() {
            return DriverPhoneNo;
        }

        public void setDriverPhoneNo(String driverPhoneNo) {
            DriverPhoneNo = driverPhoneNo;
        }

        public String getUnlockTime() {
            return UnlockTime;
        }

        public void setUnlockTime(String unlockTime) {
            UnlockTime = unlockTime;
        }

        public String getLockedTime() {
            return LockedTime;
        }

        public void setLockedTime(String lockedTime) {
            LockedTime = lockedTime;
        }

        public String getUnlockedDuration() {
            return UnlockedDuration;
        }

        public void setUnlockedDuration(String unlockedDuration) {
            UnlockedDuration = unlockedDuration;
        }

        public String getFirstGateOpenedTime() {
            return FirstGateOpenedTime;
        }

        public void setFirstGateOpenedTime(String firstGateOpenedTime) {
            FirstGateOpenedTime = firstGateOpenedTime;
        }

        public String getLastGateClosedTime() {
            return LastGateClosedTime;
        }

        public void setLastGateClosedTime(String lastGateClosedTime) {
            LastGateClosedTime = lastGateClosedTime;
        }

        public String getAuthorisedBy() {
            return AuthorisedBy;
        }

        public void setAuthorisedBy(String authorisedBy) {
            AuthorisedBy = authorisedBy;
        }

        public String getAlarkpid() {
            return Alarkpid;
        }

        public void setAlarkpid(String alarkpid) {
            Alarkpid = alarkpid;
        }

        public String getDevicecode() {
            return devicecode;
        }

        public void setDevicecode(String devicecode) {
            this.devicecode = devicecode;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String deviceID) {
            DeviceID = deviceID;
        }

        public String getDeviceCode() {
            return DeviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            DeviceCode = deviceCode;
        }

        public String getLongs() {
            return longs;
        }

        public void setLongs(String longs) {
            this.longs = longs;
        }
    }
}
