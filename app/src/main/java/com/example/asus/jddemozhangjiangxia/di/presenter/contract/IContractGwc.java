package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface IContractGwc {
    interface IViewGwc{
        void showData(GwcBean msg);

        void gouNull(String s);
    }

    interface IModelGwc {

        Observable<ResponseBody> requestData(String uid);
    }

    interface IPresenterGwc<IViewGwc> {
        void attData(IViewGwc iViewGwc);

        void deleteData(IViewGwc iViewGwc);

        void infoData(String uid);

    }
}
