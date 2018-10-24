package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;
import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils2;
import com.example.asus.jddemozhangjiangxia.di.model.service.ApiServiceTwo;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractTwo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ModelImplTwo implements IContractTwo.IModel2 {


    @Override
    public void requestData(final onCallBack onCallBack) {

        OkHttpUtils2.getInstance()
                .apiServiceTwo.getzuo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TwoBeanOne>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TwoBeanOne twoBeanOne) {
                        onCallBack.stringMsg(twoBeanOne);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public Observable<TwoBeanTwo> reqFenData(int cid) {
        ApiServiceTwo apiServiceTwo = OkHttpUtils2.getInstance().apiServiceTwo;
        Observable<TwoBeanTwo> getyou = apiServiceTwo.getyou(cid + "");
        return getyou;
    }
}
