package com.example.lastwork.usedbyfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lastwork.R;
import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;

public class ListView2Activity extends AppCompatActivity {

    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        Intent intent = this.getIntent() ;
        int i = intent.getIntExtra("number",0);
        textView = findViewById(R.id.userView2) ;
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    textView.setText(SUDI.select("user",i,0)) ;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } ;

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}