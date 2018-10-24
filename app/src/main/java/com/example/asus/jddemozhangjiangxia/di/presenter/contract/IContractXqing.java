package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.XqingBean;

import io.reactivex.Observable;

public interface IContractXqing {
    interface IViewXqing {
        void showData(XqingBean msg);
    }

    interface IModelXqing {

        Observable<XqingBean> requestData(String pid);

    }

    interface IPresenterXqing<IViewXqing> {
        void attData(IViewXqing iViewXqing);

        void deleteData(IViewXqing iViewXqing);

        void infoData(String pid);

    }

}
