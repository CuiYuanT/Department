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
import java.util.Iterator;
import java.util.List;

@WebServlet(name="Detail",urlPatterns = "/Detail")
public class Detail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        List<Department> arryList=new ArrayList<>();
        request.setAttribute("arryList",arryList);

        String depno=request.getParameter("depno");
        Util util=new Util();
        Connection connection=util.connect();
        String sql="select depno,depname,location from dep where depno=?";
        PreparedStatement pre=null;
        ResultSet result=null;
        try {
            pre=connection.prepareStatement(sql);
            pre.setString(1,depno);
            result=pre.executeQuery();
            while(result.next()){
                Department department=new Department(depno,
                                                     result.getString("depname"),
                                                     result.getString("location")
                                                     );
                arryList.add(department);
            }
            request.getRequestDispatcher("/JSP/Detail.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(connection,pre,result);
        }



    }
}

//<html>
//    <head></head>
//    <title>detail page</title>
//    <body>
//        <h1 align='center'>部门详情</h1>
//        <hr>
//        <form action='index.html' method='post' align='center'>
//            部门编号 <input type='text' name='depno' readonly><br>
//            部门名称 <input type='text' name='depname'><br>
//            部门位置 <input type='text' name='location'><br>
//            <input type='submit' value='确定'>
//        </form>
//        <hr>
//    </body>
//</html>