<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: L
  Date: 13.10.2019
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Привет! ${authuser.login}</h2>
<a href="${pageContext.request.contextPath}/logout" >Выход</a>
<hr>
<hr>
<a href="${pageContext.request.contextPath}/adminview/useradmin">Администрирование пользователей</a>
<hr>
<a href="${pageContext.request.contextPath}/adminview/autoadmin">Администрирование авто</a>
<hr>
<a href="${pageContext.request.contextPath}/adminview/autoservicesadmin">Сервис для обслуживания авто</a>

<hr>
<a href="${pageContext.request.contextPath}/adminview/orderadmin">Обработка заявок</a>





</body>
</html>
