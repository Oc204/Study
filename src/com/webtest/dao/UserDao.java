package com.webtest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webtest.Bean.User;
/*
 * mvc 数据库访问层
 * dao层用于直接访问数据库的接触类
 */
public class UserDao {
    
    public User login(Connection con,User user) throws SQLException{
        User resultUser=null;//创建反射的实体对象
        String sql="select * from user where username=? and password=?";
      /*
       * 采用预处理方式插入sql语句，在多次执行语句的时候会体现预处理方式的高效性
       * sql语句采用含占位符的方式传输，避免语句过长或者混乱导致的错误
       */
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, user.getUsername());//使用set方法填充index值为1的位置上占位符的值
        ps.setString(2, user.getPassword());//填充index值为2的位置上的占位符的值
        ResultSet rs=ps.executeQuery();//传入值进行查询
        if(rs.next()){//下一个内容不为空
            resultUser=new User();
            resultUser.setUsername(rs.getString("username"));//获取数据库的内容然后实现反射
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}