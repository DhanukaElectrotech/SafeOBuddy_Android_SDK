package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class LockVersionObj implements Serializable {
    /**
     * showAdminKbpwdFlag : true
     * groupId : 1
     * protocolVersion : 3
     * protocolType : 5
     * orgId : 1
     * logoUrl :
     * scene : 2
     */

@SerializedName("showAdminKbpwdFlag")
private boolean showAdminKbpwdFlag;
    @SerializedName("groupId")
    private int groupId;
    @SerializedName("protocolVersion")
    private int protocolVersion;
    @SerializedName("protocolType")
    private int protocolType;
    @SerializedName("orgId")
    private int orgId;
    @SerializedName("logoUrl")
    private String logoUrl;
    @SerializedName("scene")
    private int scene;

    public boolean isShowAdminKbpwdFlag() {
        return showAdminKbpwdFlag;
    }

    public void setShowAdminKbpwdFlag(boolean showAdminKbpwdFlag) {
        this.showAdminKbpwdFlag = showAdminKbpwdFlag;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }
}
