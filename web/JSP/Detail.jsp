<%@ page import="java.util.List" %>
<%@ page import="com.study.bean.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: 86177
  Date: 2022/8/18
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Detail</title>
</head>
<body>
    <script type="text/javascript">
        window.onload=function (){
            <!--
            取出json字符串
            将其改为json对象
            为文本框赋值
            -->
        }
    </script>




    <h1 align='center'>部门详情</h1>
    <hr>
    <form action='index.html' method='post' align='center'>
        <%
            List<Department> arryList=new ArrayList<>();
            arryList=(ArrayList)request.getAttribute("arryList");
            Iterator<Department> it=arryList.iterator();
            while(it.hasNext()){
                Department department=it.next();

        %>
        部门编号 <input type='text' name='depno' readonly value=<%=department.getDepno()%>><br>
        部门名称 <input type='text' name='depname' value=<%=department.getDepname()%>><br>
        部门位置 <input type='text' name='location' value=<%=department.getLocation()%>><br>
        <%
            }
        %>
        <input type='submit' value='确定'>
    </form>
    <hr>

</body>
</html>
