package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.ui.fragment.FourFragment;
import com.example.asus.jddemozhangjiangxia.ui.fragment.OneFragment;
import com.example.asus.jddemozhangjiangxia.ui.fragment.ThreeFragment;
import com.example.asus.jddemozhangjiangxia.ui.fragment.TwoFragment;
import com.example.asus.jddemozhangjiangxia.ui.fragment.fiveFragment;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.m_search)
//    ImageView mSearch;
//    @BindView(R.id.et_search)
//    EditText etSearch;
//    @BindView(R.id.m_root)
//    ImageView mRoot;
//    @BindView(R.id.m_xiaoxi)
//    ImageView mXiaoxi;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    1);
        }

        bottomTabBar.init(getSupportFragmentManager())
                .setFontSize(0)
                .setImgSize(120, 120)
                .setTabPadding(4, 6, 1)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("", R.mipmap.ac1, OneFragment.class)
                .addTabItem("", R.mipmap.abx, TwoFragment.class)
                .addTabItem("", R.mipmap.abz, ThreeFragment.class)
                .addTabItem("", R.mipmap.abv, FourFragment.class)
                .addTabItem("", R.mipmap.ac3, fiveFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }

                });


    }
}
