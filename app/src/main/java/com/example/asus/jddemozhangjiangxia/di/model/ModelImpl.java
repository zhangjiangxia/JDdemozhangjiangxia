package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ModelImpl  implements IContract.IModel {
    @Override
    public void requestData(final onCallBack onCallBack) {

       OkHttpUtils.getInstance()
               .apiService.getNews()
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(new Observer<InfoBean>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                   }

                   @Override
                   public void onNext(InfoBean infoBean) {

                       onCallBack.stringMsg(infoBean);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });


    }
}
