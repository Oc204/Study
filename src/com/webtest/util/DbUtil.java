package com.webtest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * util�㣬���ڴ���������ģ�幩�����ط�����
 * ��������������ݿ��������Ļ�������
 */
public class DbUtil {
    
    private String url="jdbc:mysql://localhost:3306/db_jsp";
    private String user="root";
    private String password="123456";
    private String driver="com.mysql.jdbc.Driver";//���ݿ���������
    
    public Connection getCon() throws Exception{
        Class.forName(driver);//��װ���ݿ�����
        Connection con=DriverManager.getConnection(url, user, password);//��ȡ���ݿ�����
        return con;//���ӳɹ�����connection���ͣ������׳��쳣
    }
    
    //�ر���Դ
    public static void getClose(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }
    
    //���ڲ���util���ܵļ򵥵�main����
    /*public static void main(String[] args) {
        DbUtil db=new DbUtil();
        try {
            db.getCon();
            System.out.println("�����������ݿ⣬���ӳɹ�");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("�����������ݿ⣬����ʧ��");
        }
        
    }*/
}