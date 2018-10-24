package com.example.asus.jddemozhangjiangxia.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterImpl;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.ui.activtiy.BackActivity;
import com.example.asus.jddemozhangjiangxia.ui.activtiy.LoginActivity;
import com.example.asus.jddemozhangjiangxia.ui.activtiy.XiangQingActivity;
import com.example.asus.jddemozhangjiangxia.ui.adapter.OneAdapterbbb;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;


public class fiveFragment extends Fragment implements IContract.IView {
    @BindView(R.id.my_img_tou)
    ImageView myImgTou;
    @BindView(R.id.my_login)
    TextView myLogin;
    @BindView(R.id.my_tuijian_recycler)
    RecyclerView myTuijianRecycler;
    Unbinder unbinder;
    private PresenterImpl presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, container, false);
        unbinder = ButterKnife.bind(this, view);


        presenter = new PresenterImpl();
        presenter.attData(this);
        presenter.infoData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        SharedPreferences sp = getActivity().getSharedPreferences("userInfo", 0);
        String uid = sp.getString("uid", "0");
        Log.i("aaa", "init: "+uid);
        String username = sp.getString("username", "|");
        Log.e("TAG1", "" + username);
        if (username.equals("|")) {
            Toast.makeText(getActivity(), "未登录", Toast.LENGTH_SHORT).show();
            myLogin.setText("登录/注册");
        } else {
            Toast.makeText(getActivity(), "已登录", Toast.LENGTH_SHORT).show();

            myLogin.setText(username);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_img_tou, R.id.my_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_img_tou:
                break;
            case R.id.my_login:

                SharedPreferences sp = getActivity().getSharedPreferences("userInfo", 0);
                String username = sp.getString("username", "|");
                if (username.equals("|")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(), BackActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100 && resultCode == 200) {
//            String name = data.getStringExtra("name");
//            myLogin.setText(name);
//        }
//    }

    @Override
    public void showData(InfoBean msg) {
        InfoBean.DataBean data = msg.getData();
        InfoBean.DataBean.TuijianBean tuijian = data.getTuijian();
        final List<InfoBean.DataBean.TuijianBean.ListBeanX> tlist = tuijian.getList();

        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        myTuijianRecycler.setLayoutManager(gridLayoutManager3);

        OneAdapterbbb oneAdapterbbb = new OneAdapterbbb(getContext(), tlist);
        myTuijianRecycler.setAdapter(oneAdapterbbb);
        oneAdapterbbb.setOnitmeclickLentener(new OneAdapterbbb.onitmeclickLentener() {
            @Override
            public void onitmeclick(int itemCount) {
                int pid = tlist.get(itemCount).getPid();
                Intent intent1 = new Intent(getActivity(), XiangQingActivity.class);
                intent1.putExtra("pid", pid);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.deleteData(this);


    }

}
