package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.AddBean;
import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractAddGw;

import io.reactivex.Observable;

public class ModelAddGw implements IContractAddGw.IModelAddGw {

    @Override
    public Observable<AddBean> requestData(String pid, String uid) {
        Observable<AddBean> getaddgwc = OkHttpUtils.getInstance().apiService.getaddgwc(uid, pid);
        return getaddgwc;
    }
}
