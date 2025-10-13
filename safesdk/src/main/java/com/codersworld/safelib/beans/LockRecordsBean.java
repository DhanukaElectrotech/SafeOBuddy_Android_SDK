package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class LockRecordsBean implements Serializable {
    @SerializedName("success")
    int success;
     @SerializedName("returnds")
     ArrayList<InfoBean> returnds;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public ArrayList<InfoBean> getReturnds() {
        return returnds;
    }

    public void setReturnds(ArrayList<InfoBean> returnds) {
        this.returnds = returnds;
    }

    public static class InfoBean implements Serializable{
    @SerializedName("SetupTime")
    String SetupTime;
    @SerializedName("CardType")
    String CardType;
    @SerializedName("Command")
    String Command;
    @SerializedName("CommandID")
    String CommandID;
    @SerializedName("CurrentLocation")
    String CurrentLocation;
    @SerializedName("Longitude")
    String Longitude;
    @SerializedName("Latitude")
    String Latitude;
    @SerializedName("PhoneNumber")
    String PhoneNumber;
    @SerializedName("CreatedBy")
    String CreatedBy;
    @SerializedName("trackingtimes")
    String trackingtimes;
    @SerializedName("OpenedThrough")
    String OpenedThrough;
    @SerializedName("SentTime")
    String SentTime;
    @SerializedName("CardOwnerName")
    String CardOwnerName;

    public String getSetupTime() {
        return SetupTime;
    }

    public void setSetupTime(String setupTime) {
        SetupTime = setupTime;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getCommandID() {
        return CommandID;
    }

    public void setCommandID(String commandID) {
        CommandID = commandID;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        CurrentLocation = currentLocation;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getTrackingtimes() {
        return trackingtimes;
    }

    public void setTrackingtimes(String trackingtimes) {
        this.trackingtimes = trackingtimes;
    }

    public String getOpenedThrough() {
        return OpenedThrough;
    }

    public void setOpenedThrough(String openedThrough) {
        OpenedThrough = openedThrough;
    }

    public String getSentTime() {
        return SentTime;
    }

    public void setSentTime(String sentTime) {
        SentTime = sentTime;
    }

    public String getCardOwnerName() {
        return CardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        CardOwnerName = cardOwnerName;
    }
}

}
