package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractSearch;
import com.example.asus.jddemozhangjiangxia.di.presenter.presenteSearch;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitySs extends AppCompatActivity implements IContractSearch.IViewSearch {

    @BindView(R.id.a_imag)
    ImageView aImag;
    @BindView(R.id.a_edit)
    EditText aEdit;
    private IContractSearch.IPresenterSearch iPresenterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss);
        ButterKnife.bind(this);
        iPresenterSearch = new presenteSearch();
        iPresenterSearch.attData(this);

    }

    @Override
    public void showData(SearchBean msg) {
        List<SearchBean.DataBean> data = msg.getData();
        data.get(0).getTitle();
        Intent  intent=new Intent( ActivitySs.this, ShowSsActivity.class);
        intent.putExtra("data",(Serializable) data);
        startActivity(intent);

    }


    @OnClick({R.id.a_imag, R.id.a_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_imag:
                String keywords = aEdit.getText().toString();
                iPresenterSearch.infoData(keywords);

                break;

        }
    }
}
