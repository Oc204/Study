package com.webtest.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webtest.Bean.User;
import com.webtest.dao.UserDao;
import com.webtest.util.DbUtil;

/*
 * mvc service层，用于处理业务
 * 实现对dao层的操作
 */
public class LoginServlet extends HttpServlet{

    DbUtil db=new DbUtil();//创建util对象
    UserDao userDao=new UserDao();//创建userdao对象
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);	
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * 处理servlet中的请求和响应
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");//从jsp页面获取username的值
        String password=request.getParameter("password");//获取password的值
        Connection con1=null;
        try {
            User user=new User(username,password);
            con1=db.getCon();		//之前这里报错，因为没有在bin目录下导入jar包
            User currentUser=userDao.login(con1, user);
            if(currentUser==null){
                //System.out.println("no");
                request.setAttribute("error", "用户名或者密码错误");	//属性error赋值为用户名或者密码错误
                request.setAttribute("username", username);//属性username赋值为username 
                request.setAttribute("password", password);//属性password赋值为password
                request.getRequestDispatcher("login.jsp").forward(request, response);//请求转发到指定url，实现服务器跳转至login界面
            }else{
                //System.out.println("yes");
                HttpSession session=request.getSession();//为用户创建会话对象
                session.setAttribute("currentUser",currentUser);//指定当前用户
                response.sendRedirect("success.jsp");	//用户信息正确,响应success界面,实现界面的跳转
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}