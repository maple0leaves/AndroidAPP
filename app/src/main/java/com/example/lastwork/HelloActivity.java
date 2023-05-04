package com.example.lastwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends AppCompatActivity {
    ImageView v1 ;
    Timer timer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_hello);
        v1 =(ImageView) findViewById(R.id.v1);
        timer =new Timer(true) ;
        timer.schedule(hello,2000);

    }
    TimerTask hello = new TimerTask() {
        @Override
        public void run() {
            Intent intent1 = new Intent(HelloActivity.this,ChooseLgoin.class);
            startActivity(intent1);
            finish();
        }
    };
}