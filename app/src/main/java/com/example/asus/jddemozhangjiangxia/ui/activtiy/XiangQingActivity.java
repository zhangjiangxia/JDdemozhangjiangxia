package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.XqingBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractXqing;
import com.example.asus.jddemozhangjiangxia.di.presenter.presenterXqing;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class XiangQingActivity extends AppCompatActivity implements IContractXqing.IViewXqing {

    @BindView(R.id.xq_imag)
    ImageView xqImag;
    @BindView(R.id.xq_price)
    TextView xqPrice;
    @BindView(R.id.xq_neirong)
    TextView xqNeirong;
    @BindView(R.id.xq_add_gwc)
    Button xqAddGwc;
    private IContractXqing.IPresenterXqing iPresenterXqing;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        ButterKnife.bind(this);
        iPresenterXqing = new presenterXqing();
        iPresenterXqing.attData(this);
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid",0);
        iPresenterXqing.infoData(pid +"");


    }

    @Override
    public void showData(XqingBean msg) {
        XqingBean.DataBean data = msg.getData();
        String images = data.getImages();
        String[] split = images.split("\\|");
        Picasso.with(XiangQingActivity.this).load(split[0]).into(xqImag);
        xqPrice.setText("价格为"+data.getPrice()+"¥");
        xqNeirong.setText(data.getTitle());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterXqing.deleteData(this);
    }

    @OnClick({R.id.xq_neirong, R.id.xq_add_gwc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xq_add_gwc:
             Intent  intent=new Intent(XiangQingActivity.this,AddGwcActivity.class);
             intent.putExtra("pid",pid);
             startActivity(intent);


                break;
        }
    }
}

