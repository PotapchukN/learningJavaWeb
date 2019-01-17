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

    <title>Скоринг</title>
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
                <a class="nav-link" href="#">Главная<span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${isAnket}">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/solvency"/>">Калькулятор кредитов</a>
            </li>
            </c:if>
            <c:if test="${isAdmin}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/solvency"/>">Калькулятор кредитов</a>
                </li>
            </c:if>
            <c:if test="${isLogin}">
                <c:if test="${!isAdmin}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/scoring/credit-rating"/>">Оценка кредитоспособности</a>
                    </li>
                </c:if>
            </c:if>
<c:if test="${isLogin}">
    <li class="nav-item">
        <a class="nav-link" href="<c:url value="/bids"/>">Заявки</a>
    </li>
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
            <a class="nav-item mr-2 enter" href="<c:url value='/login'/>">Вход</a>
        </c:if>
        <c:if test="${isLogin}">
            <a class="nav-item mr-2 enter" href="<c:url value='/logout'/>">Выйти</a>
        </c:if>

    </div>
</nav>

<!-- Bootstrap 4 -->
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="d-block img-fluid" src="<c:url value="/resources/images/imm.jpg"/>" alt="Первый слайд">
            <div class="carousel-caption d-none d-md-block">
                <h3>Потребительский кредит</h3>
                <div class="card bor bk-trans">
                    <div class="card-body flex-row mt-4 space-bet">
                        <div class="flex">
                            <div class="flex-row">
                                <span class=" col-black">Низкий</span>
                            </div>
                            <p class="freeCons smallText  col-black">Ежемесячный платеж</p>
                        </div>
                        <div class="flex">
                            <div class="flex-row">
                                <span class=" col-black">От 24 до 72 месяцев </span>
                            </div>
                            <p class="freeCons smallText  col-black">срок кредита</p>
                        </div>
                        <div class="flex">
                            <div class="flex-row">
                                <span class=" col-black">До 10 000 рублей</span>
                            </div>
                            <p class="freeCons smallText  col-black">наличными</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <img class="d-block img-fluid" src="<c:url value="/resources/images/oi.jpg"/>" alt="Второй слайд">
            <div class="carousel-caption d-none d-md-block">
                <h3>Ипотека</h3>
                <div class="card bor bk-trans">
                    <div class="card-body flex-row mt-4 space-bet">
                        <div class="flex col-black">
                            <div class="flex-row">
                                <span class="col-black">Легко</span>
                            </div>
                            <p class="freeCons smallText  col-black">без залога</p>
                        </div>
                        <div class="flex col-black">
                            <div class="flex-row">
                                <span class=" col-black">Быстро </span>
                            </div>
                            <p class="freeCons smallText  col-black">оформим за час </p>
                        </div>
                        <div class="flex col-black">
                            <div class="flex-row">
                                <span class=" col-black">До 40 000 рублей</span>
                            </div>
                            <p class="freeCons smallText col-black">сумма кредита</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <img class="d-block img-fluid" src="<c:url value="/resources/images/phone.jpg"/>" alt="Третий слайд">
            <div class="carousel-caption d-none d-md-block">
                <h3>Кредит на автомобиль</h3>
                <div class="card bor bk-trans">
                    <div class="card-body flex-row mt-4 space-bet">
                        <div class="flex">
                            <div class="flex-row col-black">
                                <span class=" col-black">Любое авто</span>
                            </div>
                            <p class="freeCons smallText  col-black">на Ваш выбор</p>
                        </div>
                        <div class="flexcol-black">
                            <div class="flex-row ">
                                <span class=" col-black">Доступно</span>
                            </div>
                            <p class="freeCons smallText  col-black">без лишних документов</p>
                        </div>
                        <div class="flex col-black">
                            <div class="flex-row ">
                                <span class=" col-black">До 40 000 рублей</span>
                            </div>
                            <p class="freeCons smallText  col-black">сумма кредита</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="row">
    <div class="col-sm-6">
        <div class="card wid">
            <img class="card-img-top" src="<c:url value="/resources/images/cash.jpg"/>" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title center">Кредит наличными</h5>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">Низкий ежемесячный платеж</li>
                <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">До 72 месяцев</li>
                <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">До 40 000 рублей</li>
            </ul>
            <div class="card-body">
                <a href="#" class="btn btn-dark">Подробнее...</a>
            </div>
        </div>
    </div>

    <div class="col-sm-6">
        <div class="card wid">
            <img class="card-img-top" src="<c:url value="/resources/images/main.jpg"/>" alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title center">Кредит народный</h5>
            </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">На товары белорусского производства</li>
                    <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">Ставка 20% годовых</li>
                    <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="checkmark">До 5 000 рублей</li>
                </ul>
            <div class="card-body">
                <a href="#" class="btn btn-dark">Подробнее...</a>
            </div>
        </div>
    </div>
</div>


<c:import url="footer/footer.jsp"/>


</body>
</html>
