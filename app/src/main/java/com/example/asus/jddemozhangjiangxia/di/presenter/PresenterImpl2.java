package com.example.asus.jddemozhangjiangxia.di.presenter;

import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;
import com.example.asus.jddemozhangjiangxia.di.model.ModelImplTwo;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractTwo;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PresenterImpl2 implements IContractTwo.IPresenter2<IContractTwo.IView2> {
    IContractTwo.IView2 iView2;

    private IContractTwo.IModel2 iModel2;
    private WeakReference<IContractTwo.IView2> iView2WeakReference;
    private WeakReference<IContractTwo.IModel2> iModel2WeakReference;

    @Override
    public void attData(IContractTwo.IView2 iView2) {
        this.iView2 =iView2;
        iModel2 = new ModelImplTwo();
        iView2WeakReference = new WeakReference<>(iView2);
        iModel2WeakReference = new WeakReference<>(iModel2);
    }

    @Override
    public void deleteData(IContractTwo.IView2 iView2) {
          iView2WeakReference.clear();
          iModel2WeakReference.clear();
    }

    @Override
    public void infoData() {
      iModel2.requestData(new IContractTwo.IModel2.onCallBack() {

          @Override
          public void stringMsg(TwoBeanOne Msg1) {
              iView2.showData(Msg1);
          }
      });
    }

    @Override
    public void reqFenData(int cid) {
        Observable<TwoBeanTwo> fenObservable = iModel2.reqFenData(cid);
        fenObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TwoBeanTwo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TwoBeanTwo twoBeanTwo) {
                        iView2.showData(twoBeanTwo);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
