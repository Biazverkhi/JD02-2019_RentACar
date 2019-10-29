<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: L
  Date: 13.10.2019
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Страница пользователя</title>
</head>
<body>

<h1>Страница пользователя</h1>
<hr>
<h5>Привет! ${authuser.login}</h5>
<a href="${pageContext.request.contextPath}/logout" >Выход</a>
<h3>Ваши текущие заявки</h3>

<c:if test="${ordersuser != null}">
    <table width="100%" cellspacing="0" cellpadding="4" border="1">
    <tbody>  <tr>
    <th style="width: 100px">order Id</th>
    <th style="width: 70px">Id пользователя</th>
    <th>Id автомобиля</th>
    <th >Дата открытия ордера</th>
    <th >Начало аренды</th>
    <th>Конец аренды</th>
    <th>Комментарии</th>
    <th>Статус ордера</th>
    <th>Стоимость</th>
    <th></th>
    <th></th>


    </tr>
    <c:forEach items="${ordersuser}" var="order">
         <tr>
            <td>${order.id}</td>
            <td>${order.authuserId}</td>
            <td>${order.autoId}</td>
            <td>${order.createOrderDate}</td>
            <td>${order.startOrderDate}</td>
            <td>${order.stopOrderDate}</td>
            <td>${order.comment}</td>
            <td>${order.reservStatus}</td>
            <td>${order.priceArend}</td>
        </tr>

    </c:forEach>
    </tbody>
    </table>

</c:if>
</body>
</html>
