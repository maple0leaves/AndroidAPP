package com.example.lastwork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lastwork.tools.MyBaseAdapter;
import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BuyFragment extends Fragment {
    String user  ;
     int[] imgList  = {R.mipmap.apple,R.mipmap.banana,R.mipmap.li,R.mipmap.mihoutao,
     R.mipmap.peach,R.mipmap.putao,R.mipmap.xigua,R.mipmap.yangtao,R.mipmap.shiliu};
     private ListView mListView ;
     private MyBaseAdapter myBaseAdapter ;
     private List<Map<String,Object>> list =  new ArrayList<Map<String,Object>>();
     public BuyFragment(String user){
         this.user = user ;
     }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false) ;
        // Inflate the layout for this fragment
        initData() ;
        mListView = view.findViewById(R.id.buylist) ;
        myBaseAdapter = new MyBaseAdapter(list,getActivity(),user) ;
        mListView.setAdapter(myBaseAdapter);

        return view ;
    }

    private void initData(){

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    SUDI.select("buy",imgList,list) ;
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