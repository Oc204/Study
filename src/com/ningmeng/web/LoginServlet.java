package com.ningmeng.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ningmeng.dao.UserDao;
import com.ningmeng.model.User;
import com.ningmeng.util.DbUtil;

public class LoginServlet extends HttpServlet{

    DbUtil db=new DbUtil();
    UserDao userDao=new UserDao();
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);	//请求 响应
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Connection con1=null;
        try {
            User user=new User(username,password);
            con1=db.getCon();		//之前这里报错，因为没有在bin目录下导入jar包
            User currentUser=userDao.login(con1, user);
            if(currentUser==null){
                //System.out.println("no");
                request.setAttribute("error", "用户名或者密码错误");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }else{
                //System.out.println("yes");
                HttpSession session=request.getSession();
                session.setAttribute("currentUser",currentUser);
                response.sendRedirect("main.jsp");	//用户信息正确,响应main界面
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}