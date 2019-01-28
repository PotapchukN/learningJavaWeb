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

    <script src="http://benpickles.github.io/peity/jquery.peity.js"> </script>


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

    <!-- Подключаем Piety Donut -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-beta.2/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2017/peity/jquery.peity.min.js"></script>


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
                <a class="nav-link" href="#">Оценка кредитоспособности<span class="sr-only">(current)</span></a>
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
            <a class="nav-item mr-2 grey" href="<c:url value='/logout'/>">Выйти</a>
        </c:if>

    </div>
</nav>

<%--------------------------------------------Progress-------------------------------------------------%>

<div class="progress-block">

<c:if test="${!empty anketStart}">
    <small class="form-text text-muted anksmall">Пожалуйста, заполните скоринг-анкету, для оценки кредитоспособности
    Минимальный балл-400, максимальный-1500</small>
</c:if>
<c:if test="${empty anketStart}">
    <div class="mrsmall">
    <small class="form-text text-muted mr-5">Спасибо за заполнение скоринг-анкеты!</small>

    <form action="<c:url value="/rating/openAnket"/>" method="get">
        <button id="openAnket" type="submit" class="btn btn-dark size-btn-for-against-question step0" name="openAnket" value ="/categorys/openAnket">Пройти ещё раз</button>
    </form>
    </div>
</c:if>
    <h5 class="caption-of-the-questionnaire mb-3 mt-4 card-title">Скоринг-анкета:</h5>
    <div class="progress">
        <div class="progress-bar progress-bar-striped active" id="progress-bar"
            role="progressbar" aria-valuenow="0" aria-valuemin="0"
            aria-valuemax="100" style="width: 2%;">

        </div>
    </div>
</div>


<%--------------------------------------------Collapse-------------------------------------------------%>


<div id="accordion">
    <c:if test="${!empty anketStart}">
<c:if test="${not empty running_success}">
    <form class="section-aboutMyself" action="<c:url value="/rating/general-info"/>"
          method="post">
        <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Личные данные</h5>
        <div class="card">
            <div class="card-header" id="headingOne">
                <h5 class="mb-0" id="age">
                    <button  type="button" class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Возраст
                    </button>
                </h5>
            </div>
            <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio1" name="customRadio1" class="custom-control-input sr-only" required value="Меньше 20 и больше 60">
                        <label class="custom-control-label" for="customRadio1">Младше 20 и старше 60</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio2" name="customRadio1" class="custom-control-input sr-only" required value="От 20 до 25">
                        <label class="custom-control-label" for="customRadio2">От 20 до 25</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio3" name="customRadio1" class="custom-control-input sr-only" required value="От 26 до 30">
                        <label class="custom-control-label" for="customRadio3">От 26 до 30</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio4" name="customRadio1" class="custom-control-input sr-only" required value="От 31 до 35">
                        <label class="custom-control-label" for="customRadio4">От 31 до 35</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio5" name="customRadio1" class="custom-control-input sr-only" required value="От 36 до 50">
                        <label class="custom-control-label" for="customRadio5">От 36 до 50</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio6" name="customRadio1" class="custom-control-input sr-only" required value="От 51 до 60">
                        <label class="custom-control-label" for="customRadio6">От 51 до 60</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwo">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Есть ли у Вас гражданство РБ?
                    </button>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio7" name="customRadio2" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio7">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio8" name="customRadio2" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio8">Нет</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio9" name="customRadio2" class="custom-control-input sr-only" required value="Вид на жительство">
                        <label class="custom-control-label" for="customRadio9">Вид на жительство</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThree">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Есть ли постоянная прописка на территории РБ?
                    </button>
                </h5>
            </div>
            <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio10" name="customRadio3" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio10">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio11" name="customRadio3" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio11">Нет</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFour">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                        Вы проживаете:
                    </button>
                </h5>
            </div>
            <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio12" name="customRadio4" class="custom-control-input sr-only" required value="Собственное жилье">
                        <label class="custom-control-label" for="customRadio12">Собственное жилье</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio13" name="customRadio4" class="custom-control-input sr-only" required value="Съемное жилье">
                        <label class="custom-control-label" for="customRadio13">Съемное жилье</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio14" name="customRadio4" class="custom-control-input sr-only" required value="У родственников">
                        <label class="custom-control-label" for="customRadio14">У родственников</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFive">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                        Семейное положение:
                    </button>
                </h5>
            </div>
            <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio15" name="customRadio5" class="custom-control-input sr-only" required value="Холост/не замужем">
                        <label class="custom-control-label" for="customRadio15">Холост/не замужем</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio16" name="customRadio5" class="custom-control-input sr-only" required value="Женат/замужем">
                        <label class="custom-control-label" for="customRadio16">Женат/замужем</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio17" name="customRadio5" class="custom-control-input sr-only" required value="В разводе">
                        <label class="custom-control-label" for="customRadio17">В разводе</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio18" name="customRadio5" class="custom-control-input sr-only" required value="Вдовец/вдова">
                        <label class="custom-control-label" for="customRadio18">Вдовец/вдова</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingSix">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                        Количество детей:
                    </button>
                </h5>
            </div>
            <div id="collapseSix" class="collapse" aria-labelledby="headingSix" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio19" name="customRadio6" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio19">Нет</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio20" name="customRadio6" class="custom-control-input sr-only" required value="Один">
                        <label class="custom-control-label" for="customRadio20">Один</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio21" name="customRadio6" class="custom-control-input sr-only" required value="Два">
                        <label class="custom-control-label" for="customRadio21">Два</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio22" name="customRadio6" class="custom-control-input sr-only" required value="Три и более">
                        <label class="custom-control-label" for="customRadio22">Три и более</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingSeven">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                        Есть ли у Вас военная обязанность?
                    </button>
                </h5>
            </div>
            <div id="collapseSeven" class="collapse" aria-labelledby="headingSeven" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio23" name="customRadio7" class="custom-control-input sr-only" required value="Не военнообязанный">
                        <label class="custom-control-label" for="customRadio23">Не военнообязанный</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio24" name="customRadio7" class="custom-control-input sr-only" required value="Отсрочка">
                        <label class="custom-control-label" for="customRadio24">Отсрочка</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio25" name="customRadio7" class="custom-control-input sr-only" required value="Отслужил">
                        <label class="custom-control-label" for="customRadio25">Отслужил</label>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit"
                class="btn btn-primary size-btn step" onsubmit="prog()">Далее</button>
    </form>
</c:if>
    <%------------------------------------Новый раздел, новая форма---------------работа-------------------------%>
    <c:if test="${not empty success_general_info}">
    <form class="section-work" action="<c:url value="/rating/job-info"/>"
          method="post">
        <script> $("#progress-bar").css("width", "40%");</script>
        <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Образование и работа</h5>
        <div class="card">
            <div class="card-header" id="headingEight">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEight" aria-expanded="false" aria-controls="collapseEight">
                        Ваше образование:
                    </button>
                </h5>
            </div>
            <div id="collapseEight" class="collapse" aria-labelledby="headingEight" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio26" name="customRadio8" class="custom-control-input sr-only" required value="Среднее">
                        <label class="custom-control-label" for="customRadio26">Среднее</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio27" name="customRadio8" class="custom-control-input sr-only" required value="Техническое">
                        <label class="custom-control-label" for="customRadio27">Техническое</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio28" name="customRadio8" class="custom-control-input sr-only" required value="Неоконченное высшее">
                        <label class="custom-control-label" for="customRadio28">Неоконченное высшее</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio29" name="customRadio8" class="custom-control-input sr-only" required value="Высшее">
                        <label class="custom-control-label" for="customRadio29">Высшее</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingNine">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseNine" aria-expanded="false" aria-controls="collapseNine">
                        Род деятельности:
                    </button>
                </h5>
            </div>
            <div id="collapseNine" class="collapse" aria-labelledby="headingNine" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio30" name="customRadio9" class="custom-control-input sr-only" required value="Работник бюджетной структуры">
                        <label class="custom-control-label" for="customRadio30">Работник бюджетной структуры</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio31" name="customRadio9" class="custom-control-input sr-only" required value="Сотрудник коммерческой фирмы">
                        <label class="custom-control-label" for="customRadio31">Сотрудник коммерческой фирмы</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio32" name="customRadio9" class="custom-control-input sr-only" required value="Пенсионер">
                        <label class="custom-control-label" for="customRadio32">Пенсионер</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio33" name="customRadio9" class="custom-control-input sr-only" required value="Иное">
                        <label class="custom-control-label" for="customRadio33">Иное(ИП)</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTen" aria-expanded="false" aria-controls="collapseTen">
                        Уровень квалификации:
                    </button>
                </h5>
            </div>
            <div id="collapseTen" class="collapse" aria-labelledby="headingTen" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio34" name="customRadio10" class="custom-control-input sr-only" required value="Отсутствует">
                        <label class="custom-control-label" for="customRadio34">Отсутствует</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio35" name="customRadio10" class="custom-control-input sr-only" required value="Обслуживающий персонал">
                        <label class="custom-control-label" for="customRadio35">Обслуживающий персонал</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio36" name="customRadio10" class="custom-control-input sr-only" required value="Специалисты и служащие">
                        <label class="custom-control-label" for="customRadio36">Специалисты и служащие</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio37" name="customRadio10" class="custom-control-input sr-only" required value="Руководитель">
                        <label class="custom-control-label" for="customRadio37">Руководитель</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingEleven">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEleven" aria-expanded="false" aria-controls="collapseEleven">
                        Трудовой стаж:
                    </button>
                </h5>
            </div>
            <div id="collapseEleven" class="collapse" aria-labelledby="headingEleven" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio38" name="customRadio11" class="custom-control-input sr-only" required value="Отсутствует">
                        <label class="custom-control-label" for="customRadio38">Отсутствует</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio39" name="customRadio11" class="custom-control-input sr-only" required value="До 2 лет">
                        <label class="custom-control-label" for="customRadio39">До 2 лет</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio40" name="customRadio11" class="custom-control-input sr-only" required value="До 5 лет">
                        <label class="custom-control-label" for="customRadio40">До 5 лет</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio41" name="customRadio11" class="custom-control-input sr-only" required value="Более 5 лет">
                        <label class="custom-control-label" for="customRadio41">Более 5 лет</label>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary size-btn step" onsubmit="prog2()">Далее</button>
    </form>
    </c:if>
    <%------------------------------------Новый раздел, новая форма---------------кредитная история-------------------------%>

    <c:if test="${not empty success_job_info}">
    <form class="section-creditHistory" action="<c:url value="/rating/credit-info"/>"
          method="post">
        <script>$("#progress-bar").css("width", "60%");</script>
        <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Кредитная история</h5>
        <div class="card">
            <div class="card-header" id="headingTwelve">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwelve" aria-expanded="false" aria-controls="collapseTwelve">
                        Есть ли у Вас действующие кредиты?
                    </button>
                </h5>
            </div>
            <div id="collapseTwelve" class="collapse" aria-labelledby="headingTwelve" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio42" name="customRadio12" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio42">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio43" name="customRadio12" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio43">Нет</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingThirdeen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThirdeen" aria-expanded="false" aria-controls="collapseThirdeen">
                        Ежемесячные выплаты по кредитам
                    </button>
                </h5>
            </div>
            <div id="collapseThirdeen" class="collapse" aria-labelledby="headingThirdeen" data-parent="#accordion">
                <div class="card-body">
                    <label for="input_loan_payments">Сумма ежемесячных выплат по кредитам</label>
                    <input type="text" name = "loan_payments"
                           id="input_loan_payments" class="form-control"
                           aria-describedby="passwordHelpBlock" required/>
                    <small id="textHelpBlock" class="form-text text-muted">
                        Вводите в бел. руб. В случае, если кредитов нет, вводите 0.
                    </small>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingFourteen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFourteen" aria-expanded="false" aria-controls="collapseFourteen">
                        Являетесь ли Вы клиентом нашего банка?
                    </button>
                </h5>
            </div>
            <div id="collapseFourteen" class="collapse" aria-labelledby="headingFourteen" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio44" name="customRadio14" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio44">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio45" name="customRadio14" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio45">Нет</label>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary size-btn step" onsubmit="prog3()">Далее</button>
    </form>
    </c:if>

    <%------------------------------------Новый раздел, новая форма---------------доходы-расходы-----------------------%>
    <c:if test="${not empty success_credit_info}">
    <form class="section-income" action="<c:url value="/rating/income-info"/>"
          method="post">
        <script>$("#progress-bar").css("width", "75%");</script>
        <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Информация о доходах и расходах</h5>
        <div class="card">
            <div class="card-header" id="headingFifteen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseFifteen" aria-expanded="false" aria-controls="collapseFifteen">
                        Общий доход
                    </button>
                </h5>
            </div>
            <div id="collapseFifteen" class="collapse" aria-labelledby="headingFifteen" data-parent="#accordion">
                <div class="card-body">
                    <label for="input_general_income">Сумма общего дохода семьи</label>
                    <input type="text" name = "general_income"
                           id="input_general_income" class="form-control"
                           aria-describedby="textHelpBlock" required/>
                    <small id="text2HelpBlock" class="form-text text-muted">
                        Вводите в бел. руб. Если Вы живете один, записывайте только свой доход. Не забывайте про вклады!
                    </small>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingSixteen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSixteen" aria-expanded="false" aria-controls="collapseSixteen">
                        Коммунальные платежи
                    </button>
                </h5>
            </div>
            <div id="collapseSixteen" class="collapse" aria-labelledby="headingSixteen" data-parent="#accordion">
                <div class="card-body">
                    <label for="input_communal_payments">Сумма расходов на коммунальные платежи</label>
                    <input type="text" name = "communal_payments"
                           id="input_communal_payments" class="form-control"
                           aria-describedby="textHelpBlock" required/>
                    <small id="text3HelpBlock" class="form-text text-muted">
                        Вводите в бел. руб. Если коммунальные платежи входят в стоимость съема жилья, введите 0
                    </small>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingSeventeen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseSeventeen" aria-expanded="false" aria-controls="collapseSeventeen">
                        Расходы на съем жилья
                    </button>
                </h5>
            </div>
            <div id="collapseSeventeen" class="collapse" aria-labelledby="headingSeventeen" data-parent="#accordion">
                <div class="card-body">
                    <label for="input_rent_house">Ежемесячная стоимость съема жилья</label>
                    <input type="number" name = "rent_house"
                           id="input_rent_house" class="form-control"
                           aria-describedby="textHelpBlock" required/>
                    <small id="text4HelpBlock" class="form-text text-muted">
                    Вводите 0, если проживаете с родителями, либо у родственников, и не платите за съем помещения
                    </small>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingEighteen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseEighteen" aria-expanded="false" aria-controls="collapseEighteen">
                        Прочие обязательные расходы
                    </button>
                </h5>
            </div>
            <div id="collapseEighteen" class="collapse" aria-labelledby="headingEighteen" data-parent="#accordion">
                <div class="card-body">
                    <label for="input_additional_payments">Ежемесячные обязательные выплаты (алименты, страхование и т.п.)</label>
                    <input type="text" name = "additional_payments"
                           id="input_additional_payments" class="form-control"
                           aria-describedby="textHelpBlock" required/>
                    <small id="text5HelpBlock" class="form-text text-muted">
                        Вводите 0, если у Вас нет дополнительных ежемесячных выплат. Не забывайте, что выплаты по кредитам Вы УЖЕ указали выше!
                    </small>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary size-btn step">Далее</button>
    </form>
    </c:if>

    <%------------------------------------Новый раздел, новая форма---------------Имущество-----------------------%>

    <c:if test="${not empty success_income_info}">
    <form class="section-holding" action="<c:url value="/rating/holding-info"/>"
          method="post">
        <script>$("#progress-bar").css("width", "95%");</script>
        <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Имущество</h5>
        <div class="card">
            <div class="card-header" id="headingNineteen">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseNineteen" aria-expanded="false" aria-controls="collapseNineteen">
                        Есть ли у Вас в собственности квартира или дом?
                    </button>
                </h5>
            </div>
            <div id="collapseNineteen" class="collapse" aria-labelledby="headingNineteen" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio46" name="customRadio19" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio46">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio47" name="customRadio19" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio47">Нет</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header" id="headingTwenty">
                <h5 class="mb-0">
                    <button type="button" class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwenty" aria-expanded="false" aria-controls="collapseTwenty">
                        Есть ли у Вас в собственности автомобиль?
                    </button>
                </h5>
            </div>
            <div id="collapseTwenty" class="collapse" aria-labelledby="headingTwenty" data-parent="#accordion">
                <div class="card-body">
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio48" name="customRadio20" class="custom-control-input sr-only" required value="Да">
                        <label class="custom-control-label" for="customRadio48">Да</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input type="radio" id="customRadio49" name="customRadio20" class="custom-control-input sr-only" required value="Нет">
                        <label class="custom-control-label" for="customRadio49">Нет</label>
                    </div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-dark size-btn step">Готово!</button>
    </form>
    </c:if>
    </c:if>

    <c:if test="${not empty success_holding_info}">
    <div class="section-property background-for-section-property">

        <script>
            $("#progress-bar").css("width", "100%");
            $(".caption-of-the-questionnaire").text("Скоринг-Анкета пройдена!");
        </script>


        <div class="card mb-5">
            <div class="card-header">
                <h5 class="section-of-the-questionnaire mb-3 pt-2 ml-3 card-title">Ваш результат:<br>
                    <c:if test="${red}">
                        <p class="ml-5 red">${score}</p>

                        <div class="col-md-6 offset-md-3 ml-5">
                            <span data-peity='{ "fill": ["red", "#eeeeee"], "innerRadius": 14, "radius": 36 }'>${score}/1500</span>
                        </div>
                        <script>
                            $(".offset-md-3 span").peity("donut")
                        </script>
                        <p class="red">Высокая вероятность отказа</p>
                    </c:if>
                <c:if test="${yellow}">
                    <p class="ml-5 yellow">${score}</p>

                    <div class="col-md-6 offset-md-3 ml-5">
                        <span data-peity='{ "fill": ["yellow", "#eeeeee"], "innerRadius": 14, "radius": 36 }'>${score}/1500</span>
                    </div>
                    <script>
                        $(".offset-md-3 span").peity("donut")
                    </script>
                    <p class="ml-5 yellow">Приемлимый результат</p>
                </c:if>
                <c:if test="${green}">
                    <p class="ml-5 green">${score}</p>
                    <div class="col-md-6 offset-md-3 ml-5">
                        <span data-peity='{ "fill": ["green", "#eeeeee"], "innerRadius": 14, "radius": 36 }'>${score}/1500</span>
                    </div>
                    <script>
                        $(".offset-md-3 span").peity("donut")
                    </script>
                    <p class="green">Высокая вероятность получения кредита</p>
                </c:if>
                </h5>
            </div>
            <div class="card-body">
                <h5 class="section-of-the-questionnaire mb-3 pt-2 card-title">Факторы:</h5>
            </div>
            <c:if test="${not empty listFactors}">
            <c:forEach items="${listFactors}" var="fact">
                <blockquote class="blockquote mb-0">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="size-icon">
                            ${fact}</li>
                    </ul>
                </blockquote>
            </c:forEach>
            </c:if>
            <c:if test="${not empty successlistFactors}">
                <blockquote class="blockquote mb-0">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><img src="<c:url value="/resources/images/checkmark.png"/>" class="size-icon">
                                Не выявлено факторов, существенно снижающих балл анкеты!</li>
                    </ul>
                </blockquote>
            </c:if>
        </div>
    </div>
    </c:if>
</div>



<c:import url="../footer/footer.jsp"/>

</body>
</html>
