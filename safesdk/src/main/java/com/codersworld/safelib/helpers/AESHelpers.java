package com.codersworld.safelib.helpers;

import android.app.Activity;
import android.content.Context;

import com.codersworld.safelib.utils.CommonMethods;

import com.codersworld.configs.utils.AESHelper;

public class AESHelpers {

    public String safeEncryption(Context context, String strParam) {
        UserSessions mUserSession = new UserSessions(context);
        String token = (mUserSession != null && CommonMethods.isValidString(mUserSession.getAccessToken(context))) ? mUserSession.getAccessToken(context) : "";
        String userid = (mUserSession != null && mUserSession.getUserInfo(context) != null) ? mUserSession.getUserInfo(context).getEmail() : "";
        try {
            //strParam = DhanukaMain.SafeOBuddyEncryptUtils(strParam, token, userid);
            strParam = new AESHelper().safeEncryption(context,strParam,token,userid);
            return strParam;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String safeDecryption(String response, final Activity context) {
        String responses = "";
        try {
            responses = new AESHelper().safeDecryption(response,context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responses;
    }

}
