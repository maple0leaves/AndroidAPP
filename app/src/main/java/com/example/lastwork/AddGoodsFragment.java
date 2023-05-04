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


public class AddGoodsFragment extends Fragment {

private EditText goodName ;
private EditText goodPrice ;
private EditText goodContent ;
private EditText goodPath ;
private Button goodBtn ;
public void init(View view){
goodName = view.findViewById(R.id.goodsTitle) ;
goodPrice = view.findViewById(R.id.goodsPrice) ;
goodContent = view.findViewById(R.id.goodsContent) ;
goodPath  =view.findViewById(R.id.goodPath) ;
goodBtn = view.findViewById(R.id.goodsBtn) ;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_goods, container, false);
        init(view) ;

        goodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = goodName.getText().toString();
                String s2 = goodPrice.getText().toString();
                String s3 = goodContent.getText().toString();
                String s4 = goodPath.getText().toString();

                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
                    Toast.makeText(getActivity(), "请填写好完整的信息", Toast.LENGTH_SHORT).show();


                } else {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                SUDI.insert("buy", s1, s2, s3, s4);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    goodName.setText("");
                    goodPrice.setText("");
                    goodContent.setText("");
                    goodPath.setText("");
                    Toast.makeText(getActivity(), "商品录入成功!", Toast.LENGTH_SHORT).show();

                }

            }


        });
        return view;
}
}