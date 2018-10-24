package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.RagBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterImpl;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterRag;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractRag;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RagActivtiy extends AppCompatActivity implements IContractRag.IViewRag {

    @BindView(R.id.rag_name)
    EditText ragName;
    @BindView(R.id.rag_pwd)
    EditText ragPwd;
    @BindView(R.id.rag_button)
    Button ragButton;
    private IContractRag.IPresenterRag<IContractRag.IViewRag> iViewRagIPresenterRag;
    private String ragname;
    private String ragpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rag_activtiy);
        ButterKnife.bind(this);
        iViewRagIPresenterRag = new PresenterRag();
        iViewRagIPresenterRag.attData(this);

    }

    @OnClick({R.id.rag_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rag_button:
                ragname = ragName.getText().toString();
                ragpwd = ragPwd.getText().toString();
                iViewRagIPresenterRag.infoData(ragname, ragpwd);
                break;
        }
    }

    @Override
    public void showData(RagBean msg) {
        String msgMsg = msg.getMsg();
        Toast.makeText(this, ""+msgMsg, Toast.LENGTH_SHORT).show();
        String code = msg.getCode();


        if (code.equals("0")){
            Intent intent = getIntent();
            intent.putExtra("name",ragname);
            intent.putExtra("pwd",ragpwd);
            setResult(123,intent);
            finish();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iViewRagIPresenterRag.deleteData(this);
    }

}
