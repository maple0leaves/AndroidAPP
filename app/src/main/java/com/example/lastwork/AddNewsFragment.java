package com.example.lastwork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;


public class AddNewsFragment extends Fragment {
private EditText title ;
private EditText content ;
private Button add ;
public void init(View view){
    title= view.findViewById(R.id.newsTitle) ;
    content = view.findViewById(R.id.newsContent) ;
    add = view.findViewById(R.id.newsBtn) ;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_news, container, false);

        init(view);
        add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String s1 = title.getText().toString() ;
               String s2 = content.getText().toString();
               if(s1.equals("")||s2.equals("")){
                   Toast.makeText(getActivity(),"请填写好完整的信息",Toast.LENGTH_SHORT).show(); ;

               }else{

                       Thread thread = new Thread(){
                           @Override
                           public void run() {
                               try {
                                   SUDI.insert("news",s1,s2,"") ;
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
                   title.setText("");
                   content.setText("");
                   Toast.makeText(getActivity(),"新闻录入成功!",Toast.LENGTH_SHORT).show(); ;


               }
           }
       });



        // Inflate the layout for this fragment
        return  view ;
    }
}