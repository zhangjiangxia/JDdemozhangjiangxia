package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asus.jddemozhangjiangxia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapseActivity extends AppCompatActivity {

    @BindView(R.id.c_text)
    TextView cText;
 private int  time=3;

 private Handler  handler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         time--;
         cText.setText(time+"s");
         if (time==0){
             Intent  intent=new Intent(CollapseActivity.this,MainActivity.class);
             startActivity(intent);
             finish();
             handler.removeMessages(0);
         }
           handler.sendEmptyMessageDelayed(0,1000);

     }
 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse);
        ButterKnife.bind(this);
       handler.sendEmptyMessageDelayed(0,1000);

    }
}
