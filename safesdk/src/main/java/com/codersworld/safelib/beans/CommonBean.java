package com.codersworld.safelib.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CommonBean implements Serializable {

    @SerializedName("msg")
    String msg;
    @SerializedName("success")
    int success;
    @SerializedName("newusercreation")
    ArrayList<SignupBean> newusercreation;

    @SerializedName("returnmessage")
    ArrayList<SOSBean> returnmessage;

    public ArrayList<SOSBean> getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(ArrayList<SOSBean> returnmessage) {
        this.returnmessage = returnmessage;
    }

    @SerializedName("returnds")
    private ArrayList<InfoBean> returnds;

    public ArrayList<InfoBean> getReturnds() {
        return returnds;
    }

    public void setReturnds(ArrayList<InfoBean> returnds) {
        this.returnds = returnds;
    }

    public ArrayList<SignupBean> getNewusercreation() {
        return newusercreation;
    }

    public void setNewusercreation(ArrayList<SignupBean> newusercreation) {
        this.newusercreation = newusercreation;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class InfoBean implements Serializable {
        @SerializedName("Success")
        private String Success;
        @SerializedName("returnval")
        private String returnval;
        @SerializedName("retruncid")
        private String retruncid;
        @SerializedName("sim")
        private String sim;
        @SerializedName("imagedetail")
        private String imagedetail;

        public String getImagedetail() {
            return imagedetail;
        }

        public void setImagedetail(String imagedetail) {
            this.imagedetail = imagedetail;
        }

        public String getSuccess() {
            return Success;
        }

        public void setSuccess(String success) {
            Success = success;
        }

        public String getReturnval() {
            return returnval;
        }

        public void setReturnval(String returnval) {
            this.returnval = returnval;
        }

        public String getRetruncid() {
            return retruncid;
        }

        public void setRetruncid(String retruncid) {
            this.retruncid = retruncid;
        }

        public String getSim() {
            return sim;
        }

        public void setSim(String sim) {
            this.sim = sim;
        }
    }


    public static class SignupBean implements Serializable {
        @SerializedName("returnval")
        String returnval;

        public String getReturnval() {
            return returnval;
        }

        public void setReturnval(String returnval) {
            this.returnval = returnval;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        @SerializedName("cid")
        String cid;

    }

    public static class SOSBean implements Serializable {
        @SerializedName("status")
        String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
