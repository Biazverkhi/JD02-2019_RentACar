<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h1></h1>
<c:choose>
    <c:when test="${authuser == null}">
        <h2>Введите логин и пароль
            <div class="form">
                <form>
                    <input name="login" required type="text" placeholder="login"><i> Логин </i><br>
                    <input name="password" required type="password" placeholder="password"><i> Пароль </i><br>
                    <input formaction="${pageContext.request.contextPath}/login" formmethod="post" type="submit"
                           value="Войти"><br>
                    <a href="${pageContext.request.contextPath}/registration">Регистрация</a>
                </form>
            </div>
        </h2>
    </c:when>
    <c:otherwise>
        <h5>Привет! ${authuser.login}</h5>
        <a href="${pageContext.request.contextPath}/userpage">Личный кабинет</a>
        <a href="${pageContext.request.contextPath}/logout">Выход</a>
    </c:otherwise>
</c:choose>
<c:choose>

    <c:when test="${authuser == null}">

        <p style="color: #5fff6e">${error}</p>
    </c:when>
</c:choose>


</body>
</html>
