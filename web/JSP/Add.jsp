<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>

<html>
<head></head>
<meta charset="UTF-8">
<title>add page</title>
<body>
<h1 align="center">添加部门</h1>
<hr>
<form action="<%=request.getContextPath()%>/Add" method="get" align="center">
    部门编号 <input type="text" name="depno"><br>
    部门名称 <input type="text" name="depname"><br>
    部门位置 <input type="text" name="location"><br>
    <input type="submit" value="保存"><br>
</form>
<hr>
</body>
</html>