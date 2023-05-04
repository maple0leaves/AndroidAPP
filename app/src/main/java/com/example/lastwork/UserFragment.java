package com.example.lastwork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lastwork.usedbyfragment.ListView2Activity;
import com.example.lastwork.usedbyfragment.ListViewActivity;


public class UserFragment extends Fragment {
    String user  ;
    TextView userName ;
    public UserFragment(String user){
        this.user =user ;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false) ;
        userName = view.findViewById(R.id.userName) ;
        userName.setText("学生姓名:"+user);
       ListView listView = view.findViewById(R.id.userView) ;
        String[] arr = {"绩点","排名","学号","班级","课程"} ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1,arr) ;
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ListView2Activity.class);
                intent.putExtra("number",i+1) ;
                startActivity(intent);
            }
        });
        return view  ;
    }
}