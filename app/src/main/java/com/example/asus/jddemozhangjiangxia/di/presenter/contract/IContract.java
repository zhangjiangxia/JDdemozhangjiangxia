package com.example.asus.jddemozhangjiangxia.di.presenter.contract;

import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;

public interface IContract {
    interface IView{
      void showData(InfoBean msg);
    }
    interface IModel{
      interface onCallBack{
         void  stringMsg(InfoBean Msg);
        }
        void requestData(onCallBack onCallBack);
    }
    interface IPresenter<IView>{
       void attData(IView iView);
       void deleteData(IView iView);
       void infoData();

    }

}
