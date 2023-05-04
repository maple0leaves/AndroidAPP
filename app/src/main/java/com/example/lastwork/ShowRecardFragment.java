package com.example.lastwork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;
import java.util.ArrayList;


public class ShowRecardFragment extends Fragment {

    ArrayList<String> list  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_show_recard, container, false);

        ListView listView = (ListView) view.findViewById(R.id.showList);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    list = SUDI.findRecard("recard") ;
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
       return view ;

    }
}