package com.example.asus.jddemozhangjiangxia.di.model;

import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.example.asus.jddemozhangjiangxia.data.http.OkHttpUtils;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractRag;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractSearch;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ModelSearch implements IContractSearch.IModelSearch {


    @Override
    public Observable<SearchBean> requestData(String keywords) {
        Observable<SearchBean> getsousuo = OkHttpUtils.getInstance().apiService.getsousuo(keywords, "1");

       return getsousuo;
    }
}
