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
    <title>Панель администрирования заказов</title>

</head>


<body>
<h5>Привет! ${authuser.login}</h5>

<a href="${pageContext.request.contextPath}/logout">Выход</a>

<h3>Панель администрирования заказов</h3>
<c:if test="${orders != null}">
    <table width="100%" cellspacing="0" cellpadding="4" border="1">
        <tbody>  <tr>
            <th style="width: 100px">Id</th>
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
        <c:forEach items="${orders}" var="order">
            <form > <tr>
                <td><input name="id"  type="text" readonly value=${order.id}></td>
                <td><input name="authuserId" required type="text"  value=${order.authuserId}></td>
                <td><input name="autoId" required type="text"  value=${order.autoId}></td>
                <td><input name="createOrderDate" required type="datetime-local"  value=${order.createOrderDate} ></td>
                <td><input name="startOrderDate" required type="datetime-local" value=${order.startOrderDate}></td>
                <td><input name="stopOrderDate" required type="datetime-local" value=${order.stopOrderDate}></td>
                <td><textarea name="commentary" cols="30" rows="3" >${order.comment}</textarea></td>
                <td><select name ="reservStatus" ><option value="закрыт">закрыт</option>
                    <option value="на рассмотрении">на рассмотрении</option>
                    <option value="открыт">открыт</option><option value="одобрен">одобрен</option>
                    <option selected value="${order.reservStatus}">${order.reservStatus}</option></select></td>
                <td><input name="priceArend" required type="number"  value=${order.priceArend}></td>

                <td> <input formaction="updateorder" formmethod="post" type="submit" value="обновить"></td>
                <td>  <input formaction="deleteorder" formmethod="post" type="submit" value="удалить"></td>
                    <%--                    <td><input type="checkbox" name="id" value=${user.id} > </td>--%>
            </tr>
            </form>
        </c:forEach>
      </tbody>
    </table>
    <%--        <input formaction="useradmin" formmethod="post" type="submit" value="обновить">--%>
</c:if>
<h3>Добавить новый заказ</h3>
<table width="100%" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th style="width: 70px">Id пользователя</th>
        <th>Id автомобиля</th>
        <th >Дата открытия ордера</th>
        <th >Начало аренды</th>
        <th>Конец аренды</th>
        <th>Комментарии</th>
        <th>Статус ордера</th>
        <th>Стоимость</th>
        <th></th>

    </tr>
    <form > <tr>
        <td><input name="authuserId" required type="text" placeholder="Id пользователя"></td>
        <td><input name="autoId" required type="text" placeholder="Id автомобиля"></td>
        <td><input name="createOrderDate" required type="datetime-local" placeholder="Дата открытия ордера" ></td>
        <td><input name="startOrderDate" required type="datetime-local" placeholder="Начало аренды"></td>
        <td><input name="stopOrderDate" required type="datetime-local" placeholder="Конец аренды"></td>
        <td><textarea name="commentary" cols="30" rows="3" placeholder="Комментарии"></textarea></td>

        <td><select name ="reservStatus"  ><option value="закрыт">закрыт</option>
            <option value="на рассмотрении">на рассмотрении</option>
            <option value="открыт">открыт</option><option value="одобрен">одобрен</option>
            </select></td>
        <td><input name="priceArend" required type="number" placeholder="Стоимость"}></td>
        <td><input formaction="addorder" formmethod="post" type="submit" value="добавить"></td>
    </tr>
    </form>
</table>
</body>
</html>
