package com.example.lastwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lastwork.tools.Function;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText user ;
    private EditText pwd ;
    private CheckBox rem ;
    private Button LN ;
    private TextView R1 ;
    private void init(){
        user = findViewById(R.id.Login_phonenumber) ;
        pwd = findViewById(R.id.Login_password) ;
        LN = findViewById(R.id.login) ;
        LN.setOnClickListener(this);
        rem = findViewById(R.id.reme_paswd) ;
        rem.setOnClickListener(this);
        R1 = findViewById(R.id.register) ;
        R1.setOnClickListener(this);
    }
    private void setByCheckBox(){
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE) ;
        Boolean isCheckBox = sharedPreferences.getBoolean("rem",false) ;
        if(isCheckBox){
            user.setText(sharedPreferences.getString("user",null));
            pwd.setText(sharedPreferences.getString("password",null));
            rem.setChecked(isCheckBox);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setByCheckBox();
    }

    @Override
    public void onClick(View view) {
        Intent intent =this.getIntent() ;
        String table = intent.getStringExtra("type") ;
        switch (view.getId()){
            case R.id.login:

                String user_str = user.getText().toString() ;
                String pwd_str = pwd.getText().toString() ;
                try {
                    Function.login_fun(this,table,user_str,pwd_str,rem);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.register:
                Intent intent1 = new Intent(this, Register.class);
                intent1.putExtra("type",table) ;
                startActivity(intent1);

                break ;
    }
}
}