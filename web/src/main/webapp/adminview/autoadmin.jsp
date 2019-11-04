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
    <title>Панель администрирования автомобилей</title>

</head>


<body>
<h5>Привет! ${authuser.login}</h5>

<h3>Панель администрирования автомобилей</h3>
<a href="${pageContext.request.contextPath}/logout">Выход</a>

<c:if test="${autos != null}">
    <table width="100%" cellspacing="0" cellpadding="4" border="1">
        <tbody>  <tr>
            <th style="width: 100px">Id</th>
            <th style="width: 70px">Марка</th>
            <th>Модель</th>
            <th >Тип топлива</th>
            <th >Год</th>
            <th>Цена/сутки</th>
        </tr>
        <c:forEach items="${autos}" var="auto">
            <form > <tr>
                <td><input name="id"  type="text" readonly value=${auto.id}></td>
                <td><input name="brand" required type="text"  value=${auto.brand}></td>
                <td><input name="model" required type="text"  value=${auto.model}></td>
                <td><select name ="fuel" ><option value="Бензин">Бензин</option>
                    <option value="Дизель">Дизель</option>
                    <option selected value="${auto.fuel}">${auto.fuel}</option></select></td>
                <td><input name="date" required type="text" value=${auto.date} ></td>
                <td><input name="price" required type="text" value=${auto.price}></td>
                <td><select name ="status" ><option value="свободно">свободно</option>
                    <option value="резерв">резерв</option><option value="занято">занято</option>
                    <option selected value="${auto.status}">${auto.status}</option></select></td>
                <td><input formaction="updateauto" formmethod="post" type="submit" value="обновить"></td>
                <td><input formaction="deleteauto" formmethod="post" type="submit" value="удалить"></td>
                    <%--                    <td><input type="checkbox" name="id" value=${user.id} > </td>--%>
            </tr>
            </form>
        </c:forEach>
      </tbody>
    </table>
    <%--        <input formaction="useradmin" formmethod="post" type="submit" value="обновить">--%>
</c:if>
<h3>Добавить новое авто</h3>
<table width="100%" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th style="width: 70px">Марка</th>
        <th>Модель</th>
        <th >Тип топлива</th>
        <th >Год</th>
        <th>Цена/сутки</th>
        <th>Статус</th>
        <th></th>

    </tr>
    <form > <tr>
        <td><input name="brand" required type="text"  placeholder="Бренд"></td>
        <td><input name="model" required type="text"  placeholder="логин"></td>
        <td><select name ="fuel" ><option selected value="Бензин">Бензин</option>
            <option value="Дизель">Дизель</option></select></td>
        <td><input name="date" required type="text" placeholder="год производства" ></td>
        <td><input name="price" required type="text" placeholder="цена/сутки"></td>
        <td><select name ="status" ><option value="свободно">свободно</option>
            <option value="резерв">резерв</option><option value="занято">занято</option>
           </select></td>
        <td><input formaction="addauto" formmethod="post" type="submit" value="добавить"></td>
    </tr>
    </form>
</table>
</body>
</html>
