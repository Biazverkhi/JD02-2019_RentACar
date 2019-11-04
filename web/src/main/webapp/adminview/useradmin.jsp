<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Панель администрирования пользователей</title>

</head>


<body>
<a href="${pageContext.request.contextPath}/logout">Выход</a>
<h5>Привет! ${authuser.login}</h5>

<h3>Панель управления пользователями</h3>
<c:if test="${users != null}">
    <table width="100%" cellspacing="0" cellpadding="4" border="1">
        <tbody>
        <tr>
            <th style="width: 100px">Id</th>
            <th style="width: 70px">Логин</th>
            <th>Пароль</th>
            <th>Тип</th>
            <th>User-Id</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Телефонный номер</th>
            <th>email</th>
            <th>Номер паспорта</th>
            <th>Дата выдачи паспорта</th>
            <th>Орган выдачи паспорта</th>
            <th></th>
            <th></th>

        </tr>
        <c:forEach items="${users}" var="user">
            <form>
                <tr>
                    <td><input name="id" type="text" readonly value=${user.id}></td>
                    <td><input name="login" required type="text" value=${user.login}></td>
                    <td><input name="password" required type="password" value=${user.password}></td>

                    <td><select name="role">
                        <option value="USER">USER</option>
                        <option value="ADMIN">ADMIN</option>
                        <option selected value="${user.role}">${user.role}</option>
                    </select></td>

                    <td><input name="user_id" type="text" readonly value=${user.userId}></td>
                    <td><input name="firstname" required type="text" value=${user.firstName}></td>
                    <td><input name="lastname" required type="text" value=${user.lastName}></td>
                    <td><input name="phone" required type="text" value=${user.phone}></td>
                    <td><input name="email" required type="email" value=${user.email}></td>
                    <td><input name="passport_number" required type="text" value=${user.passport_number}></td>
                    <td><input name="passport_data" required type="date" value=${user.passport_data}></td>
                    <td><input name="passport_authority" required type="text" value=${user.passport_authority}></td>
                    <td><input formaction="update" formmethod="post" type="submit" value="обновить"></td>
                    <td><input formaction="delete" formmethod="post" type="submit" value="удалить"></td>
                        <%--                    <td><input type="checkbox" name="id" value=${user.id} > </td>--%>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
    <%--        <input formaction="useradmin" formmethod="post" type="submit" value="обновить">--%>
</c:if>
<h3>Добавить нового пользователя</h3>
<table width="70%" cellspacing="0" cellpadding="4" border="1">
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Тип</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Телефонный номер</th>
        <th>email</th>
        <th>Номер паспорта</th>
        <th>Дата выдачи паспорта</th>
        <th>Орган выдачи паспорта</th>
        <th></th>

    </tr>
    <form>
        <tr>
            <td><input name="login" required type="text" placeholder="логин"></td>
            <td><input name="password" required type="text" placeholder="пароль"></td>
            <td><select name="role">
                <option selected value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select></td>
            <td><input name="firstname" required type="text" placeholder="имя"></td>
            <td><input name="lastname" required type="text" placeholder="фамилия"></td>
            <td><input name="phone" required type="text" placeholder="телефон"></td>
            <td><input name="email" required type="email" placeholder="email"></td>
            <td><input name="passport_number" required type="text" placeholder="номер пасспорта"></td>
            <td><input name="passport_data" required type="date" placeholder="дата пасспорта"></td>
            <td><input name="passport_authority" required type="text" placeholder="орган выдачи пасспорта"></td>
            <td><input formaction="add" formmethod="post" type="submit" value="добавить"></td>
        </tr>
    </form>
</table>
</body>
</html>
