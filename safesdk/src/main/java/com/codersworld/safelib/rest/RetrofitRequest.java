package com.codersworld.safelib.rest;

import android.util.Log;

import com.codersworld.configs.urls.common.Links;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ttlock.bl.sdk.util.LogUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

    public static Retrofit retrofit;
     public static Retrofit getRetrofitInstance(int urlType,int converter) {
         retrofit=null;
         String baseUrl = "";
         if(urlType==1){
             baseUrl = Links.BASE_URL_MEMBOCOOL;
         }else if(urlType==3){
             baseUrl = Links.BASE_URL_TTLOCK;
         }else if(urlType==4){
             baseUrl = Links.BASE_URL_TTLOCK1;
         }else{
             baseUrl = Links.BASE_URL_MMTHINKBIZ;
         }
         if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            if(converter==1) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(getUnsafeOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }else if (converter==2){
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(getUnsafeOkHttpClient())
                        .addConverterFactory(new ToStringConverterFactory())
                        .build();
            }else{
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(getUnsafeOkHttpClient())
                        .addConverterFactory(
                                new Converter.Factory() {//Converter转换器
                                    @Override
                                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                                        return new Converter<ResponseBody, String>() {

                                            @Override
                                            public String convert(ResponseBody value) throws IOException {
                                                String json = value.string();
                                                //LogUtil.d("json:" + json);
                                                return json;
                                            }
                                        };
                                    }
                                }
                        )
                        .build();
            }
        }
        return retrofit;
    }
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("ApiResponse", message);
                }
            });
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static OkHttpClient getOkHttpClient() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
               // Log.e("ApiResponse", message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        okClientBuilder.connectTimeout(1000, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(1000, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(1000, TimeUnit.SECONDS);
        return okClientBuilder.build();
    }
}
