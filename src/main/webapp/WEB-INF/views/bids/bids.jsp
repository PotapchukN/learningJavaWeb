<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Заявки</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">

    <%--bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Подключаем jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Подключаем плагин Popper -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <!-- Подключаем Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <!-- Подключаем Google Donut -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">МойБанк</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/scoring"/>">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/solvency"/>">Калькулятор кредитов</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="#">Статистика клиентов<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Виды кредитов
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Потребительский</a>
                    <a class="dropdown-item" href="#">Ипотека</a>
                    <a class="dropdown-item" href="#">Кредит на авто</a>
                </div>
            </li>
        </ul>
        <c:if test="${isLogin}">
            <a class="nav-item mr-2 enter" href="<c:url value='/logout'/>">Выйти</a>
        </c:if>
    </div>
</nav>

<c:if test="${!empty listBids}">
<div class="container mt-3">
    <p class="mb-3 mt-2 ml-5">Информация по кредитам, которые наш банк может Вам предложить:</p>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Кредит</th>
            <th>Максимальный срок</th>
            <th>Максимальная сумма</th>
            <th>Поручители</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listBids}" var="bid">
        <tr>
            <td>${bid.credit.type}</td>
            <td>${bid.maxTerm} мес</td>
            <td>${bid.maxSum}</td>
            <td>${bid.guarantor}</td>
        </tr>
        </c:forEach>
        <tr>
            <th>Дата</th>
            <th>Время</th>
        </tr>
        <tr>
        <td>${bidDate}</td>
        <td>${bidTime}</td>
        </tr>
        </tbody>
    </table>
</div>
</c:if>
<c:if test="${empty listBids}">
    <p>Ваша скоринг-анкета обрабатывается, пожалуйста ожидайте</p>
</c:if>

<c:import url="../footer/footer.jsp"/>
</body>
</html>
