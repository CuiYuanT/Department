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

@WebServlet(name="Delete",urlPatterns = "/Delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();

        String depno=request.getParameter("depno");
        //删除数据库中的部门信息
        Util util=new Util();
        Connection connection=util.connect();
        String sql="delete from dep where depno=?";
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
           pre=connection.prepareStatement(sql);
           pre.setString(1,depno);
           int count=pre.executeUpdate();
           if(count!=0){
               //如果删除成功，就重新刷新页面。调用Index类来重新显示页面。
               //request.getRequestDispatcher("/Index").forward(request,response);
               response.sendRedirect(request.getContextPath()+"/Index");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           util.close(connection,pre,result);
       }
    }
}
