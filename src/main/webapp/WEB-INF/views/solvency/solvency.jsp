<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Платежеспособность</title>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <c:set value="0" var="x"/>
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <%--<script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>--%>
    <%--bootstrap--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Подключаем jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Подключаем плагин Popper -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

    <!-- Подключаем Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</head>




<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">МойБанк</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/scoring"/>">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Калькулятор кредитов <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${isLogin}">
                <c:if test="${!isAdmin}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/scoring/credit-rating"/>">Оценка кредитоспособности</a>
                    </li>
                </c:if>
            </c:if>
            <c:if test="${isAdmin}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/statistic"/>">Статистика клиентов</a>
                </li>
            </c:if>
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
        <c:if test="${!isLogin}">
            <a class="nav-item mr-2 grey-color" href="<c:url value='/login'/>">Вход</a>
        </c:if>
        <c:if test="${isLogin}">
            <a class="nav-item mr-2 grey-color" href="<c:url value='/logout'/>">Выйти</a>
        </c:if>

    </div>
</nav>


<c:if test="${!isAdmin}">
    <small class="form-text text-muted newsmall">Предлагаем Вам ознакомиться с услугой "Расчет максимальной суммы кредита".
    <br>Оцените свою платежеспособность на основании заполненной Вами скоринг-анкеты</small>
</c:if>
<div class="flex-column">


    <c:if test="${Calculate0}">
        <div class="card mt-3 ml-5 width-for-calculateSolvency bluedark">
            <form  action="<c:url value="/solvency/calculate/${id}"/>" method="post">
                <div class="form-group">
                    <h5>Платежеспособность: ${fio}</h5>
                    <label>
                        <select size="${countOfCredits}" name="cred" class="form-control">
                            <option disabled>Кредит:</option>
                            <c:forEach items="${listCredits}" var="credit">
                                <option value="${credit.type}" selected >
                                        ${credit.type}
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                </div>


                <div class="form-group">
                    <input type="text"  name="year" class="form-control mb-2 mr-sm-2"  placeholder="Количество лет" required>
                </div>
                <div class="form-group"><button type="submit" class="btn btn-primary">Рассчитать</button></div>
            </form>
        </div>
    </c:if>

    <c:if test="${adminCalculate}">
        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Кредит</th>
                    <th>Имя Фамилия</th>
                    <th>Сумма (бел руб)</th>
                    <th>Срок (год)</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${creditType}</td>
                    <td>${fio}</td>
                    <td>${maxSummCredit}</td>
                    <td>${year}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>


<div class="flex-row flex-space-evenly">
    <div class="card">
        <div class="card-header">Кредиты</div>
        <div class="card-body">

            <div class="container">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Кредит</th>
                        <th>Процент</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listCredits}" var="credit">
                    <tr>
                        <td>${credit.type}</td>
                        <td>${credit.percent}%</td>
                    </tr>
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
        <c:if test="${!isAdmin}">
    <div class="card">
        <div class="card-header">Оценка платежеспособности</div>
        <div class="card-body">
            <h5 class="card-title">Рассчитать максимальную сумму кредита:</h5>

            <form  action="<c:url value="/solvency/calculate"/>" method="post">

                <div class="form-group">
                <label>
                    <select size="${countOfCredits}" name="cred" class="form-control">
                        <option disabled>Кредит:</option>
                        <c:forEach items="${listCredits}" var="credit">
                            <option value="${credit.type}" selected >
                                    ${credit.type}
                            </option>
                        </c:forEach>
                    </select>
                </label>
                </div>


                <div class="form-group">
                <input type="text"  name="year" class="form-control mb-2 mr-sm-2"  placeholder="Количество лет" required>
                </div>
                <div class="form-group"><button type="submit" class="btn btn-primary">Рассчитать</button></div>
            </form>

            <c:if test="${!empty maxSummCredit}">
                <h5 class="section-of-the-questionnaire"> Максимальный размер кредита:<br/>
                    "${creditType}"
                    <br/> для Вас составит ${maxSummCredit} бел. руб.</h5>
            </c:if>
        </div>
    </div>
        </c:if>
        <c:if test="${isAdmin}">
            <div class="card">
                <div class="card-header">Пользователи, успешно прошедшие скоринг-анкету:</div>
                <div class="card-body">

                    <div class="container">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Имя Фамилия</th>
                                <th>E-mail</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${listPaymantForUsers}" var="user1">

                            <tr>
                                <td>${user1.user.name} ${user1.user.surname}</td>
                                <td>${user1.user.email}</td>
                                <td>

                                    <form action="<c:url value="/solvency/calculate/${user1.user.id}"/>" method="get">
                                        <button class="btn btn-primary" type="submit"
                                                name="lala" value ="/solvency/calculate/${user1.user.id}}">Рассчитать...</button>
                                    </form>
                                <td>

                            </tr>
                            </tbody>
                            </c:forEach>
                        </table>

                    </div>
                </div>
            </div>
        </c:if>
    </div>

</div>


<c:import url="../footer/footer.jsp"/>


</body>
</html>
