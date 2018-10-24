package com.example.asus.jddemozhangjiangxia.di.presenter;

import android.util.Log;

import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelSearch;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractSearch;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class presenteSearch implements IContractSearch.IPresenterSearch<IContractSearch.IViewSearch> {
    IContractSearch.IViewSearch  iViewSearch;
    private IContractSearch.IModelSearch iModelSearch;
    private WeakReference<IContractSearch.IViewSearch> iViewSearchWeakReference;
    private WeakReference<IContractSearch.IModelSearch> iModelSearchWeakReference;

    @Override
    public void attData(IContractSearch.IViewSearch iViewSearch) {
       this.iViewSearch  =iViewSearch;
       iModelSearch = new ModelSearch();
        iViewSearchWeakReference = new WeakReference<>(iViewSearch);
        iModelSearchWeakReference = new WeakReference<>(iModelSearch);

    }

    @Override
    public void deleteData(IContractSearch.IViewSearch iViewSearch) {
        iViewSearchWeakReference.clear();
        iModelSearchWeakReference.clear();
    }

    @Override
    public void infoData(String keywords) {
        Observable<SearchBean> searchBeanObservable = iModelSearch.requestData(keywords);
         searchBeanObservable.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<SearchBean>() {

                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(SearchBean searchBean) {
                     iViewSearch.showData(searchBean);
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
