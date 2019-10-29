<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<%--    <title><fmt:message key="page.name.index" bundle="${messages}"/></title>--%>
<%--    <title><fmt:message key="page.name.index" bundle="${messages}"/></title>--%>

    <fmt:setLocale value="${locale}"/>
    <fmt:setBundle basename="localizations" var="messages"/>
    <title><fmt:message key="page.name.index" bundle="${messages}"/></title>

</head>
<body>
<h2><fmt:message key="page.name.index" bundle="${messages}"/></h2>
<%--<h3><fmt:message key="page.name.index" bundle="${messages}"/></h3>--%>
<c:choose>
    <c:when test="${authuser == null}">
        <jsp:include page="/login.jsp"></jsp:include>
    </c:when>
    <c:otherwise>
        <h5>Привет! ${authuser.login}</h5>
        <a href="${pageContext.request.contextPath}/userpage">Личный кабинет</a>
        <a href="${pageContext.request.contextPath}/logout">Выход</a>
    </c:otherwise>
</c:choose>
<hr>

<c:if test="${autos != null}">mvn
    <table width="70%" cellspacing="0" cellpadding="4" border="1">
        <tbody>
        <tr>
            <th style="width: 70px">Марка</th>
            <th>Модель</th>
            <th>Тип топлива</th>
            <th>Год</th>
            <th>Цена/сутки</th>
            <c:if test="${authuser != null}">
                <th >Начало аренды</th>
                <th>Конец аренды</th>

            </c:if>

        </tr>
        <c:forEach items="${autos}" var="auto">
            <tr>
                <c:choose>
                    <c:when test="${authuser == null}">
                        <td>${auto.brand}</td>
                        <td>${auto.model}</td>
                        <td>${auto.fuel}</td>
                        <td>${auto.date}</td>
                        <td>${auto.price}</td>
                    </c:when>
                    <c:otherwise>
                        <form>
                            <td><input name="brand" required type="text" value=${auto.brand}></td>
                            <td><input name="model" required type="text" value=${auto.model}></td>
                            <td><input name="fuel" required type="text" value=${auto.fuel}></td>
                            <td><input name="date" required type="text" value=${auto.date}></td>
                            <td><input name="price" required type="text" value=${auto.price}></td>
                            <td><input name="startOrderDate" required type="datetime-local"  placeholder="Начало аренды"></td>
                            <td><input name="stopOrderDate" required type="datetime-local"  placeholder="Конец аренды"></td>
                            <input type="hidden" name="authuserId" value=${authuser.id}>
                            <input type="hidden" name="autoId" value=${auto.id}>
                           <td><input formaction="${pageContext.request.contextPath}/userpage" formmethod="post" type="submit" value="забронировать"></td>
                        </form>


                    </c:otherwise>


                </c:choose>


                    <%--                    <td><input type="checkbox" name="id" value=${user.id} > </td>--%>
            </tr>

        </c:forEach>
        </tbody>
    </table>


</c:if>

</body>
</html>
