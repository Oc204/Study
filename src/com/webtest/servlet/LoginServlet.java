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
 * mvc service�㣬���ڴ���ҵ��
 * ʵ�ֶ�dao��Ĳ���
 */
public class LoginServlet extends HttpServlet{

    DbUtil db=new DbUtil();//����util����
    UserDao userDao=new UserDao();//����userdao����
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
     * ����servlet�е��������Ӧ
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");//��jspҳ���ȡusername��ֵ
        String password=request.getParameter("password");//��ȡpassword��ֵ
        Connection con1=null;
        try {
            User user=new User(username,password);
            con1=db.getCon();		//֮ǰ���ﱨ����Ϊû����binĿ¼�µ���jar��
            User currentUser=userDao.login(con1, user);
            if(currentUser==null){
                //System.out.println("no");
                request.setAttribute("error", "�û��������������");	//����error��ֵΪ�û��������������
                request.setAttribute("username", username);//����username��ֵΪusername 
                request.setAttribute("password", password);//����password��ֵΪpassword
                request.getRequestDispatcher("login.jsp").forward(request, response);//����ת����ָ��url��ʵ�ַ�������ת��login����
            }else{
                //System.out.println("yes");
                HttpSession session=request.getSession();//Ϊ�û������Ự����
                session.setAttribute("currentUser",currentUser);//ָ����ǰ�û�
                response.sendRedirect("success.jsp");	//�û���Ϣ��ȷ,��Ӧsuccess����,ʵ�ֽ������ת
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}