<%--
  Created by IntelliJ IDEA.
  User: 50127
  Date: 2020/3/24
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
welcome!<br>
<c:forEach items="${accounts}" var="account">
    ${account.id}
    ${account.pwd} <br>
</c:forEach>
</body>
</html>
