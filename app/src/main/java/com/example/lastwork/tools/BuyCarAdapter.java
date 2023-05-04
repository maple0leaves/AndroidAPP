package com.example.lastwork.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastwork.R;
import com.example.lastwork.usedbyfragment.BuyActivity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BuyCarAdapter extends BaseAdapter{
        private  boolean isHave = false ;
        private List<Map<String,Object>> datas ;
        private Context mContext ;
        private Integer money  =0;
        private TextView allMoney ;
        public BuyCarAdapter (List<Map<String ,Object>>datas,Context mContext,TextView allMoney){
            this.datas = datas ;
            this.mContext = mContext ;
            this.allMoney = allMoney ;
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
public void clear(){
          datas.clear();
          notifyDataSetChanged();
}
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            int count  = i+1 ;
            final ViewHolder holder ;
            if(view ==null){
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout3,null) ;
                holder = new ViewHolder() ;
                holder.goodChecked =view.findViewById(R.id.carRadio);
                holder.img =view.findViewById(R.id.carImg) ;
                holder.name =view.findViewById(R.id.carName) ;
                holder.price=view.findViewById(R.id.carPrice) ;
                holder.add=view.findViewById(R.id.numAdd) ;
                holder.sub=view.findViewById(R.id.numSub) ;
                holder.num=view.findViewById(R.id.num) ;


                holder.goodChecked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    if(holder.goodChecked.isChecked() ==true){
                        Integer oneItem =Integer.parseInt( holder.num.getText().toString());
                        Integer priceItem = Integer.parseInt(String.valueOf(holder.price.getText().charAt(0)));
                        money = oneItem*priceItem+money ;
                        allMoney.setText(money.toString());
                    }else if(holder.goodChecked.isChecked() ==false){
                        Integer oneItem =Integer.parseInt( holder.num.getText().toString());
                        Integer priceItem = Integer.parseInt(String.valueOf(holder.price.getText().charAt(0)));
                        money = money- oneItem*priceItem;
                        if(money>=0) {
                            allMoney.setText(money.toString());
                        }else{
                            allMoney.setText("0");
                        }
                    }
                    }
                });

                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer x= Integer.parseInt(holder.num.getText().toString()) +1 ;
                        holder.num.setText(x.toString()) ;
                    }
                });
                holder.sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int count =Integer.parseInt(holder.num.getText().toString()) ;
                        if(count>0){
                            Integer x = Integer.parseInt(holder.num.getText().toString()) -1 ;
                            holder.num.setText(x.toString()) ;
                        }
                        if(count==0) Toast.makeText(mContext,"不能再减少了",Toast.LENGTH_SHORT).show() ;
                    }
                });

                view.setTag(holder);

            }else{
                holder = (ViewHolder) view.getTag() ;
            }

            holder.img.setImageResource((Integer) datas.get(i).get("img"));
            holder.name.setText(datas.get(i).get("name").toString());
            holder.price.setText(datas.get(i).get("price").toString());
            holder.add.setImageResource((Integer) datas.get(i).get("add"));
            holder.num.setText(datas.get(i).get("num").toString());
            holder.sub.setImageResource((Integer) datas.get(i).get("sub"));


            return view;
        }


        static class ViewHolder{
            CheckBox goodChecked ;
            ImageView img ;
            TextView name ;
            TextView price ;
            ImageView add ;
            ImageView sub ;
            TextView num ;

        }
    }

