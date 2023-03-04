package com.study.department;

import com.study.bean.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Index")
public class Index extends HttpServlet {

    /*
    * 1、连接数据库，查询数据；
    * 2、将查询到的数据封装到Department对象中，再添加到集合里；
    * 3、转发到Index.jsp上，展示数据。
    * */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  //读取seesion对象，如果没有，返回null
        if (session != null && session.getAttribute("userName") != null) {
            List<Department> arryList = new ArrayList<>();
            Util util = new Util();
            Connection connection = util.connect();
            PreparedStatement pre = null;
            ResultSet result = null;

            String sql = "select depno,depname,location from dep";
            try {
                pre = connection.prepareStatement(sql);
                result = pre.executeQuery();
                while (result.next()) {
                    Department department = new Department(result.getString("depno"),
                            result.getString("depname"),
                            result.getString("location"));

                    arryList.add(department);
                }
                request.setAttribute("arryList", arryList);
                request.getRequestDispatcher("/JSP/Index.jsp").forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(connection, pre, result);
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/JSP/Admin.jsp");
        }
    }
}


