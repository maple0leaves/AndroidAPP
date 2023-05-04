package com.example.lastwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseLgoin extends AppCompatActivity implements View.OnClickListener{
    Button manager ;
    Button student ;
    private void init(){
        manager = findViewById(R.id.manager) ;
        manager.setOnClickListener(this);
        student = findViewById(R.id.student) ;
        student.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_lgoin);
        init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.manager:
                String ma = "manager" ;
                Intent intent1 = new Intent(ChooseLgoin.this,Login.class);
                intent1.putExtra("type",ma) ;
                startActivity(intent1);
                break;
            case R.id.student:
                String st = "student" ;
                Intent intent2 = new Intent(ChooseLgoin.this,Login.class);
                intent2.putExtra("type",st) ;
                startActivity(intent2);
                break;
        }
    }
}