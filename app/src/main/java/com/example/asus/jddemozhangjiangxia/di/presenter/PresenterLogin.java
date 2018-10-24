package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.LoginBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelLogin;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractLogin;

import java.lang.ref.WeakReference;

public class PresenterLogin implements IContractLogin.IPresenterLogin<IContractLogin.IViewLogin> {
    IContractLogin.IViewLogin iViewLogin;
    private IContractLogin.IModelLogin iModelLogin;
    private WeakReference<IContractLogin.IViewLogin> iViewLoginWeakReference;
    private WeakReference<IContractLogin.IModelLogin> iModelLoginWeakReference;

    @Override
    public void attData(IContractLogin.IViewLogin iViewLogin) {
        this.iViewLogin = iViewLogin;
        iModelLogin = new ModelLogin();
        iViewLoginWeakReference = new WeakReference<>(iViewLogin);
        iModelLoginWeakReference = new WeakReference<>(iModelLogin);
    }

    @Override
    public void deleteData(IContractLogin.IViewLogin iViewLogin) {
       iViewLoginWeakReference.clear();
       iModelLoginWeakReference.clear();
    }

    @Override
    public void infoData(String logname, String logpwd) {
          iModelLogin.requestData(logname, logpwd, new IContractLogin.IModelLogin.onCallBack() {
              @Override
              public void stringMsg(LoginBean Msg) {
                  iViewLogin.showData(Msg);
              }
          });
    }
}
