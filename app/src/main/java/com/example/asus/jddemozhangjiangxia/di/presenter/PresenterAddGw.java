package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.AddBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelAddGw;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractAddGw;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterAddGw implements IContractAddGw.IPresenterAddGw<IContractAddGw.IViewAddGw> {

    IContractAddGw.IViewAddGw iViewAddGw;
    private IContractAddGw.IModelAddGw iModelAddGw;
    private WeakReference<IContractAddGw.IViewAddGw> iViewAddGwWeakReference;
    private WeakReference<IContractAddGw.IModelAddGw> iModelAddGwWeakReference;

    @Override
    public void attData(IContractAddGw.IViewAddGw iViewAddGw) {
        this.iViewAddGw = iViewAddGw;
        iModelAddGw = new ModelAddGw();
        iViewAddGwWeakReference = new WeakReference<>(iViewAddGw);
        iModelAddGwWeakReference = new WeakReference<>(iModelAddGw);

    }

    @Override
    public void deleteData(IContractAddGw.IViewAddGw iViewAddGw) {
        iViewAddGwWeakReference.clear();
        iModelAddGwWeakReference.clear();
    }

    @Override
    public void infoData(String pid, String uid) {
        Observable<AddBean> addBeanObservable = iModelAddGw.requestData(pid, uid);
        addBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddBean addBean) {
                     iViewAddGw.showData(addBean);
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
