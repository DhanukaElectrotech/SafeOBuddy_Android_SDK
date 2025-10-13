package com.codersworld.safelib.beans;

import java.io.Serializable;

public class PendingDataBean implements Serializable {
    private int id;
    private String apiname;
    private String firstcolumn;
    private String detail;
    private String uploadStatus;
    private String serverId;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiname() {
        return apiname;
    }

    public void setApiname(String apiname) {
        this.apiname = apiname;
    }

    public String getFirstcolumn() {
        return firstcolumn;
    }

    public void setFirstcolumn(String firstcolumn) {
        this.firstcolumn = firstcolumn;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
