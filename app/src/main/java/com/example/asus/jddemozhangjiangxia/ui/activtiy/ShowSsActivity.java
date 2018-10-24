package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.SearchBean;
import com.example.asus.jddemozhangjiangxia.ui.adapter.SearchShowAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowSsActivity extends AppCompatActivity {

    @BindView(R.id.show_recyclerview)
    RecyclerView showRecyclerview;
    private SearchShowAdapter showAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ss);
        ButterKnife.bind(this);
        final Intent intent = getIntent();
        final List<SearchBean.DataBean> data = (List<SearchBean.DataBean>) intent.getSerializableExtra("data");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowSsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showRecyclerview.setLayoutManager(linearLayoutManager);
        showAdapter = new SearchShowAdapter(ShowSsActivity.this, data);
        showRecyclerview.setAdapter(showAdapter);
        showAdapter.setOnitmeclickLentener(new SearchShowAdapter.onitmeclickLentener() {
            @Override
            public void onitmeclick(int itemCount) {
               // Toast.makeText(ShowSsActivity.this, "點擊了地"+itemCount+"条", Toast.LENGTH_SHORT).show();
                int pid = data.get(itemCount).getPid();
                Intent  intent1=new Intent(ShowSsActivity.this,XiangQingActivity.class);
                intent1.putExtra("pid",pid);
                startActivity(intent1);
                finish();

            }
        });

    }
}
