package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelGwc;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractGwc;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class PresenterGwc implements IContractGwc.IPresenterGwc<IContractGwc.IViewGwc> {
    IContractGwc.IViewGwc iViewGwc;
    private IContractGwc.IModelGwc iModelGwc;
    private WeakReference<IContractGwc.IViewGwc> iViewGwcWeakReference;
    private WeakReference<IContractGwc.IModelGwc> iModelGwcWeakReference;

    @Override
    public void attData(IContractGwc.IViewGwc iViewGwc) {
        this.iViewGwc  =iViewGwc;
        iModelGwc = new ModelGwc();
        iViewGwcWeakReference = new WeakReference<>(iViewGwc);
        iModelGwcWeakReference = new WeakReference<>(iModelGwc);


    }

    @Override
    public void deleteData(IContractGwc.IViewGwc iViewGwc) {
      iViewGwcWeakReference.clear();
      iModelGwcWeakReference.clear();
    }

    @Override
    public void infoData(String uid) {
        Observable<ResponseBody> beanObservable = iModelGwc.requestData(uid);
        beanObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            if (string.equals("null")){
                                iViewGwc.gouNull("您的购物车空空如也，请添加购物车");
                            }else {
                                Gson gson = new Gson();
                                GwcBean gwcBean = gson.fromJson(string, GwcBean.class);
                                iViewGwc.showData(gwcBean);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
