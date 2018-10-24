package com.example.asus.jddemozhangjiangxia.ui.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.GwcBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterGwc;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractGwc;
import com.example.asus.jddemozhangjiangxia.ui.adapter.GwcAdapter;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FourFragment extends Fragment implements IContractGwc.IViewGwc ,View.OnClickListener{


    @BindView(R.id.m_expandable)
    ExpandableListView mExpandable;
    @BindView(R.id.m_check)
    CheckBox mCheck;
    @BindView(R.id.m_price)
    TextView mPrice;
    @BindView(R.id.m_num)
    TextView mNum;
    @BindView(R.id.m_jiesuan)
    Button mJiesuan;
    Unbinder unbinder;
    private IContractGwc.IPresenterGwc iPresenterGwc;
    private GwcAdapter gwcAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        iPresenterGwc = new PresenterGwc();
        iPresenterGwc.attData(this);

        unbinder = ButterKnife.bind(this, view);

        //initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("userInfo", 0);
        String uid = sp.getString("uid", "0");
        if (!uid.equals("0")) {
            Log.e("sss", "initData: " + uid);
            iPresenterGwc.infoData(uid);

        } else {
            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void gouNull(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.m_check, R.id.m_jiesuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_check:
                boolean allChacked = gwcAdapter.isAllChacked();
                gwcAdapter.chageAll(!allChacked);
                gwcAdapter.notifyDataSetChanged();
                refreshall();
                break;
            case R.id.m_jiesuan:
                break;
        }
    }



    @Override
    public void showData(GwcBean msg) {
        List<GwcBean.DataBean> data = msg.getData();

        gwcAdapter = new GwcAdapter(data, getActivity());
        mExpandable.setAdapter(gwcAdapter);

        for (int i = 0; i <data.size() ; i++) {
            mExpandable.expandGroup(i);
        }

        gwcAdapter.setOnChangeLinsenter(new GwcAdapter.onChangeLinsenter() {
            @Override
            public void shopChang(int i) {
                boolean shop = gwcAdapter.isShop(i);
                gwcAdapter.shopall(i,!shop);
                gwcAdapter.notifyDataSetChanged();
                refreshall();
            }

            @Override
            public void goodChang(int i, int i1) {
                gwcAdapter.goodselect(i,i1);
                gwcAdapter.notifyDataSetChanged();
                refreshall();
            }

            @Override
            public void goodChang(int i, int i1, int num) {
                gwcAdapter.numshange(i,i1,num);
                gwcAdapter.notifyDataSetChanged();
                refreshall();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //全选按钮的点击事件
            case R.id.m_check:
                //调用适配器的方法状态 并赋值
                boolean allChecked = gwcAdapter.isAllChacked();
                gwcAdapter.chageAll(!allChecked);
                //刷新适配器
                gwcAdapter.notifyDataSetChanged();
                //调取刷新全选的方法
                refreshall();
                break;
        }
    }


    private void refreshall() {
        boolean allChacked = gwcAdapter.isAllChacked();
        mCheck.setChecked(allChacked);

        double allprice = gwcAdapter.allPrice();
        mPrice.setText("总价为：¥"+allprice);
        int sunm=gwcAdapter.allNum();
        mNum.setText("共"+sunm+"件商品");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenterGwc.deleteData(this);
    }

}
