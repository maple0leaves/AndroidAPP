package com.example.lastwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity  implements View.OnClickListener{
    private TextView tabDeal;
    private TextView tabPoi;
    private TextView tabMore;
    private TextView tabUser;
    private String user ;
    private FrameLayout ly_content;

    private NewsFragment f1;
    private BuyFragment f3 ;
    private BuyCarFragment f2 ;
    private UserFragment f4 ;
    private FragmentManager fragmentManager;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.activity_student);
                bindView();
                Intent intent = this.getIntent() ;
               user =  intent.getStringExtra("type") ;
//                Resources res = getResources() ;
//                Drawable drawable = res.getDrawable(R.drawable.bkcolor) ;
//                this.getWindow().setBackgroundDrawable(drawable);
                tabDeal.setSelected(true);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                f1 = new NewsFragment();
                transaction.add(R.id.fragment_container,f1);
                transaction.commit();
            }

            //UI组件初始化与事件绑定
            private void bindView() {
                tabDeal = (TextView)this.findViewById(R.id.manager_deal);
                tabPoi = (TextView)this.findViewById(R.id.txt_poi);
                tabUser = (TextView)this.findViewById(R.id.txt_user);
                tabMore = (TextView)this.findViewById(R.id.txt_more);
                ly_content = (FrameLayout) findViewById(R.id.fragment_container);

                tabDeal.setOnClickListener(this);
                tabMore.setOnClickListener(this);
                tabUser.setOnClickListener(this);
                tabPoi.setOnClickListener(this);

            }

            //重置所有文本的选中状态
            public void selected(){
                tabDeal.setSelected(false);
                tabMore.setSelected(false);
                tabPoi.setSelected(false);
                tabUser.setSelected(false);
            }

            //隐藏所有Fragment
            public void hideAllFragment(FragmentTransaction transaction){
                if(f1!=null){
                    transaction.hide(f1);
                }
                if(f2!=null){
                    transaction.hide(f2);
                }
                if(f3!=null){
                    transaction.hide(f3);
                }
                if(f4!=null){
                    transaction.hide(f4);
                }
            }

            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                switch(v.getId()){
                    case R.id.manager_deal://新闻模块
                        selected();
                        tabDeal.setSelected(true);
                        f1 = new NewsFragment();
                        transaction.add(R.id.fragment_container,f1);
                        break;

                    case R.id.txt_more://购物车
                        selected();
                        tabMore.setSelected(true);
                        f2 = new BuyCarFragment(user);
                        transaction.add(R.id.fragment_container,f2);
                        break;

                    case R.id.txt_poi://购物
                        selected();
                        tabPoi.setSelected(true);
                        f3 =  new BuyFragment(user);
                        transaction.add(R.id.fragment_container,f3);
                        break;

                    case R.id.txt_user://学生
                        selected();
                        tabUser.setSelected(true);
                        f4 =  new UserFragment(user);
                        transaction.add(R.id.fragment_container,f4);
                        break;
                }

                transaction.commit();
            }
        }


