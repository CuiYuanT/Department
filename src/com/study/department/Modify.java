package com.study.department;

import com.study.bean.Department;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 1、从”修改“链接处将depno传到Modify中
 * 2、Modify类中根据depno查询数据，显示到文本框内
 * 3、数据修改完以后，从前端传送给ModifySuccessful类中，在该类中执行数据更新语句
 */
@WebServlet(name = "Modify",urlPatterns = "/Modify")
public class Modify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF/8");
        PrintWriter out=response.getWriter();
        List<Department> arryList=new ArrayList<>();
        request.setAttribute("arryList",arryList);

        //从前端获取传送过来的部门号depno
        String depno=request.getParameter("depno");
        Util util=new Util();
        Connection connection=util.connect();
        String sql="select depname,location from dep where depno=?";
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
            pre=connection.prepareStatement(sql);
            pre.setString(1,depno);
            result=pre.executeQuery();
            while(result.next()){
                Department department=new Department(depno,
                        result.getString("depname"),
                        result.getString("location"));
                arryList.add(department);
            }
            request.getRequestDispatcher("/JSP/Modify.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(connection,pre,result);
        }


    }
}
