package com.study.department;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="Add",urlPatterns = "/Add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter writer=response.getWriter();

        String depno=request.getParameter("depno");
        String depname=request.getParameter("depname");
        String location=request.getParameter("location");

        Util util=new Util();
        Connection connection=util.connect();
        PreparedStatement pre=null;
        ResultSet result=null;
        String sql="insert into dep(depno,depname,location) values(?,?,?)";
        int count=0;
        try {
            pre=connection.prepareStatement(sql);
            //pre.setString(1,depno);
            pre.setString(1,depno);
            pre.setString(2,depname);
            pre.setString(3,location);
            count=pre.executeUpdate();
            if(count==1){
                //request.getRequestDispatcher("/Index").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/Index");
            }else{
                //request.getRequestDispatcher("/error.html").forward(request,response);
                response.sendRedirect(request.getContextPath()+"/JSP/AddError.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(connection,pre,result);
        }
    }
}
