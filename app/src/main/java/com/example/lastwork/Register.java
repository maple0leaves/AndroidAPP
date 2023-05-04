package com.example.lastwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText user ;
    private EditText pwd ;
    private Button RG ;
    boolean count =false;

    private void init(){
        user = findViewById(R.id.Register1_phonenumber) ;
        pwd = findViewById(R.id.Register1_password) ;
        RG = findViewById(R.id.Register) ;
        RG.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    @Override
    public void onClick(View view) {
        String user_str = user.getText().toString();
        String pwd_str = pwd.getText().toString();
        if (user_str.equals("") || pwd_str.equals("")) {
            Toast.makeText(this, "请填写好完整的登录信息", Toast.LENGTH_SHORT).show();

        }
        else {
            Intent intent = this.getIntent();
            String type = intent.getStringExtra("type");
            Thread loginTd = new Thread(){
                @Override
                public void run() {
                    try {
                        count= SUDI.select(type,user_str,pwd_str) ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } ;
            loginTd.start();
            try {
                loginTd.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count==true){
                Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
            }else{
                Thread loginTd1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            SUDI.insert(type, user_str, pwd_str);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                };
                loginTd1.start();
                try {
                    loginTd1.join();
                    Toast.makeText(this,"恭喜你，注册成功！！！",Toast.LENGTH_SHORT).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}