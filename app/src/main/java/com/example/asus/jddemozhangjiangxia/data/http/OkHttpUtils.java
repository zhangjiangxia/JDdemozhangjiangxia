package com.example.asus.jddemozhangjiangxia.data.http;

import com.example.asus.jddemozhangjiangxia.di.model.Api;
import com.example.asus.jddemozhangjiangxia.di.model.service.ApiService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpUtils {
    private static final OkHttpUtils ourInstance = new OkHttpUtils();
    public   ApiService apiService;

    public static OkHttpUtils getInstance() {
        return ourInstance;
    }

    private OkHttpUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.GETURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        apiService.getlogin("18611520300","123123");

    }
}
