package com.codersworld.safelib.helpers;

import com.codersworld.safelib.beans.LoginBean;

import org.json.JSONObject;

public class LoginGson {

    public LoginBean fromJson(String response) {
        LoginBean mBean = null;
        try{
            JSONObject mObject = new JSONObject(response) ;
            mBean = new LoginBean();
            mBean.setSuccess(mObject.getInt("success"));
            mBean.setMessage(mObject.getString("message"));
            return mBean;
        }catch (Exception e){
            return null;
        }
    }
}
