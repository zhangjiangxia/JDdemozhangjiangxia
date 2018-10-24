package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;

import io.reactivex.Observable;

public interface IContractTwo {
    interface IView2 {

        void showData(TwoBeanOne msg1);
        void showData(TwoBeanTwo msg2);
    }

    interface IModel2 {
        Observable<TwoBeanTwo> reqFenData(int cid);

        interface onCallBack {
            void stringMsg(TwoBeanOne twoBeanOne);
        }

        void requestData(onCallBack onCallBack);
    }

    interface IPresenter2<IView2> {
        void attData(IView2 iView);

        void deleteData(IView2 iView);

        void infoData();

        void reqFenData(int cid);
    }

}
