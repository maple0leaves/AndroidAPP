package com.example.lastwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lastwork.tools.SUDI;
import com.example.lastwork.usedbyfragment.ImageViewActivity;
import com.example.lastwork.usedbyfragment.ListViewActivity;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;


public class NewsFragment extends Fragment {
    ArrayList<String> list  ;
    ImageView imageView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImageViewActivity.class) ;
                startActivity(intent);

            }
        });
        ListView listView = (ListView) view.findViewById(R.id.listview);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    list = SUDI.select("news") ;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,list) ;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                intent.putExtra("number",i+1) ;
                startActivity(intent);
            }
        });
        return view;
    }


}