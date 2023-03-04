package com.study.department;

import com.study.bean.User;
import com.study.util.AdminUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String admin=request.getParameter("admin");
        String password=request.getParameter("password");
        Boolean b=false;
        AdminUtil adminUtil=new AdminUtil();
        b=adminUtil.admin(admin,password);
        if(b){
            User user=new User(admin,password);
            //如果没有Session对象，会创建一个Session对象
            HttpSession session=request.getSession();
            session.setAttribute("userName",user.getAdmin());
            //如果选择了“十天内免登录”，就创造cookies
            String check=request.getParameter("check");
            if("yes".equals(check)){
                Cookie cookie_01=new Cookie("admin",admin);
                Cookie cookie_02=new Cookie("password",password);
                cookie_01.setMaxAge(60*60*24*10);
                cookie_02.setMaxAge(60*60*24*10);
                response.addCookie(cookie_01);
                response.addCookie(cookie_02);
            }
            request.getRequestDispatcher("/JSP/welcom.jsp").forward(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/JSP/AdminError.jsp");
        }
    }
}
