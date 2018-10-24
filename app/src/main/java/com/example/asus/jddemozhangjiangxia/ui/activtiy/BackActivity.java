package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.asus.jddemozhangjiangxia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackActivity extends AppCompatActivity {

    @BindView(R.id.back_button)
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.back_button)
      public void onViewClicked() {
        SharedPreferences userInfo = getSharedPreferences("userInfo", 0);
        SharedPreferences.Editor edit = userInfo.edit();
        edit.clear();
        edit.commit();
        finish();
    }
}
