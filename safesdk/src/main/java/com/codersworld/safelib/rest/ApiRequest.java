package com.codersworld.safelib.rest;



import com.codersworld.configs.urls.common.Constants;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiRequest {

    @GET("ConService.aspx?method=getlockmacdetails")////lockid,contactid,token
    Call<String> getDeviceInfo(@Query("lockid") String lockid,@Query(Constants.P_CONTACT_ID) String contactid,@Query("token") String token);
}
