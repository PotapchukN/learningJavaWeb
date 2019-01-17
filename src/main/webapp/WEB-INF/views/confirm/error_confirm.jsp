<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 20.10.2018
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>
<%--<%request.setCharacterEncoding("UTF-8");%>--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Ошибка подтверждение email</title>
</head>
<body>
<p>Ваш email не подтвержден</p>
<h4 ><a href="${contextPath}/registration">Регистрация</a></h4>
</body>
</html>
