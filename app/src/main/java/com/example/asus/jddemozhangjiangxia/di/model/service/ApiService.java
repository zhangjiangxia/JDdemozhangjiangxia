package com.example.asus.jddemozhangjiangxia.di.model.service;

import com.example.asus.jddemozhangjiangxia.data.bean.AddBean;
import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.data.bean.LoginBean;
import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.example.asus.jddemozhangjiangxia.data.bean.XqingBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("home/getHome")
    Observable<InfoBean> getNews();

    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> getlogin(@Field("mobile") String mobile, @Field("password") String password);


    //注册
    @FormUrlEncoded
    @POST("user/reg")
    Observable<RagBean> getrag(@Field("mobile") String mobile, @Field("password") String password);

    //搜索

//    ?keywords=笔记本&page=1
    @FormUrlEncoded
    @POST("product/searchProducts")
    Observable<SearchBean> getsousuo(@Field("keywords") String keywords, @Field("page") String page);

//查询购物车
    @FormUrlEncoded
    @POST("product/getCarts")
    Observable<ResponseBody> getabout(@Field("uid") String uid);

  //商品详情
  @GET("product/getProductDetail")
  Observable<XqingBean> getxiangqing(@Query("pid")String pid);
   // http://www.zhaoapi.cn/product/getProductDetail?pid=13

    //添加购物车
    //user/addAddr?uid=71&addr=北京市昌平区金域国际1-1-1&mobile=18612991023&name=kson
    @FormUrlEncoded
 @POST("product/addCart")
    Observable<AddBean> getaddgwc(@Field("uid") String uid,@Field("pid") String pid);
}
