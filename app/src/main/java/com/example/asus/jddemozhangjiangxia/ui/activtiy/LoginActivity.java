package com.example.asus.jddemozhangjiangxia.ui.activtiy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jddemozhangjiangxia.R;
import com.example.asus.jddemozhangjiangxia.data.bean.LoginBean;
import com.example.asus.jddemozhangjiangxia.di.presenter.PresenterLogin;
import com.example.asus.jddemozhangjiangxia.di.presenter.contract.IContractLogin;
import com.example.asus.jddemozhangjiangxia.ui.fragment.fiveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IContractLogin.IViewLogin {

    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.weixin_login)
    TextView weixinLogin;
    @BindView(R.id.qq_login)
    TextView qqLogin;
    private IContractLogin.IPresenterLogin<IContractLogin.IViewLogin> LoginIPresenterLogin;
    private String logname;
    private String logpwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginIPresenterLogin = new PresenterLogin();
        LoginIPresenterLogin.attData(this);
    }

    @OnClick({R.id.login_button, R.id.register, R.id.weixin_login, R.id.qq_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                logname = loginName.getText().toString();
                logpwd = loginPwd.getText().toString();
                LoginIPresenterLogin.infoData(logname, logpwd);


                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RagActivtiy.class);
                startActivityForResult(intent, 110);
                break;
            case R.id.weixin_login:
                break;
            case R.id.qq_login:
                break;
        }
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void showData(LoginBean msg) {
        String msg1 = msg.getMsg();
        String code = msg.getCode();
        if (code.equals("0")) {
            SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            String uid = String.valueOf(msg.getData().getUid());
            String token = msg.getData().getToken();
            String username = msg.getData().getUsername();
            Log.e("TAG","username"+username);
            edit.putString("token",token);
            edit.putString("uid", uid);
            edit.putString("username",username);
            edit.commit();
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);

            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 123) {
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            loginName.setText(name);
            loginPwd.setText(pwd);
        }
    }
}
