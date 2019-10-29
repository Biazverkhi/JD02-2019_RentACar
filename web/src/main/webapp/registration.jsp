<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация нового пользователя</title>

</head>

<body>
<table width="50">
    <tr>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Телефонный номер</th>
        <th>email</th>
        <th>Номер паспорта</th>
        <th>Дата выдачи паспорта</th>
        <th>Орган выдачи паспорта</th>
    </tr>
    <form > <tr>
        <td><input name="login" required type="text"  placeholder="логин"></td>
        <td><input name="password" required type="password"  placeholder="пароль"></td>
        <td><input name="firstname" required type="text"  placeholder="имя"></td>
        <td><input name="lastname" required type="text"  placeholder="фамилия"></td>
        <td><input name="phone" required type="text"  placeholder="телефон"></td>
        <td><input name="email" required type="email"  placeholder="email"></td>
        <td><input name="passport_number" required type="text"  placeholder="номер пасспорта"></td>
        <td><input name="passport_data" required type="date"  placeholder="дата пасспорта"></td>
        <td><input name="passport_authority" required type="text"  placeholder="орган выдачи пасспорта"></td>
        <td> <input formaction="${pageContext.request.contextPath}/registration" formmethod="post" type="submit"  value="добавить"></td>
    </tr>
    </form>
</table>


</body>
</html>
