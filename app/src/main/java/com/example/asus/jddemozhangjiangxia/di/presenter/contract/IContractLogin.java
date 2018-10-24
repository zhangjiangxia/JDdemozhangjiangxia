package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.LoginBean;

public interface IContractLogin {
    interface IViewLogin{
        void showData(LoginBean msg);
    }
    interface IModelLogin{
        interface onCallBack{
            void  stringMsg(LoginBean Msg);
        }
        void requestData(String logname,String logpwd,onCallBack onCallBack);
    }
    interface IPresenterLogin<IView>{
        void attData(IView iView);
        void deleteData(IView iView);
        void infoData(String logname, String logpwd);
    }
}
