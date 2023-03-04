<%@ page import="com.study.bean.Department" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: 86177
  Date: 2022/8/18
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <title>Modify page</title>
</head>
<body>
    <h1 align='center'>修改部门</h1>
    <hr>
    <form action='/Day01/ModifySuccessful' method='post' align='center'>
        <%
            List<Department> arryList=(List)(request.getAttribute("arryList"));
            Iterator iterator=arryList.iterator();
            while(iterator.hasNext()){
                Department department=(Department) (iterator.next());
        %>
        部门编号 <input type='text' name='depno' readonly value=<%=department.getDepno()%>><br>
        部门名称 <input type='text' name='depname' value=<%=department.getDepname()%>><br>
        部门位置 <input type='text' name='location' value=<%=department.getLocation()%>><br>
        <%
            }
        %>
        <input type='submit' value='修改'><br>
    </form>
    <hr>
</body>
</html>
