package com.example.asus.jddemozhangjiangxia.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanOne;
import com.example.asus.jddemozhangjiangxia.data.bean.TwoBeanTwo;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterImpl2;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractTwo;
import com.example.asus.jddemozhangjiangxia.ui.adapter.FenneibuAdapter;
import com.example.asus.jddemozhangjiangxia.ui.adapter.FenyeAdapter;
import com.example.asus.jddemozhangjiangxia.ui.adapter.TwoAdapter2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TwoFragment extends Fragment implements IContractTwo.IView2 {

    @BindView(R.id.two_RecyclerView)
    RecyclerView twoRecyclerView;
    @BindView(R.id.two_expandable)
    RecyclerView twoExpandable;
    Unbinder unbinder;
    private IContractTwo.IPresenter2 iPresenter2;
    private TwoAdapter2 twoAdapter2;

    private int cid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        unbinder = ButterKnife.bind(this, view);
        iPresenter2 = new PresenterImpl2();
        iPresenter2.attData(this);
        iPresenter2.infoData();
        return view;
    }


    @Override
    public void showData(TwoBeanTwo msg2) {
        List<TwoBeanTwo.DataBean> data = msg2.getData();

        twoExpandable.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        FenyeAdapter fenyeAdapter = new FenyeAdapter(data, getActivity());
        twoExpandable.setAdapter(fenyeAdapter);


    }


    @Override
    public void showData(final TwoBeanOne msg1) {
        final List<TwoBeanOne.DataBean> data = msg1.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        twoRecyclerView.setLayoutManager(linearLayoutManager);
        twoAdapter2 = new TwoAdapter2(data, getContext());
        twoRecyclerView.setAdapter(twoAdapter2);
        twoAdapter2.setOnItemClick(new TwoAdapter2.OnItemClickLisentener() {
            @Override
            public void ondianji(int layoutPosition) {
                cid = data.get(layoutPosition).getCid();
                iPresenter2.reqFenData(cid);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter2.deleteData(this);
    }


}
