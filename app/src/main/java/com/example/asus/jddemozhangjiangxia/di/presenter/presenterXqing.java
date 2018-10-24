package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.XqingBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelXqing;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractXqing;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class presenterXqing implements IContractXqing.IPresenterXqing<IContractXqing.IViewXqing> {
    IContractXqing.IViewXqing iViewXqing;
    private IContractXqing.IModelXqing iModelXqing;
    private WeakReference<IContractXqing.IViewXqing> iViewXqingWeakReference;
    private WeakReference<IContractXqing.IModelXqing> iModelXqingWeakReference;

    @Override
    public void attData(IContractXqing.IViewXqing iViewXqing) {
        this.iViewXqing = iViewXqing;
        iModelXqing = new ModelXqing();
    }

    @Override
    public void deleteData(IContractXqing.IViewXqing iViewXqing) {
        iViewXqingWeakReference = new WeakReference<>(iViewXqing);
        iModelXqingWeakReference = new WeakReference<>(iModelXqing);
    }

    @Override
    public void infoData(String pid) {
        Observable<XqingBean> xqingBeanObservable = iModelXqing.requestData(pid);

                 xqingBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XqingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XqingBean xqingBean) {
                        iViewXqing.showData(xqingBean);
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
