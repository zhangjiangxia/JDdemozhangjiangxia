package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;

import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractGwc;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


public class ModelGwc  implements IContractGwc.IModelGwc {


    @Override
    public Observable<ResponseBody> requestData(String uid) {
        Observable<ResponseBody> getabout = OkHttpUtils.getInstance().apiService.getabout(uid);
        return getabout;
    }
}
