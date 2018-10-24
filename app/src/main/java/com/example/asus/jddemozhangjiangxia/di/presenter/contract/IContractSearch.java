package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;

import io.reactivex.Observable;

public interface IContractSearch {
    interface IViewSearch {
        void showData(SearchBean msg);
    }

    interface IModelSearch {

        Observable<SearchBean> requestData(String keywords);
    }

    interface IPresenterSearch<IViewSearch> {
        void attData(IViewSearch iViewSearch);

        void deleteData(IViewSearch iViewSearch);

        void infoData(String keywords);

    }

}
