package com.example.a50249.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

public class loginActivity extends Activity {
//    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        ImageView back = findViewById(R.id.iv_login_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, welcomeActivity.class);
                startActivity(intent);
            }
        });

        TextView tv = findViewById(R.id.tv_login_register);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });

        Button log = findViewById(R.id.bt_login_login);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = findViewById(R.id.et_login_username);
                EditText passw = findViewById(R.id.et_login_password);
                String pass = passw.getText().toString();
                String username = name.getText().toString();
                String spP = findPassword(username);
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(loginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
//                    return;
                } else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(loginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
//                    return;
                }else if(spP!=null&&!TextUtils.isEmpty(spP)&&!spP.equals(MD5Utils.md5(pass))) {
                    Toast.makeText(loginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
//                    return;
                }else if((spP.equals(MD5Utils.md5(pass)))){
                    Intent intent = new Intent(loginActivity.this, mainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(loginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
//                    return;
                }
            }
        });
    }

    private String findPassword(String username){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(username , "");
    }
}
