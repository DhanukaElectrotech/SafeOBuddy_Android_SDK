package com.codersworld.safelib.rest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codersworld.configs.urls.common.Links;
import com.codersworld.safelib.beans.DeviceDetailBean;
import com.codersworld.configs.beans.DeviceInfoBean;
import com.codersworld.safelib.helpers.LoginGson;
import com.codersworld.safelib.helpers.UserSessions;
import com.codersworld.safelib.helpers.AESHelpers;
 import com.codersworld.safelib. beans.AccountInfo;
import com.codersworld.safelib.beans.AllLocksBean;
import com.codersworld.safelib.beans.KeyListObj;
import com.codersworld.safelib.beans.LoginBean;
import com.codersworld.safelib.rest.ttlock.RetrofitAPIManager;
import com.codersworld.safelib.utils.CommonMethods;
import com.codersworld.safelib.utils.SFProgress;
import com.dhanukaelectrotech.safesdk.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import com.codersworld.configs.rest.ApiRequest;

import org.json.JSONObject;

public class ApiCall {
    public Activity mContext = null;
    public UserSessions mUserSessions = null;

    public ApiCall(Context applicationContext) {
    }
    public ApiCall(Activity ctx) {
        this.mContext = ctx;
        mUserSessions = new UserSessions(mContext);
    }
    public void userLogin(OnResponse<UniverSelObjct> onResponse, String strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.makeLogin(strParams).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);

                             LoginBean mBean = new Gson().fromJson(strResp, LoginBean.class);
                             onResponse.onSuccess(new UniverSelObjct(mBean, Links.SB_LOGIN_API, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_LOGIN_API, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_LOGIN_API, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                t.printStackTrace();
                onResponse.onError(Links.SB_LOGIN_API, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void ttlockAuth(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(3, 3).create(ApiRequest.class);
        mRequest.ttlockAuth(strParams[0], strParams[1], strParams[2], strParams[3]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                try {
                    if (response != null) {
                        try {
                            String strResp = response.body().toString();
                            AccountInfo mBean = new Gson().fromJson(strResp, AccountInfo.class);
                            onResponse.onSuccess(new UniverSelObjct(mBean, Links.SB_API_TTLOCK_AUTH_TOKEN, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_TTLOCK_AUTH_TOKEN, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_TTLOCK_AUTH_TOKEN, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                t.printStackTrace();
                onResponse.onError(Links.SB_API_TTLOCK_AUTH_TOKEN, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getUserKeyList(OnResponse<UniverSelObjct> onResponse, HashMap<String, String> param) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< ResponseBody > call = mRequest.getUserKeyList(param);
        RetrofitAPIManager.enqueue(call,new TypeToken<KeyListObj>() {}, result->{
            try{
            }catch (Exception e){
                e.printStackTrace();
            }
            if (!result.success) {
                onResponse.onError(Links.SB_API_TTLOCK_USER_KEYLIST, mContext.getResources().getString(R.string.something_wrong));
                return;
            }else{
                KeyListObj keyListObj = result.getResult();
                onResponse.onSuccess(new UniverSelObjct(keyListObj, Links.SB_API_TTLOCK_USER_KEYLIST, "true", ""));
            }

        }, requestError -> {
            requestError.printStackTrace();
            onResponse.onError(Links.SB_API_TTLOCK_USER_KEYLIST, mContext.getResources().getString(R.string.something_wrong));
        });
     }
    public void getGatewayList(OnResponse<UniverSelObjct> onResponse,  String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        Call<String> call = mRequest.getGatewayList(params[0],params[1],params[2],params[3],params[4]);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (json.contains("list")) {
                    onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_GATEWAY_LIST, "true", ""));
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_GATEWAY_LIST, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_GATEWAY_LIST, mContext.getResources().getString(R.string.something_wrong));
            }
        });
     }

    public void uploadGatewayDetail(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.uploadGatewayDetail(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_UPLOAD_DETAIL, "true", (params.length>8)?params[8]:""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_UPLOAD_DETAIL, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_UPLOAD_DETAIL, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_UPLOAD_DETAIL, mContext.getResources().getString(R.string.something_wrong));
            }
        });
     }

   public void renameGateway(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.renameGateway(params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_RENAME, "true", (params.length>8)?params[8]:""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_RENAME, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_RENAME, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_RENAME, mContext.getResources().getString(R.string.something_wrong));
            }
        });
     }

   public void renameTTLock(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.renameTTLock(params[0],params[1],params[2],params[3],params[4]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_RENAME_LOCK, "true", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_RENAME_LOCK, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_RENAME_LOCK, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_RENAME_LOCK, mContext.getResources().getString(R.string.something_wrong));
            }
        });
     }

   public void gatewayIsInitSuccess(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.gatewayIsInitSuccess(params[0],params[1],params[2],params[3]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_INIT_SUCCESS, "true", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_INIT_SUCCESS, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_INIT_SUCCESS, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_INIT_SUCCESS, mContext.getResources().getString(R.string.something_wrong));
            }
        });
     }
    public void getGateRecords(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.getGateRecords(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                            onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_API_GET_GATE_RECORDS, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_GET_GATE_RECORDS, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_GET_GATE_RECORDS, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_API_GET_GATE_RECORDS, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }
    public void getGateRecordsData(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.getGateRecordsData(strParams[0],strParams[1],strParams[2],strParams[3],strParams[4],strParams[5],strParams[6],strParams[7]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response != null) {
                        try {
                             onResponse.onSuccess(new UniverSelObjct(response.body().toString(), Links.SB_API_GET_GATE_RECORDS_DATA, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_GET_GATE_RECORDS_DATA, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_GET_GATE_RECORDS_DATA, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_API_GET_GATE_RECORDS_DATA, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getAllV3Locks(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        Call<String>  mCall = mRequest.getAllV3Locks(strParams[0]);
        if(strParams.length>1){
            mCall = mRequest.getAllV3Locks1(strParams[0],strParams[1]);
        }
        mCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                            AllLocksBean mBean = new Gson().fromJson(strResp,AllLocksBean.class);
                             onResponse.onSuccess(new UniverSelObjct((mBean !=null)?mBean:new AllLocksBean(), Links.SB_GET_ALL_V3_LOCKS, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_GET_ALL_V3_LOCKS, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_GET_ALL_V3_LOCKS, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_GET_ALL_V3_LOCKS, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void updateLockData(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.updateLockData(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                              onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_UPDATE_LOCK_DATA_CHILD, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_UPDATE_LOCK_DATA_CHILD, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_UPDATE_LOCK_DATA_CHILD, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_UPDATE_LOCK_DATA_CHILD, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void updateLockName(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.updateLockName(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                             onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_UPDATE_LOCK_NAME, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void deleteLock(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.deleteLock(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                             onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_DELETE_LOCK, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_DELETE_LOCK, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_DELETE_LOCK, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_DELETE_LOCK, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void saveArmMode(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.saveArmMode(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                             onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_UPDATE_LOCK_NAME, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_UPDATE_LOCK_NAME, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void uploadGeneratedOTP(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.uploadGeneratedOTP(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                             onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_OPEN_LOCK, strParams[1], ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_OPEN_LOCK, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_OPEN_LOCK, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_OPEN_LOCK, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }
    public void unlockPendingGates(OnResponse<UniverSelObjct> onResponse, Map<String, String> mParams,Boolean isTrue) {
        if (isTrue){
            try {
                SFProgress.showProgressDialog(mContext, true);
            } catch (Exception e) {
            }
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(2, 2).create(ApiRequest.class);
        mRequest.unlockPendingGates(mParams).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (isTrue){
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                try {
                    if (response != null) {
                        try {
                            String strResp = response.body().toString();
                            onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_UNLOCK_GATE_UPLOAD, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_UNLOCK_GATE_UPLOAD, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_UNLOCK_GATE_UPLOAD, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                if (isTrue){
                    try {
                        SFProgress.hideProgressDialog(mContext);
                    } catch (Exception e) {
                    }
                }
                onResponse.onError(Links.SB_UNLOCK_GATE_UPLOAD, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void saveLockStatus(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);

        mRequest.saveGateRecords(strParams[0],strParams[1],strParams[2],strParams[3],strParams[4],strParams[5],strParams[6]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                try {
                    if (response != null) {
                        try {
                              onResponse.onSuccess(new UniverSelObjct(response.body().toString(), Links.SB_API_SAVE_LOCK_STATUS_DATA, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_SAVE_LOCK_STATUS_DATA, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_SAVE_LOCK_STATUS_DATA, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_SAVE_LOCK_STATUS_DATA, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void initTTLock(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.iniTTLock(params[0],params[1],params[2],params[3],params[4]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {//LockInitResultObj
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_INIT, "true", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_INIT, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_INIT, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_TTLOCK_INIT, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void remotlyUnlockDevice(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.remotlyUnlockDevice(params[0],params[1],params[2],params[3]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String json = response.body();
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                if (CommonMethods.isValidString(json)){
                    try {//LockInitResultObj
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_TTLOCK_UNLOCK_REMOTLY, "true", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_TTLOCK_UNLOCK_REMOTLY, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_TTLOCK_UNLOCK_REMOTLY, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onResponse.onError(Links.SB_API_TTLOCK_UNLOCK_REMOTLY, mContext.getResources().getString(R.string.something_wrong));
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
            }
        });
    }

    public void addLocksToServer(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.addLocksToServer(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                            onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_API_ADD_NEW_LOCK, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

   public void getTroubleshoot(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.addLocksToServer(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                try {
                    if (response != null) {
                        try {
                            String strResp = new AESHelpers().safeDecryption(response.body().toString(), mContext);
                            onResponse.onSuccess(new UniverSelObjct(strResp, Links.SB_API_ADD_NEW_LOCK, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_ADD_NEW_LOCK, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

 //SB_API_LOCK_RECORDS_LIST
    public void getLockRecords(OnResponse<UniverSelObjct> onResponse, String... params) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitAPIManager.provideClientApi(0);
        //mRequest.getUserKeyList(param).enqueue(
        Call< String > call = mRequest.getLockRecords(params[0],params[1],params[2],params[3],params[4],params[5],params[6]);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                String json = response.body();
                if (CommonMethods.isValidString(json)){
                    try {//LockInitResultObj
                        onResponse.onSuccess(new UniverSelObjct(json, Links.SB_API_LOCK_RECORDS_LIST, "true", ""));
                    } catch (Exception e) {
                        e.printStackTrace();
                        onResponse.onError(Links.SB_API_LOCK_RECORDS_LIST, mContext.getResources().getString(R.string.something_wrong));
                    }
                } else {
                    onResponse.onError(Links.SB_API_LOCK_RECORDS_LIST, mContext.getResources().getString(R.string.something_wrong));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_LOCK_RECORDS_LIST, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void checkBluetoothAccess(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        try {
            SFProgress.showProgressDialog(mContext, true);
        } catch (Exception e) {
        }
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.checkBluetoothAccess(strParams[0]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }

                try {
                    if (response != null) {
                        try {
                            onResponse.onSuccess(new UniverSelObjct(response.body().toString(), Links.SB_API_CHECK_BLUETOOTH_ACCESS, "true", ""));
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_CHECK_BLUETOOTH_ACCESS, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_CHECK_BLUETOOTH_ACCESS, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                try {
                    SFProgress.hideProgressDialog(mContext);
                } catch (Exception e) {
                }
                onResponse.onError(Links.SB_API_CHECK_BLUETOOTH_ACCESS, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

    public void getLockData(OnResponse<UniverSelObjct> onResponse,  String... params) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, Links.BASE_URL_TTLOCK1+Links.SB_API_TTLOCK_GET_LOCKDATA,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String res = response;
                        try {
                            JSONObject obj = new JSONObject(response);
                             if (obj.has("lockData")){
                                 DeviceDetailBean mBean = new Gson().fromJson(response,DeviceDetailBean.class);
                                 onResponse.onSuccess(new UniverSelObjct((mBean !=null)?mBean:new DeviceDetailBean(), Links.SB_API_TTLOCK_GET_LOCKDATA, "true", ""));
                             }else{
                                onResponse.onError(Links.SB_API_TTLOCK_GET_LOCKDATA, mContext.getResources().getString(R.string.something_wrong));
                            }
                      /*      if (obj.has("lockData")){
                                String electricQuantity = obj.getString("electricQuantity");
                                String lockData = obj.getString("lockData");
                                String keyId = obj.getString("keyId");
                                String lockMac = obj.getString("lockMac");
                                String noKeyPwd = obj.getString("noKeyPwd");
                                GuestShutterLockModel mBean1 = checkLockData(guestShutterLockModel);
                                guestShutterLockModel.setLockData(mBean1.getLockData());
                                guestShutterLockModel.setMACID(mBean1.getMACID());
                                hitUpdateLockDataApi(lockData, lockMac, guestShutterLockModel.getBtlockid());
                                //                                lockOpenProcess(lockData, lockMac);
                            }*/

                        } catch (Exception e) {
                            onResponse.onError(Links.SB_API_TTLOCK_GET_LOCKDATA, mContext.getResources().getString(R.string.something_wrong));
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onResponse.onError(Links.SB_API_TTLOCK_GET_LOCKDATA, mContext.getResources().getString(R.string.something_wrong));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> prms = new HashMap<String, String>();
                prms.put("accessToken",params[1] );
                prms.put("lockId", params[2]);
                prms.put("date", String.valueOf(System.currentTimeMillis()));
                prms.put("clientId", params[0]);
                return prms;
            }
        };

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(mContext).add(postRequest);
    }
    public void getDeviceInfo(OnResponse<UniverSelObjct> onResponse, String... strParams) {
        ApiRequest mRequest = RetrofitRequest.getRetrofitInstance(1, 2).create(ApiRequest.class);
        mRequest.getDeviceInfo(strParams[0],strParams[1],strParams[2]).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if (response != null) {
                        try {
                             DeviceInfoBean mBean = new Gson().fromJson(response.body().toString(),DeviceInfoBean.class);
                             if(mBean !=null) {
                                 onResponse.onSuccess(new UniverSelObjct(mBean, Links.SB_API_GET_DEVICE_INFO, "true", ""));
                             }else{
                                 onResponse.onError(Links.SB_API_GET_DEVICE_INFO, mContext.getResources().getString(R.string.something_wrong));
                             }
                        } catch (Exception e) {
                            e.printStackTrace();
                            onResponse.onError(Links.SB_API_GET_DEVICE_INFO, mContext.getResources().getString(R.string.something_wrong));
                        }
                    } else {
                        onResponse.onError(Links.SB_API_GET_DEVICE_INFO, mContext.getResources().getString(R.string.something_wrong));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                onResponse.onError(Links.SB_API_GET_DEVICE_INFO, mContext.getResources().getString(R.string.something_wrong));
            }
        });
    }

}
