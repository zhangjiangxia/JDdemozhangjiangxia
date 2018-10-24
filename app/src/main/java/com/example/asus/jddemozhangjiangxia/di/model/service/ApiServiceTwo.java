package com.example.asus.jddemozhangjiangxia.di.model.service;

import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceTwo {
    @GET("product/getCatagory")
    Observable<TwoBeanOne> getzuo();
    @GET("product/getProductCatagory")
    Observable<TwoBeanTwo> getyou(@Query("cid") String cid);
    @GET("product/getProducts")
    Observable<TwoBeanTwo> getyouxia();
   // https://www.zhaoapi.cn/product/getCatagory?Ishome=1

}
