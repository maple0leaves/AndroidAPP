package com.example.lastwork.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lastwork.R;
import com.example.lastwork.usedbyfragment.BuyActivity;
import com.example.lastwork.usedbyfragment.ListViewActivity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MyBaseAdapter extends BaseAdapter {
    private  boolean isHave = false ;
    private List<Map<String,Object>> datas ;
    private String user ;
    private Context mContext ;
    public MyBaseAdapter (List<Map<String ,Object>>datas,Context mContext,String user){
        this.datas = datas ;
        this.mContext = mContext ;
        this.user = user ;
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int count  = i+1 ;
        final ViewHolder holder ;
        if(view ==null){
           view = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout2,null) ;
           holder = new ViewHolder() ;
           holder.img =(ImageView) view.findViewById(R.id.image2) ;
           holder.goodName =(TextView) view.findViewById(R.id.name2) ;
           holder.pri = (TextView) view.findViewById(R.id.pri2) ;
           holder.add = (Button) view.findViewById(R.id.btn2) ;

           holder.img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(mContext, BuyActivity.class);
                   intent.putExtra("id",count+"") ;
                   mContext.startActivity(intent);
               }
           });

           holder.add.setOnClickListener(new View.OnClickListener() {

               //要在这获取哪一个商品并加入到数据库中
               @Override
               public void onClick(View view) {

                   Thread thread = new Thread(){
                       @Override
                       public void run() {
                           try {
                                isHave =  SUDI.select("buycar",count+"") ;
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


                   if(isHave ==false){
                       Thread thread1 = new Thread(){
                           @Override
                           public void run() {
                               try {
                               //从buy中把i号商品加入到buycar中,1只是区分，没有作用
                                   SUDI.insert("buy","buycar",count+"",user ,1) ;
                               } catch (SQLException e) {
                                   e.printStackTrace();
                               }
                           }
                       } ;
                       thread1.start();
                       try {
                           thread1.join();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }

                       Toast.makeText(mContext,"加入购物车成功！",Toast.LENGTH_SHORT).show();

                   }else if(isHave ==true){
                       Toast.makeText(mContext,"该商品已经加入了购物车",Toast.LENGTH_SHORT).show();

                   }
               }
           });

           view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag() ;
        }

        holder.img.setImageResource((Integer) datas.get(i).get("img"));
        holder.goodName.setText(datas.get(i).get("goodName").toString());
        holder.pri.setText(datas.get(i).get("pri").toString());
        holder.add.setText(datas.get(i).get("add").toString());


        return view;
    }

    static class ViewHolder{
        ImageView img ;
        TextView goodName ;
        TextView pri ;
        Button add ;
    }
}
