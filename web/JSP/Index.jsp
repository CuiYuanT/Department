<%@ page import="java.util.List" %>
<%@ page import="com.study.bean.Department" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: 86177
  Date: 2022/8/18
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<meta charset="UTF-8">
<body>
    <h1 align='center'>部门列表</h1><br>
    <a href="<%=request.getContextPath()%>/Exit">退出系统</a>
    <hr>
    <table border='1px' align='center' width='700px'>
        <tr>
             <th>序号</th>
             <th>部门编号</th>
             <th>部门名称</th>
             <th>操作</th>
        </tr>
        <%
            int i=0;
            List<Department> arryList=(List)(request.getAttribute("arryList"));
            Iterator iterator=arryList.iterator();
            while(iterator.hasNext()){
                Department department=(Department) (iterator.next());
        %>
        <tr>
            <th><%=++i%></th>
            <th><%=department.getDepno()%></th>
            <th><%=department.getDepname()%></th>
            <th>
                <a href="<%=request.getContextPath()%>/Detail?depno=<%=department.getDepno()%>">详情</a>
                <a href="<%=request.getContextPath()%>/Modify?depno=<%=department.getDepno()%>">修改</a>
                <a href="<%=request.getContextPath()%>/Delete?depno=<%=department.getDepno()%>">删除</a>
            </th>
        </tr>
        <%
            }
        %>
        <tr>
            <th></th>
        </tr>

    </table>
    <hr>
    <a href='<%=request.getContextPath()%>/JSP/Add.jsp'>增加</a>
</body>
</html>
</body>
</html>
