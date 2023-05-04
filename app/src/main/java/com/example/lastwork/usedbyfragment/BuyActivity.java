package com.example.lastwork.usedbyfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastwork.R;
import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;

public class BuyActivity extends AppCompatActivity {
    int[] imgList  = {R.mipmap.apple,R.mipmap.banana,R.mipmap.li,R.mipmap.mihoutao,
            R.mipmap.peach,R.mipmap.putao,R.mipmap.xigua,R.mipmap.yangtao,R.mipmap.shiliu};
    String  content  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Intent intent  = this.getIntent() ;
        String id = intent.getStringExtra("id") ;
        ImageView imageView = findViewById(R.id.buyImg);
        int i = Integer.parseInt(id)-1 ;
        imageView.setImageResource(imgList[i]);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    //同理这个i没有什么作用，重写太多，用来区别的
                    content = SUDI.select("buy",id,1) ;
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
        TextView textView = findViewById(R.id.buyText) ;
        textView.setText(content);
    }
}