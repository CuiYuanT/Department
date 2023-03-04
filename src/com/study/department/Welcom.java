package com.study.department;

import com.study.bean.User;
import com.study.util.AdminUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Handler;

@WebServlet("/welcom")
public class Welcom extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Cookie[] cookies=request.getCookies();
        String admin=null;
        String password=null;
        if(cookies!=null){ //如果cookie不为空,就读取cookie,但是会读取到所有的cookie;
            for(int i=0;i<cookies.length;i++){
                if("admin".equals(cookies[i].getName())){
                    admin=cookies[i].getValue();
                }else if("password".equals(cookies[i].getName())){
                    password=cookies[i].getValue();
                }
            }
            //如果读取到了用户名和密码就直接登录
            if(admin!=null && password!=null){
                Boolean b=false;
                AdminUtil adminUtil=new AdminUtil();
                b=adminUtil.admin(admin,password);
                if(b){
                    User user=new User(admin,password);
                    request.setAttribute("username",user);
                    request.getRequestDispatcher("/JSP/welcom.jsp").forward(request,response);
                }else {
                    response.sendRedirect(request.getContextPath()+"/JSP/AdminError.jsp");
                }
//                Util util=new Util();
//                Connection connection=util.connect();
//                String sql="select admin,password from user where admin=? and password=?";
//                PreparedStatement pre=null;
//                ResultSet result=null;
//                try {
//                    pre=connection.prepareStatement(sql);
//                    pre.setString(1,admin);
//                    pre.setString(2,password);
//                    result=pre.executeQuery();
//                    if(result.next()){
//                        User user=new User(result.getString("admin"),result.getString("password"));
//                        request.getRequestDispatcher("/JSP/welcom.jsp").forward(request,response);
//                    }else {
//                        response.sendRedirect(request.getContextPath()+"/JSP/AdminError.jsp");
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }finally {
//                    util.close(connection,pre,result);
//                }
            }

        }else {  //如果cookie为空，跳转到登录页面
            response.sendRedirect(request.getContextPath()+"/JSP/Admin.jsp");
        }

    }
}
