package com.codersworld.safelib.helpers;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.codersworld.configs.urls.common.Links;
import com.codersworld.configs.urls.tt.tt;
import com.codersworld.configs.urls.vehicletrack.membocool;
import com.codersworld.safelib.database.DatabaseHelper;
import com.codersworld.safelib.database.DatabaseManager;
import com.codersworld.safelib.database.dao.OTPDao;
import com.codersworld.safelib.listeners.OnAuthListener;
import com.codersworld.safelib.listeners.QueryExecutor;
import com.codersworld.safelib.beans.AccountInfo;
import com.codersworld.safelib.beans.GenerateOTP;
import com.codersworld.safelib.beans.LoginBean;
import com.codersworld.safelib.rest.ApiCall;
import com.codersworld.configs.rest.ApiRequest;
import com.codersworld.safelib.rest.OnResponse;
import com.codersworld.safelib.rest.UniverSelObjct;
import com.codersworld.safelib.rest.ttlock.RetrofitAPIManager;
import com.codersworld.safelib.utils.CommonMethods;
import com.codersworld.safelib.utils.SFProgress;
import com.dhanukaelectrotech.safesdk.R;
import com.google.gson.Gson;
import com.ttlock.bl.sdk.util.DigitUtil;
import com.ttlock.bl.sdk.util.GsonUtil;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import com.codersworld.configs.urls.common.Constants;
/**
 * Created by Mr.Mad on 02/02/2023.
 */
public class JKHelper extends Application implements OnResponse<UniverSelObjct> {
    Activity mActivity;
    int fromType = 0;
    String strPassword = "";
    OnAuthListener mListener;
    int mAuthCount = 0;

    public void makeTTAuthentication(Activity ctx, int fromType, OnAuthListener mListener) {
        this.mActivity = ctx;
        this.mListener = mListener;
        this.fromType = fromType;
        LoginBean.InfoBean mBeanUser = UserSessions.getUserInfo(ctx);
        String ClientID = "";
        String ClientSecret = "";
        String username = tt.ttuname;
        strPassword = tt.ttpass;
        if (mBeanUser != null) {
            strPassword = (CommonMethods.isValidString(mBeanUser.getBtmailpwd())) ? DigitUtil.getMD5(mBeanUser.getBtmailpwd()) : tt.ttpass;
            username = (CommonMethods.isValidString(mBeanUser.getBtmainuserId())) ? mBeanUser.getBtmainuserId() : tt.ttuname;
            ClientID = (CommonMethods.isValidString(mBeanUser.getClientid())) ? mBeanUser.getClientid() : Links.TT_CLIENT_ID;
            ClientSecret = (CommonMethods.isValidString(mBeanUser.getClientsecret())) ? mBeanUser.getClientsecret() : Links.TT_CLIENT_SECRET;
        }
        new ApiCall(ctx).ttlockAuth(this, ClientID, ClientSecret, username, strPassword);
    }

    int mCounter = 0;
    ProgressDialog mProgressDialog = null;

    public void setCounter(Activity ctx, int mCounter) {
        this.mCounter = mCounter;
        if (mCounter > 0) {
            try {
                checkProgress(0);
                mProgressDialog = SFProgress.showTempProgressDialog(ctx, true);
            } catch (Exception e) {
            }
        }
    }

    public void checkProgress(int action) {
        if (action > 0) {
            mCounter--;
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            if (mCounter <= 0) {
                mProgressDialog.dismiss();
            }
        }
    }

    int id = 0;
    public void uploadGeneratedOTP(Activity ctx) {
        DatabaseManager.initializeInstance(new DatabaseHelper(ctx));
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        OTPDao pd = new OTPDao(database, ctx);
        ArrayList<GenerateOTP> mListGenerateOTP = pd.selectAll();
        if (CommonMethods.isValidArrayList(mListGenerateOTP)) {
            GenerateOTP mOTP = mListGenerateOTP.get(0);
            id = mOTP.getId();
            this.mActivity = ctx;
            String params = Links.SB_OPEN_LOCK + "&ContactID=" + mOTP.getContactId() + "&DeviceID=" + mOTP.getDeviceId() + "&Rpwd=" + mOTP.getRpwd() + "&GenratePwd=" + mOTP.getOtp() + "&VehicleNo=" + mOTP.getVehicalNo() + "&type=" + mOTP.getType() + "&OtpGenerationTime=" + mOTP.getOtpGenerateTime() + "&Flag=" + "offline";
            AESHelpers mAESHelper = new AESHelpers();
            String encParam = mAESHelper.safeEncryption(ctx, params);
            new ApiCall(ctx).uploadGeneratedOTP(this, encParam, mOTP.getId() + "");
        }
    }
    public void updateLockData(Activity ctx,String lockData,String lockMac, String lockId) {
        this.mActivity = ctx;
        AESHelpers mAESHelper = new AESHelpers();
        String encParam = mAESHelper.safeEncryption(mActivity, membocool.getUpdateDataParams(lockData, lockMac, lockId,UserSessions.getUserInfo(mActivity).getUsername()));
        new ApiCall(ctx).updateLockData(this, encParam);
    }


    public String generateLocalOTP(Activity mActivity, String... mParams) {
        final ArrayList<GenerateOTP> mListOTP = new ArrayList<GenerateOTP>();
        this.mActivity = mActivity;
        Double tokenNumber;
        Double tokenNumberOther;
        int randomNumber = Integer.parseInt(mParams[2]);
        String strOTP = "";
        if (mParams[0].startsWith("66")) {//= mParam[0]
            tokenNumber = randomNumber * 0.8; // C==INT(B2*0.8)
            tokenNumber = Math.floor(tokenNumber);
            tokenNumber = tokenNumber + 15;  //D
            tokenNumber = tokenNumber * 0.4; // E
            double aar = Math.floor(tokenNumber);
            String str1 = String.format("%.0f", aar);
            int tokenNumber1 = Integer.parseInt(str1);
            tokenNumber1 = tokenNumber1 + 1;// F
            strOTP = String.valueOf(tokenNumber1);
            strOTP = strOTP.substring(strOTP.length() - 5);
            tokenNumber1 = Integer.parseInt(strOTP);
            long val = tokenNumber1;
            long val1 = val * val;
            strOTP = String.valueOf(val1);
            strOTP = strOTP.substring(strOTP.length() - 3);
            val1 = Integer.parseInt(strOTP);
            val1 = val1 * Integer.parseInt(mParams[1]);
            strOTP = String.valueOf(val1); // I
            strOTP = strOTP.substring(strOTP.length() - 6);
        } else if (mParams[0].startsWith("67")) {//lockId = mParam[0]
            tokenNumber = randomNumber * 0.6;
            tokenNumber = Math.floor(tokenNumber);
            String str0 = String.format("%.0f", tokenNumber);
            int tokenNumber1 = Integer.parseInt(str0);
            tokenNumber1 = tokenNumber1 + 15;
            long deviceCodest = Integer.parseInt(mParams[1]);
            long tk = tokenNumber1 ^ deviceCodest;
            tokenNumberOther = randomNumber * 0.4;
            double aar = Math.floor(tokenNumberOther);
            String str1 = String.format("%.0f", aar);
            int tokenNumber2 = Integer.parseInt(str1);
            tokenNumber2 = tokenNumber2 + 1;
            long tkOther = tokenNumber2 ^ deviceCodest;
            long totalValue = tk * tkOther;
            String cal = "4294967296";
            long val = Long.parseLong(cal);
            long finaltotalValue = totalValue % val;
            String cal2 = "1000000";
            val = Long.parseLong(cal2);
            finaltotalValue = finaltotalValue % val;
            strOTP = String.valueOf(finaltotalValue);
        }

        String otpGenerateTime = CommonMethods.getCurrentFormatedDate("MM-dd-yyyy HH:mm");
        GenerateOTP generateOTP = new GenerateOTP();
        if (strOTP.length() == 1) {
            strOTP = "00000" + strOTP;
        } else if (strOTP.length() == 2) {
            strOTP = "0000" + strOTP;
        } else if (strOTP.length() == 3) {
            strOTP = "000" + strOTP;
        } else if (strOTP.length() == 4) {
            strOTP = "00" + strOTP;
        } else if (strOTP.length() == 5) {
            strOTP = "0" + strOTP;
        }
        generateOTP.setOtp(strOTP);
        generateOTP.setRpwd(mParams[2]);
        generateOTP.setOtpGenerateTime(otpGenerateTime);
        generateOTP.setContactId(mParams[3]);
        generateOTP.setDeviceId(mParams[4]);
        generateOTP.setVehicalNo(mParams[5]);
        generateOTP.setType("OPENGATE");
        mListOTP.add(generateOTP);
        DatabaseManager.getInstance().executeQuery(new QueryExecutor() {
            @Override
            public void run(SQLiteDatabase database) {
                OTPDao dao = new OTPDao(database, mActivity);
                ArrayList<GenerateOTP> list = mListOTP;
                dao.insert(list);
                DatabaseManager.getInstance().closeDatabase();
            }
        });
        return strOTP;
    }


    @Override
    public void onSuccess(UniverSelObjct response) {
        try {
            switch (response.getMethodname()) {

                case Links.SB_OPEN_LOCK:
                    try {
                        String strResponse = response.getResponse().toString();
                        if (CommonMethods.isValidString(strResponse)) {
                            JSONObject jsonObject = new JSONObject(strResponse);
                            if (jsonObject.getInt("success") == 1) {
                                DatabaseManager.initializeInstance(new DatabaseHelper(mActivity));
                                SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
                                OTPDao pd = new OTPDao(database, mActivity);
                                pd.deleteOTPById(id);
                                uploadGeneratedOTP(mActivity);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;

                case Links.SB_API_TTLOCK_AUTH_TOKEN:
                    try {
                        Boolean isError = true;
                        String msg = "";
                        AccountInfo mAccountInfo = (AccountInfo) response.getResponse();
                        if (mAccountInfo != null) {
                            if (mAccountInfo.getErrcode() == 0) {
                                mAccountInfo.setMd5Pwd(strPassword);
                                String str = new Gson().toJson(mAccountInfo);
                                UserSessions.saveTTAccountInfo(mActivity, str);
                                if (fromType == 1) {//
                                    // startActivity(Intent(this@LoginActivity, UserLockActivity::class.java))
                                } else if (fromType == 2) {//
                                    //startActivity(Intent(this@LoginActivity, GuestRoomList::class.java))
                                } /*else if (fromType == 3) {//Login Screen with no action
                                   // CommonMethods.moveWithClear(mActivity, LocksActivity.class);
                                }*/ else if (mListener != null) {
                                    mListener.onAuth(mAccountInfo);
                                }
                                isError = false;
                            } else {
                                msg = (CommonMethods.isValidString(mAccountInfo.getErrmsg())) ? mAccountInfo.getErrmsg() : mActivity.getString(R.string.error_tt_auth);
                            }
                        } else {
                            msg = (CommonMethods.isValidString(response.getMsg())) ? response.getMsg() : mActivity.getString(R.string.error_tt_auth);
                        }
                        if (isError) {
                            if (mAuthCount < 2) {
                                mAuthCount++;
                                makeTTAuthentication(mActivity, fromType, mListener);
                            } else {
                                if (fromType == 3 || fromType == 4) {
                                    if (mListener != null) {
                                        mListener.onAuth(mAccountInfo);
                                    }
                                } else {
                                    CommonMethods.errorDialog(mActivity, (CommonMethods.isValidString(msg)) ? msg : mActivity.getString(R.string.error_tt_auth), mActivity.getString(R.string.app_name), mActivity.getString(R.string.media_picker_ok));
                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String type, String error) {
        try {
            switch (type) {
                case Links.SB_OPEN_LOCK:
                    uploadGeneratedOTP(mActivity);
                    break;
                case Links.SB_API_TTLOCK_AUTH_TOKEN:
                    try {
                        if (mAuthCount < 2) {
                            mAuthCount++;
                            makeTTAuthentication(mActivity, fromType, mListener);
                        } else {
                            if (fromType == 3 || fromType == 4) {
                                if (mListener != null) {
                                    mListener.onAuth(null);
                                }
                            } else {
                                CommonMethods.errorDialog(mActivity, mActivity.getString(R.string.error_tt_auth), mActivity.getString(R.string.app_name), mActivity.getString(R.string.media_picker_ok));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ProgressDialog mProgressBar;
    public AccountInfo accountInfo;

    public void auth(int i, Activity ctx, OnAuthListener listnr) {
        mListener = listnr;

        // Preferencehelper prefs = new Preferencehelper(ctx);
        mProgressBar = new ProgressDialog(ctx);
        mProgressBar.setTitle("SafeOBuddy");
        mProgressBar.setMessage("authenticating...");
        mProgressBar.show();
        mProgressBar.setCancelable(false);

        ApiRequest apiService = RetrofitAPIManager.provideClientApi(0);
        String account = tt.ttuname;//prefs.getPrefsbtAdminUserName();
        String password = tt.ttpass;//DigitUtil.getMD5(prefs.getPrefsbtAdminPass());
        String ClientID = "";
        String ClientSecret = "";
        ClientID = Links.TT_CLIENT_ID;
        ClientSecret = Links.TT_CLIENT_SECRET;
        Call<String> call = apiService.ttlockAuth(ClientID, ClientSecret, account, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                String json = response.body();
                mProgressBar.dismiss();
                //pDialog.dismiss();
                accountInfo = GsonUtil.toObject(json, AccountInfo.class);
                if (accountInfo != null) {
                    if (accountInfo.errcode == 0) {
                        accountInfo.setMd5Pwd(password);

                         //makeToast(accountInfo.toString());
                        SharedPreferences sharedPreferences = ctx.getSharedPreferences("accountInfo", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("accesstoken", accountInfo.getAccess_token());
                        myEdit.putString("login_id", account);
                        myEdit.apply();
                        if (i == 1) {
/**/
                        } else if (i == 2) {
/*
                            Intent intent = new Intent(ctx, GuestRoomList.class);
                            ctx.startActivity(intent);
                            ctx.finish();
*/
                        } else if (i == 3) {
/*
                            Intent intent = new Intent(ctx, AddLockType.class);
                            ctx.startActivity(intent);
                            ctx.finish();
*/
                        } else {
                            mListener.onAuth(accountInfo);
                        }
                    } else {
                        if (mAuthCount < 2) {
                            mAuthCount++;
                            auth(i, ctx, listnr);
                        } else {
                            if (i == 4) {
                                mListener.onAuth(accountInfo);
                            }
                            JKHelper.openCommentDialog(ctx, "Either User is not created for Lock, or the user  is not active, please contact to administration \nThanks");//at : +91-7042741404
                            // makeToast(accountInfo.errmsg);
                            Toast.makeText(ctx, accountInfo.errmsg, Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    if (mAuthCount < 2) {
                        mAuthCount++;
                        auth(i, ctx, listnr);
                    } else {
                        if (i == 4) {
                            mListener.onAuth(accountInfo);
                        }
                        JKHelper.openCommentDialog(ctx, "Either User is not created for Lock, or the user  is not active, please contact to administration \nThanks");//at : +91-7042741404
                        //makeToast(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //makeToast(t.getMessage());
                if (mAuthCount < 2) {
                    mAuthCount++;
                    auth(i, ctx, listnr);
                } else {
                    if (i == 4) {
                        mListener.onAuth(accountInfo);
                    }

                    Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
                    JKHelper.openCommentDialog(ctx, "Either User is not created for Lock, or the user  is not active, please contact to administration \nThanks");//at : +91-7042741404
                    mProgressBar.dismiss();
                    //pDialog.dismiss();
                }
            }
        });


    }
    public static void openCommentDialog(final Context mContext, String param) {
        final String comments = param;
        Typeface font = Typeface.createFromAsset(
                mContext.getAssets(),
                "fonts/sansation-bold.ttf");
        final Dialog Localdialog = new Dialog(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        Localdialog.setContentView(R.layout.dialog_comment);
        Window window = Localdialog.getWindow();
        window.setWindowAnimations(R.style.DialogAnimation);
        final TextView txt_number = (TextView) Localdialog.findViewById(R.id.cmttext);
        final ImageView icon_close = (ImageView) Localdialog.findViewById(R.id.icon_close);
        final TextView txt_title = (TextView) Localdialog.findViewById(R.id.txt_title);
        txt_number.setTypeface(font);
        txt_number.setText(comments);
        icon_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Localdialog.dismiss();
            }
        });
        Localdialog.show();
    }

}
