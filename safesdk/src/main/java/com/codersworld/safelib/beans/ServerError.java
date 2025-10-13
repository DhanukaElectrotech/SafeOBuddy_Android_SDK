package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by TTLock on 2019/4/23.
 */

public class ServerError implements Serializable {
    /**
     * errcode : 10000
     * errmsg : invalid client_id
     * description : client_id不存在
     */

    @SerializedName("errcode")
    public int errcode;
    @SerializedName("errmsg")
    public String errmsg;
    @SerializedName("description")
    public String description;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
