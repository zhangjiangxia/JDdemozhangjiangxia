package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.AddBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterAddGw;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractAddGw;

public class AddGwcActivity extends AppCompatActivity implements IContractAddGw.IViewAddGw {


    private String uid;
    private IContractAddGw.IPresenterAddGw iPresenterAddGw;
    private int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gwc);

        iPresenterAddGw = new PresenterAddGw();
        iPresenterAddGw.attData(this);
        Intent intent = AddGwcActivity.this.getIntent();
        pid = intent.getIntExtra("pid", 0);

        SharedPreferences sp = AddGwcActivity.this.getSharedPreferences("userInfo", 0);
        uid = sp.getString("uid", "0");

        if (!uid.equals("0")) {
            Log.e("aa", "initData: " + uid);

        } else {
            Toast.makeText(AddGwcActivity.this, "请登录", Toast.LENGTH_SHORT).show();
        }

        iPresenterAddGw.infoData(pid+"",uid);

    }

    @Override
    public void showData(AddBean msg) {
        String msg1 = msg.getMsg();
        String code = msg.getCode();
        Toast.makeText(this, msg1, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterAddGw.deleteData(this);

    }
}
