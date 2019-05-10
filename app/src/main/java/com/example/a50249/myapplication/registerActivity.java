package com.example.a50249.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class registerActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText name = (EditText)findViewById(R.id.et_registeractivity_username);
        final EditText passw = (EditText)findViewById(R.id.et_registeractivity_password);
        final EditText cpassw = (EditText)findViewById(R.id.et_registeractivity_confirmpassword);
        ImageView back = findViewById(R.id.iv_registeractivity_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this, welcomeActivity.class);
                startActivity(intent);
            }
        });
        Button reg = (Button)findViewById(R.id.bt_registeractivity_register);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String useranme = name.getText().toString().trim();
                String password = passw.getText().toString().trim();
                String cpassword = cpassw.getText().toString().trim();
                System.out.println(useranme + " " + password + " " + cpassword);
                if (TextUtils.isEmpty(useranme)){
                    Toast.makeText(registerActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(registerActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(cpassword)){
                    Toast.makeText(registerActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!password.equals(cpassword)){
                    Toast.makeText(registerActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    regAccout(useranme,password);
                    Toast.makeText(registerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(registerActivity.this, loginActivity.class);
                    intent.putExtra("username", useranme);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                }
            }
        });

    }

    private void regAccout(String username, String password){
        String storePass = MD5Utils.md5(password);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);;
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(username, storePass);
        editor.commit();
    }

//    private String md5(String p){
//        String md5code;
//        byte[] securityByte = null;
//        try{
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(p.getBytes());
//            securityByte = md.digest();
//        }catch (NoSuchAlgorithmException e){
//            throw new RuntimeException("No such algorithm!");
//        }
//        String md5code = new BigInteger(1, securityByte).toString(16);
//        for (int i = 0; i < 32 - md5code.length(); i++) {
//            md5code = "0" + md5code;
//        }
//        return  md5code;
//    }
}