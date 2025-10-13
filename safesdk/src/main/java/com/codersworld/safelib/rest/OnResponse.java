package com.codersworld.safelib.rest;


public interface OnResponse<T> {
    void onSuccess(T response);
    void onError(String type,String error);
}
