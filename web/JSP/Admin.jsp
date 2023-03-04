<%--
  Created by IntelliJ IDEA.
  User: 86177
  Date: 2022/8/20
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<form method="post" name="adminForm" action="<%=request.getContextPath()%>/Admin">
    用户名：<input name="admin" type="text"><br>
    密码： <input name="password" type="password"><br>
    <input type="checkbox" name="check" value="yes">十天内免登录<br>
    <input type="submit" value="登录">

</form>
</body>
</html>
