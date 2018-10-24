package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.AddBean;

import io.reactivex.Observable;

public interface IContractAddGw {
    interface IViewAddGw {
        void showData(AddBean msg);
    }

    interface IModelAddGw {

        Observable<AddBean> requestData(String pid, String uid);

    }

    interface IPresenterAddGw<IViewAddGw> {
        void attData(IViewAddGw iViewAddGw);

        void deleteData(IViewAddGw iViewAddGw);

        void infoData(String pid, String uid);

    }

}
