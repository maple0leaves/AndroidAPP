package com.example.lastwork;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastwork.tools.BuyCarAdapter;
import com.example.lastwork.tools.MyBaseAdapter;
import com.example.lastwork.tools.SUDI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class BuyCarFragment extends Fragment implements View.OnClickListener{
private CheckBox allSelect ;
private TextView allTextMoney ;
private Button allBtnMoney ;
private String userName ;
    int[] imgList  = {R.mipmap.apple,R.mipmap.banana,R.mipmap.li,R.mipmap.mihoutao,
            R.mipmap.peach,R.mipmap.putao,R.mipmap.xigua,R.mipmap.yangtao,R.mipmap.shiliu};
    private ListView mListView ;
    private BuyCarAdapter myBaseAdapter ;
    private List<Map<String,Object>> list =  new ArrayList<Map<String,Object>>();

    public BuyCarFragment(String userName){
        this.userName= userName ;
    }
public void initData() throws InterruptedException {
    Thread thread =new Thread() {
        @Override
        public void run() {
            try {
                SUDI.select("buycar", imgList, list, 1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };
    thread.start();
    thread.join();
}




    public void init(View view){
allSelect = view.findViewById(R.id.allSelect) ;
allSelect.setOnClickListener(this) ;
allTextMoney =view.findViewById(R.id.allTextMoney) ;
allTextMoney.setOnClickListener(this);
myBaseAdapter = new BuyCarAdapter(list,getActivity(),allTextMoney) ;
allBtnMoney = view.findViewById(R.id.allBtnMoney) ;
allBtnMoney.setOnClickListener(this);
mListView = view.findViewById(R.id.buyCarlist) ;

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buy_car, container, false) ;

        try {
            initData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init(view);

        mListView.setAdapter(myBaseAdapter);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.allSelect:


//               //获得listview
//                for (int i = 0; i < mListView.getChildCount(); i++) {
//                    LinearLayout layout = (LinearLayout)mListView.getChildAt(i);// 获得子item的layout
//                    CheckBox rbtn = (CheckBox) layout.findViewById(R.id.carRadio);
//                    rbtn.setChecked(true);
//                    myBaseAdapter.notifyDataSetChanged();

                Integer all = 0 ;
                for (int i = 0; i < mListView.getChildCount(); i++) {
                   LinearLayout layout = (LinearLayout)mListView.getChildAt(i);// 获得子item的layout
                    CheckBox rbtn = (CheckBox) layout.findViewById(R.id.carRadio);
                    if (allSelect.isChecked() == true) {
                        rbtn.setChecked(true);
                        myBaseAdapter.notifyDataSetChanged();
                        TextView text = (TextView) layout.findViewById(R.id.carPrice);
                        TextView number = (TextView) layout.findViewById(R.id.num);

                        Integer oneItem = Integer.parseInt(number.getText().toString());
                        Integer priceItem = Integer.parseInt(String.valueOf(text.getText().charAt(0)));
                        all = oneItem * priceItem + all;
                    }
                    if (allSelect.isChecked() == false) {
                        rbtn.setChecked(false);
                        myBaseAdapter.notifyDataSetChanged();
                        all = 0;
                    }
                }
                allTextMoney.setText(all.toString());
                break ;

            case R.id.allBtnMoney:
                allTextMoney.setText("0");

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            SUDI.copy("buycar","recard") ;
                            SUDI.delete("buycar") ;
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
                Toast.makeText(getActivity(),"结算成功！",Toast.LENGTH_SHORT).show();
                myBaseAdapter.clear();

                break ;

    }
}
}