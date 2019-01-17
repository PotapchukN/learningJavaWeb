<%--
  Created by IntelliJ IDEA.
  User: Nastya
  Date: 20.10.2018
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8"%>
<%--<%request.setCharacterEncoding("UTF-8");%>--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Успешная регистрация</title>
</head>
<body>
<p>Вы были успешно зарегистрированы.Проверьте почту для активации вашего аккаунта.</p>
<h4 ><a href="${contextPath}/login">Вход</a></h4>
</body>
</html>
