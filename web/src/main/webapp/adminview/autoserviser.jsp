<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: L
  Date: 11.10.2019
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сервайзеры</title>

</head>


<body>
<h5>Привет! ${authuser.login}</h5>

<h3>Тут показываю сервайзеров авто. Связь многие ко многим</h3>
<a href="${pageContext.request.contextPath}/logout">Выход</a>

<table width="50%" cellspacing="0" cellpadding="4" border="1">
    <tbody>
    <tr>
        <th style="width: 100px">Id авто</th>
        <th style="width: 100px">Id сервайзера</th>
        <th style="width: 70px">сервайзер</th>

    </tr>
    <c:forEach items="${servicer}" var="servicer">
        <tr>
            <td>${id1}</td>


            <td>${servicer.id}</td>
            <td>${servicer.services}</td>
        </tr>
    </c:forEach>


    </tbody>
</table>


</body>
</html>
