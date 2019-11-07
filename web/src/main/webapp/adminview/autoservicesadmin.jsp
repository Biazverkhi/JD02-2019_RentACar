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
    <title>Панель администрирования сервиса обслуживания для автомобилей</title>

</head>


<body>
<h5>Привет! ${authuser.login}</h5>

<h3>Панель администрирования сервиса обслуживания для автомобилей</h3>
<a href="${pageContext.request.contextPath}/logout">Выход</a>

<c:if test="${services!= null}">
    <table width="100%" cellspacing="0" cellpadding="4" border="1">
        <tbody>
        <tr>
            <th style="width: 100px">Id</th>
            <th style="width: 70px">Профессия</th>
        </tr>
        <c:forEach items="${services}" var="services">
            <form>
                <tr>
                    <td><input name="id" type="text" readonly value=${services.id}></td>
                    <td><select name="services">
                        <option value="MOTORIST">моторист</option>
                        <option value="ELECTRICIAN">электрик</option>
                        <option value="MECHANIC">механик</option>
                        <option value="CLEANER">уборщик</option>
                        <option selected value="${services.services}">${services.services}</option>
                    </select></td>
                    <td>
                        <input formaction="updateautoservices" formmethod="post" type="submit" value="обновить"></td>
                    <td><input formaction="deleteautoservices" formmethod="post" type="submit" value="удалить"></td>
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
        <th style="width: 100px">Id</th>
        <th style="width: 70px">Профессия</th>

    </tr>
    <form>
        <tr>
            <td><select name="services">
                <option value="MOTORIST">моторист</option>
                <option value="ELECTRICIAN">электрик</option>
                <option value="MECHANIC">механик</option>
                <option value="CLEANER">уборщик</option>
            </select></td>
            <td><input formaction="addservices" formmethod="post" type="submit" value="добавить"></td>
        </tr>
    </form>
</table>
</body>
</html>
