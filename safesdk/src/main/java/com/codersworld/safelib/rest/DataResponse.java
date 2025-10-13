package com.codersworld.safelib.rest;


interface DataResponse<T> {

      void onSuccess(T response);

      void onFaliure(String error);
}
