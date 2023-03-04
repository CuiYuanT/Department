<%@page contentType="text/html; charset=UTF-8" %>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head></head>
<meta charset="UTF-8">
<title>Hello page</title>
<body>
<h1>欢迎<%=request.getParameter("admin")%>使用本OA系统</h1><br>
<%--<h1>欢迎<%=(com.study.bean.User)(request.getAttribute("username")).getAdmin()%>使用本OA系统</h1><br>--%>
<a href="<%=request.getContextPath()%>/Index">进入系统</a>
</body>
</html>