package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccountInfo extends ServerError implements Serializable {

@SerializedName("access_token")
    private String access_token;
    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("uid")
    private int uid;
    @SerializedName("openid")
    private int openid;
    @SerializedName("scope")
    private String scope;
    @SerializedName("token_type")
    private String token_type;
    @SerializedName("expires_in")
    private int expires_in;
    @SerializedName("md5Pwd")
    private String md5Pwd;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;


    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getOpenid() {
        return openid;
    }

    public void setOpenid(int openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getMd5Pwd() {
        return md5Pwd;
    }
    //
    public void setMd5Pwd(String md5Pwd) {
        this.md5Pwd = md5Pwd;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", uid=" + uid +
                ", openid=" + openid +
                ", scope='" + scope + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }
}
