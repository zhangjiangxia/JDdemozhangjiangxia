package com.example.asus.jddemozhangjiangxia.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.InfoBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterImpl;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContract;
import com.example.asus.jddemozhangjiangxia.ui.activtiy.ActivitySs;
import com.example.asus.jddemozhangjiangxia.ui.activtiy.XiangQingActivity;
import com.example.asus.jddemozhangjiangxia.ui.adapter.OneAdapter;
import com.example.asus.jddemozhangjiangxia.ui.adapter.OneAdapteraa;
import com.example.asus.jddemozhangjiangxia.ui.adapter.OneAdapterbbb;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements IContract.IView {

    @BindView(R.id.one_Flybanner)
    FlyBanner oneFlybanner;
    @BindView(R.id.one_recyclerview)
    RecyclerView oneRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.pmd_dilpper)
    ViewFlipper pmdDilpper;
    @BindView(R.id.tv_miaosha_time)
    TextView tvMiaoshaTime;
    @BindView(R.id.tv_miaosha_shi)
    TextView tvMiaoshaShi;
    @BindView(R.id.tv_miaosha_minter)
    TextView tvMiaoshaMinter;
    @BindView(R.id.tv_miaosha_second)
    TextView tvMiaoshaSecond;
    @BindView(R.id.one_recyler2)
    RecyclerView oneRecyler2;
    @BindView(R.id.one_recyler3)
    RecyclerView oneRecyler3;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ss_rwm)
    ImageView ssRwm;
    @BindView(R.id.ss_search_imag)
    ImageView ssSearchImag;
    @BindView(R.id.ss_search_edit)
    EditText ssSearchEdit;
    private IContract.IPresenter iPresenter;
    private FragmentActivity oueactivity;
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        iPresenter = new PresenterImpl();
        iPresenter.attData(this);
        iPresenter.infoData();

        oneFlybanner = view.findViewById(R.id.one_Flybanner);
        unbinder = ButterKnife.bind(this, view);
        oueactivity = getActivity();
        startCountDown();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000/*,false*/);//传入false表示加载失败
            }
        });
        return view;
    }

    @Override
    public void showData(InfoBean msg) {
        InfoBean.DataBean data = msg.getData();

        List<InfoBean.DataBean.BannerBean> banner = data.getBanner();
//轮播
        List<String> imgesUrl = new ArrayList<>();
        for (int i = 0; i < banner.size(); i++) {
            String icon = banner.get(i).getIcon();
            imgesUrl.add(icon);
        }
        oneFlybanner.setImagesUrl(imgesUrl);
//数据展示
        List<InfoBean.DataBean.FenleiBean> fenlei = data.getFenlei();

        OneAdapter one = new OneAdapter(getActivity(), fenlei);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        oneRecyclerview.setLayoutManager(gridLayoutManager);
        oneRecyclerview.setAdapter(one);

        // new GridLayoutManager

        //跑马灯
        pmdDilpper.startFlipping();
        pmdDilpper.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_come_in));
        pmdDilpper.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_get_out));
        //京东秒杀
        InfoBean.DataBean.MiaoshaBean miaosha = data.getMiaosha();
        final List<InfoBean.DataBean.MiaoshaBean.ListBean> mslist = miaosha.getList();

        OneAdapteraa one2 = new OneAdapteraa(getActivity(), mslist);
        oneRecyler2.setAdapter(one2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false);
        one2.setOnitmeclickLentener(new OneAdapteraa.onitmeclickLentener() {
            @Override
            public void onitmeclick(int itemCount) {
                int pid = mslist.get(itemCount).getPid();
                Intent  intent2=new Intent(getActivity(),XiangQingActivity.class);
                intent2.putExtra("pid",pid);
                startActivity(intent2);
            }
        });
        oneRecyler2.setLayoutManager(gridLayoutManager1);

        //秒杀倒计时
        countDown();
        //为你推荐
        InfoBean.DataBean.TuijianBean tuijian = data.getTuijian();
        final List<InfoBean.DataBean.TuijianBean.ListBeanX> tlist = tuijian.getList();

        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        oneRecyler3.setLayoutManager(gridLayoutManager3);

        OneAdapterbbb oneAdapterbbb = new OneAdapterbbb(getContext(), tlist);

        oneRecyler3.setAdapter(oneAdapterbbb);
        oneAdapterbbb.setOnitmeclickLentener(new OneAdapterbbb.onitmeclickLentener() {
            @Override
            public void onitmeclick(int itemCount) {
                int pid = tlist.get(itemCount).getPid();
                Intent  intent1=new Intent(getActivity(),XiangQingActivity.class);
                intent1.putExtra("pid",pid);
                startActivity(intent1);
            }
        });

    }

            //秒杀倒计时
            private void countDown() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String format = df.format(curDate);
                StringBuffer buffer = new StringBuffer();
                String substring = format.substring(0, 11);
                buffer.append(substring);
                Log.d("ccc", substring);
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (hour % 2 == 0) {
                    tvMiaoshaTime.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            tvMiaoshaTime.setText((hour + 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(totime);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            tvMiaoshaShi.setText("0" + hours + "");
            if (minute >= 10) {
                tvMiaoshaMinter.setText(minute + "");
            } else {
                tvMiaoshaMinter.setText("0" + minute + "");
            }
            if (second >= 10) {
                tvMiaoshaSecond.setText(second + "");
            } else {
                tvMiaoshaSecond.setText("0" + second + "");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 真正调用秒杀倒计时的代码
     */
    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.deleteData(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ss_rwm, R.id.ss_search_imag, R.id.ss_search_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ss_rwm:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 1000);
                break;
            case R.id.ss_search_imag:
                break;
            case R.id.ss_search_edit:
                String ss = ssSearchEdit.getText().toString();
                    Intent intent2 = new Intent(getActivity(),ActivitySs.class);
                    startActivity(intent2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}