package com.example.asus.jddemozhangjiangxia.data.http;

import com.example.asus.jddemozhangjiangxia.di.model.ApiTwo;
import com.example.asus.jddemozhangjiangxia.di.model.service.ApiServiceTwo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpUtils2 {
    private static final OkHttpUtils2 ourInstance = new OkHttpUtils2();
    public ApiServiceTwo apiServiceTwo;


    public static OkHttpUtils2 getInstance() {
        return ourInstance;
    }

    private OkHttpUtils2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiTwo.TWOURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiServiceTwo = retrofit.create(ApiServiceTwo.class);


    }
}
