package com.webtest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webtest.Bean.User;
/*
 * mvc ���ݿ���ʲ�
 * dao������ֱ�ӷ������ݿ�ĽӴ���
 */
public class UserDao {
    
    public User login(Connection con,User user) throws SQLException{
        User resultUser=null;//���������ʵ�����
        String sql="select * from user where username=? and password=?";
      /*
       * ����Ԥ����ʽ����sql��䣬�ڶ��ִ������ʱ�������Ԥ����ʽ�ĸ�Ч��
       * sql�����ú�ռλ���ķ�ʽ���䣬�������������߻��ҵ��µĴ���
       */
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, user.getUsername());//ʹ��set�������indexֵΪ1��λ����ռλ����ֵ
        ps.setString(2, user.getPassword());//���indexֵΪ2��λ���ϵ�ռλ����ֵ
        ResultSet rs=ps.executeQuery();//����ֵ���в�ѯ
        if(rs.next()){//��һ�����ݲ�Ϊ��
            resultUser=new User();
            resultUser.setUsername(rs.getString("username"));//��ȡ���ݿ������Ȼ��ʵ�ַ���
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }
}