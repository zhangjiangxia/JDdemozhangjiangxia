package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;

public interface IContractRag {
    interface IViewRag{
        void showData(RagBean msg);
    }
    interface IModelRag{
        interface onCallBack{
            void  stringMsg(RagBean Msg);
        }
        void requestData(String ragname, String ragpwd, onCallBack onCallBack);
    }
    interface IPresenterRag<IView>{
        void attData(IView iView);
        void deleteData(IView iView);
        void infoData(String ragname, String ragpwd);
    }
}
