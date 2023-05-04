package com.example.lastwork.tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.lastwork.MainActivity;
import com.example.lastwork.ManagerActivity;
import com.example.lastwork.StudentActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLException;

public class Function {
    static private String userName ="" ;
    static  boolean count =false ;

    public static void login_fun(Context context,String table,String user, String password, CheckBox rem) throws IOException, XmlPullParserException, SQLException, InterruptedException {

        if(user.equals("") || password.equals("")){

            Toast.makeText(context,"请填写好完整的登录信息",Toast.LENGTH_SHORT).show(); ;
        }
        else{

            Thread loginTd = new Thread(){
                @Override
                public void run() {
                    try {
                        count= SUDI.select(table,user,password) ;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } ;
            loginTd.start();
            loginTd.join();

            if(count ==false){
                Toast.makeText(context,"用户名或密码错误",Toast.LENGTH_SHORT).show(); ;
            }
            else if(count == true){
                Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT) ;
                SharedPreferences sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("user",user) ;
                editor.putString("password",password) ;
                editor.putBoolean("rem",rem.isChecked()) ;
                editor.commit() ;
                Toast.makeText(context,"登录成功",Toast.LENGTH_SHORT).show();
                if(table.equals("student")) {
                    Intent intent = new Intent(context, StudentActivity.class);
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            try {
                                userName = SUDI.select("student",user,1.0) ;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } ;
                    thread.start();
                    thread.join();
                    intent.putExtra("type",userName) ;
                    context.startActivity(intent);
                }else if(table.equals("manager")){
                    Intent intent = new Intent(context, ManagerActivity.class);
                    context.startActivity(intent);
                }

            }

        }


    }
}
