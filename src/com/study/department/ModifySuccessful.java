package com.study.department;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="ModifySuccessful",urlPatterns = "/ModifySuccessful")
public class ModifySuccessful extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String depno=requset.getParameter("depno");
        String depname=requset.getParameter("depname");
        String location=requset.getParameter("location");

        Util util=new Util();
        Connection connection=util.connect();
        String sql="update dep set location=? where depno=?";
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
            pre=connection.prepareStatement(sql);
            pre.setString(1,location);
            pre.setString(2,depno);
            int count=pre.executeUpdate();
            if(count==1){
                response.sendRedirect(requset.getContextPath()+"/Index");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(connection,pre,result);
        }
    }
}
