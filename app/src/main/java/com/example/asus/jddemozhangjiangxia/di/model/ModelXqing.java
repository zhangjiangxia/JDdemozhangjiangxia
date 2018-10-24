package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.XqingBean;
import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractXqing;

import io.reactivex.Observable;

public class ModelXqing implements IContractXqing.IModelXqing {


    @Override
    public Observable<XqingBean> requestData(String pid) {
        Observable<XqingBean> getxiangqing = OkHttpUtils.getInstance()
                .apiService.getxiangqing(pid);

        return getxiangqing;
    }
}
