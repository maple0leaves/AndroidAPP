package com.example.lastwork.tools;

import android.util.Log;

import com.example.lastwork.R;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SUDI {

    public static ArrayList<String> findRecard(String table) throws SQLException {
        ArrayList<String>  list = new ArrayList<>() ;
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String str = "" ;
            String u1 = res.getString(2) ;
            String u2 = res.getString(3) ;
            String u3 = res.getString(4) ;
            String u4 = res.getString(5) ;

            str =u4+"\n"+u3+"买了"+u2+"的"+u1 ;
            list.add(str) ;
        }
        JDBCUtils.Release(con,sta,res);
        return  list;
    }

    public static void copy(String from ,String to) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "insert into "+to+" (id,name,price,buyuser)"+" select id,name,price,buyuser from "+from  ;
        String sql1 = "update "+to+" set time=now() ";
        Statement sta = con.createStatement() ;

        sta.executeUpdate(sql);
        sta.executeUpdate(sql1);

        sta.close();
        con.close();
    }

    public  static void delete(String table) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "delete from "+table;
        Statement sta = con.createStatement() ;
       sta.executeUpdate(sql);
    }
//登录用
    public static Boolean select(String table,String user,String pwd) throws SQLException {
        boolean isHave = false ;
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);

        while (res.next()){
            String u = res.getString(2) ;//列号从1开始
            String p =res.getString(3) ;
            if(u.equals(user)&&p.equals(pwd)){
                isHave=true ;
                break;
            }

        }
        JDBCUtils.Release(con,sta,res);
        return isHave ;
    }
//购物车用
    public static Boolean select(String table,String position) throws SQLException {
        boolean isHave = false ;
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select id  from "+table +" where id ="+position;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String u = res.getString(1) ;//列号从1开始
            if(u.equals(position)){
                isHave=true ;
                break;
            }

        }
        JDBCUtils.Release(con,sta,res);
        return isHave ;
    }
    public static String select(String table,String user,double i ) throws SQLException {
        String userName = "";
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select userName  from "+table +" where user ="+user;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String u = res.getString(1) ;//列号从1开始
            userName = u ;

        }
        JDBCUtils.Release(con,sta,res);
        return userName ;
    }
    public static String  select(String table,String id,int i) throws SQLException {
        String content = "";
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select content from "+table +" where id ="+id;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String u = res.getString(1) ;//列号从1开始
            content = u ;

        }
        JDBCUtils.Release(con,sta,res);
        return content ;
    }
    //新闻标题列表用
    public static ArrayList<String> select(String table) throws SQLException {
        ArrayList<String> title =new ArrayList<String>()  ;
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String u = res.getString(1) ;//列号从1开始
            title.add(u) ;
        }
        JDBCUtils.Release(con,sta,res);
        return title ;
    }
    public static String select(String table,int i ,int GAP) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        if(i==1){
            String sql = "select * from "+table;
            //PreparedStatement 避免sql注入漏洞
            Statement sta = con.createStatement() ;
            ResultSet res = sta.executeQuery(sql);

            Integer all = 0;
            Integer x = 0 ;
            while (res.next()){
                String u = res.getString(4) ;//列号从1开始
                all = all+Integer.parseInt(u) ;
                x++ ;
            }
            all = all/x ;
            JDBCUtils.Release(con,sta,res);
            return String.valueOf(all) ;
        }
        if(i==2){
            String sql = "select * from "+table;
            //PreparedStatement 避免sql注入漏洞
            Statement sta = con.createStatement() ;
            ResultSet res = sta.executeQuery(sql);
            String str = "" ;
            while (res.next()){
                String u = res.getString(3) ;//列号从1开始
                String four = res.getString(4) ;
                str = str+u+":"+four+"\n"  ;
            }
            JDBCUtils.Release(con,sta,res);
            return  str;
        }
        if(i==3){
            String sql = "select * from "+table;
            //PreparedStatement 避免sql注入漏洞
            Statement sta = con.createStatement() ;
            ResultSet res = sta.executeQuery(sql);
            String str = "" ;
            while (res.next()){
                String u = res.getString(2) ;//列号从1开始

                str = u ;
                break;
            }
            JDBCUtils.Release(con,sta,res);
            return  str;
        }
        if(i==4){
            String sql = "select * from "+table;
            //PreparedStatement 避免sql注入漏洞
            Statement sta = con.createStatement() ;
            ResultSet res = sta.executeQuery(sql);
            String str = "" ;
            while (res.next()){
                String u = res.getString(5) ;//列号从1开始

                str = u ;
                break;
            }
            JDBCUtils.Release(con,sta,res);
            return  str;
        }
        if(i==5){
            String sql = "select * from "+table;
            //PreparedStatement 避免sql注入漏洞
            Statement sta = con.createStatement() ;
            ResultSet res = sta.executeQuery(sql);
            String str = "已学课程:" ;
            while (res.next()){
                String u = res.getString(3) ;//列号从1开始

                str = str+"\n"+u ;

            }
            JDBCUtils.Release(con,sta,res);
            return  str;
        }


        return "" ;
    }
//新闻列表内容用
    public static String select(String table,int i ) throws SQLException {
        String str =""  ;
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table+" limit "+(i-1)+",1";
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);


        while (res.next()){
            String u = res.getString(2) ;//列号从1开始
            str = u ;
        }
        JDBCUtils.Release(con,sta,res);
        return str ;
    }
    public static void select(String table, int[] imgList,List<Map<String,Object>> list) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);
        int i  = 0 ;
        while (res.next()){
            Map<String,Object> map = new HashMap<String ,Object>()  ;
            //列号从1开始
            String name = res.getString(2) ;
            String price  =res.getString(3) ;

            map.put("img",imgList[i]) ;

            map.put("goodName",name) ;
            map.put("pri",price) ;
//            Log.e("look",imgList[i]+"");
//            Log.e("look",name) ;
//            Log.e("look",price) ;
//            Log.e("look",map.toString()) ;
            map.put("add","加购物车") ;
            list.add(map) ;
            i++;
        }
//        Log.e("look",list.toString()) ;
        JDBCUtils.Release(con,sta,res);
    }
    public static void select(String table, int[] imgList,List<Map<String,Object>> list,int i ) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "select * from "+table;
        //PreparedStatement 避免sql注入漏洞
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql);
        while (res.next()){
            Map<String,Object> map = new HashMap<String ,Object>()  ;
            String id = res.getString(1) ;
            Integer xiabiao = Integer.parseInt(id) ;
            //列号从1开始
            String name = res.getString(2) ;
            String price  =res.getString(3) ;

            map.put("img",imgList[xiabiao-1]) ;

            map.put("name",name) ;
            map.put("price",price) ;
            map.put("add", R.mipmap.add) ;
            map.put("num",0) ;
            map.put("sub",R.mipmap.sub) ;
//            Log.e("look",imgList[i]+"");
//            Log.e("look",name) ;
//            Log.e("look",price) ;
//            Log.e("look",map.toString()) ;
            list.add(map) ;
            i++;
        }
//        Log.e("look",list.toString()) ;
        JDBCUtils.Release(con,sta,res);
    }


//    public static int update(String table,Object smsInfo) throws SQLException {
//        Connection con = JDBCUtils.getConnection() ;
//        String sql = "update ? set address=?,date=?,type=?,body=? where id=?" ;
//        //PreparedStatement 避免sql注入漏洞
//        PreparedStatement pre = con.prepareStatement(sql) ;
////        pre.setString(1,table);
////        pre.setString(2,smsInfo.getAddress());
////        pre.setLong(3,smsInfo.getDate());
////        pre.setInt(4,smsInfo.getType());
////        pre.setString(5,smsInfo.getBody());
////        pre.setInt(6,smsInfo.getId());
//
//        int i =pre.executeUpdate() ;
//
//        pre.close();
//        con.close();
//        return i ;
//    }

//    public static int delete(String table,int id) throws SQLException {
//        Connection con = JDBCUtils.getConnection() ;
//        String sql = "delete from table where id=?" ;
//        //PreparedStatement 避免sql注入漏洞
//        PreparedStatement pre = con.prepareStatement(sql) ;
//        pre.setInt(1,id);
//        int i =pre.executeUpdate() ;
//        pre.close();
//        con.close();
//        return i ;
//    }
    //注册用
    public static int insert (String table,String user,String pwd) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "insert into "+table+" (user,pwd) values ("+user+","+pwd+")" ;
        Statement sta = con.createStatement() ;

        sta.executeUpdate(sql);

        sta.close();
        con.close();
        return 1 ; //受影响行数
    }
    public static int insert (String table,String user,String pwd,String i ) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        String sql = "insert into "+table+" values ("+user+","+pwd+")" ;
        Statement sta = con.createStatement() ;

        sta.executeUpdate(sql);

        sta.close();
        con.close();
        return 1 ; //受影响行数
    }
    public static int insert (String table,String s1,String s2,String s3,String s4 ) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        ArrayList<String> arr = select(table) ;
        String  id =  arr.get(arr.size()-1) ;
        Integer count = Integer.parseInt(id)+1 ;
        id = String.valueOf(count) ;
        String sql = "insert into "+table+" values ("+id+","+s1+","+s2+","+s3+","+s4+")" ;
        Statement sta = con.createStatement() ;

        sta.executeUpdate(sql);

        sta.close();
        con.close();
        return 1 ; //受影响行数
    }

    public static int insert (String table,String s1,String s2,String s3,String s4 ,String s5) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;

        String sql = "insert into "+table+" values ("+s2+","+s3+","+s4+","+s5+","+s1+")" ;
        Statement sta = con.createStatement() ;

        sta.executeUpdate(sql);

        sta.close();
        con.close();
        return 1 ; //受影响行数
    }

    public static int insert (String from,String to,String position,String user ,int i ) throws SQLException {
        Connection con = JDBCUtils.getConnection() ;
        user = "'"+user ;
        user=user+"'";
        String sql = "select id,name,price from "+from+" where id="+position ;
        Statement sta = con.createStatement() ;
        ResultSet res = sta.executeQuery(sql) ;
//varchar 类型时 字符串要加'',不能直接字符类型插进去，谢谢你mysql
        while (res.next()){
            String id = res.getString(1) ;
            String name = res.getString(2) ;
            name ="'"+name ;
            name = name+"'" ;
            String price = res.getString(3) ;
            price ="'"+price ;
            price = price+"'" ;
            String sql1 = "insert into "+to+" values("+id+","+name+","+price+","+user+")" ;
            sta.executeUpdate(sql1);

        }
        res.close();
        sta.close();
        con.close();
        return 1 ; //受影响行数
    }
}
