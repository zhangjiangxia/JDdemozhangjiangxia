package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.di.model.ModelImpl;

import java.lang.ref.WeakReference;

public class PresenterImpl implements IContract.IPresenter<IContract.IView> {
    IContract.IView iView;
    private IContract.IModel iModel;
    private WeakReference<IContract.IModel> iModelWeakReference;
    private WeakReference<IContract.IView> iViewWeakReference;

    @Override
    public void attData(IContract.IView iView) {
        this.iView = iView;
        iModel = new ModelImpl();
        iModelWeakReference = new WeakReference<>(iModel);
        iViewWeakReference = new WeakReference<>(iView);

    }

    @Override
    public void deleteData(IContract.IView iView) {
        iModelWeakReference.clear();
        iViewWeakReference.clear();
    }

    @Override
    public void infoData() {
        iModel.requestData(new IContract.IModel.onCallBack() {
            @Override
            public void stringMsg(InfoBean Msg) {
                iView.showData(Msg);
            }
        });
    }
}
