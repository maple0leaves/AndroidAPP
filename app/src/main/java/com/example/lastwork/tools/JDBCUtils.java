package com.example.lastwork.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//
public class JDBCUtils {
    private static  String driver  = "com.mysql.jdbc.Driver" ;
    private static String url = "jdbc:mysql://172.26.20.38:3306/android";
    private static String username = "root" ;
    private  static String password = "123456" ;

//    Connection con = null ;
//    Statement sta = null ;
//    ResultSet res = null ;

    static {
        try {
            Class.forName(driver) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库失败");
        }
    }

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url,username,password) ;
    }

    public static void Release(Connection con,PreparedStatement pre,ResultSet res) throws SQLException {
        if(con!=null){
            con.close();
        }
        if(pre!=null){
            pre.close();
        }
        if(res!=null){
            res.close();
        }

    }
    public static void Release(Connection con, Statement sta, ResultSet res) throws SQLException {
        if(con!=null){
            con.close();
        }
        if(sta!=null){
            sta.close();
        }
        if(res!=null){
            res.close();
        }

    }
}

