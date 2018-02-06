package com.webtest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * util层，用于存放最基本的模板供其他地方调用
 * 这里用来存放数据库的连接类的基本代码
 */
public class DbUtil {
    
    private String url="jdbc:mysql://localhost:3306/db_jsp";
    private String user="root";
    private String password="123456";
    private String driver="com.mysql.jdbc.Driver";//数据库驱动类型
    
    public Connection getCon() throws Exception{
        Class.forName(driver);//安装数据库驱动
        Connection con=DriverManager.getConnection(url, user, password);//获取数据库连接
        return con;//连接成功返回connection类型，否则抛出异常
    }
    
    //关闭资源
    public static void getClose(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
    //用于测试util功能的简单的main函数
    /*public static void main(String[] args) {
        DbUtil db=new DbUtil();
        try {
            db.getCon();
            System.out.println("测试连接数据库，连接成功");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("测试连接数据库，连接失败");
        }
        
    }*/
}