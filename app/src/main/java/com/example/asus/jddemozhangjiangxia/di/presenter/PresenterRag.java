package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;
import com.example.asus.jddemozhangjiangxia.di.model.ModelImpl;
import com.example.asus.jddemozhangjiangxia.di.model.ModelRag;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractRag;

import java.lang.ref.WeakReference;

public class PresenterRag implements IContractRag.IPresenterRag<IContractRag.IViewRag> {
    IContractRag.IViewRag iViewRag;
    private IContractRag.IModelRag iModelRag;
    private WeakReference<IContractRag.IViewRag> iViewRagWeakReference;
    private WeakReference<IContractRag.IModelRag> iModelRagWeakReference;

    @Override
    public void attData(IContractRag.IViewRag iViewRag) {
        this.iViewRag=iViewRag;
        iModelRag = new ModelRag();
        iViewRagWeakReference = new WeakReference<>(iViewRag);
        iModelRagWeakReference = new WeakReference<>(iModelRag);

    }

    @Override
    public void deleteData(IContractRag.IViewRag iViewRag) {
        iViewRagWeakReference.clear();
        iModelRagWeakReference.clear();
    }

    @Override
    public void infoData(String ragname, String ragpwd) {
       iModelRag.requestData(ragname,ragpwd,new IContractRag.IModelRag.onCallBack() {
           @Override
           public void stringMsg(RagBean Msg) {
               iViewRag.showData(Msg);
           }
       });



    }
}
