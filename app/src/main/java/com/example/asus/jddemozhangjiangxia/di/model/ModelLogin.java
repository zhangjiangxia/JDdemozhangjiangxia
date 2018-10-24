package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.LoginBean;
import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractLogin;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ModelLogin implements IContractLogin.IModelLogin {


    @Override
    public void requestData(String logname, String logpwd, final onCallBack onCallBack) {
        OkHttpUtils.getInstance().apiService.getlogin(logname, logpwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                     onCallBack.stringMsg(loginBean);
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