package com.ningmeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ningmeng.model.User;
/*
 * mvc Êý¾Ý¿â·ÃÎÊ²ã
 */
public class UserDao {
    
    public User login(Connection con,User user) throws SQLException{
        User resultUser=null;
        String sql="select * from user where username=? and password=?";
        PreparedStatement ps=con.prepareStatement(sql);//
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            resultUser=new User();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}