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

    <%--<script src="http://benpickles.github.io/peity/jquery.peity.js"> </script>--%>


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
            <a class="nav-item mr-2 grey-color" href="<c:url value='/logout'/>">Выйти</a>
        </c:if>

    </div>
</nav>


<c:if test="${sendBid}">
    <p id="textSendSuccess">Отправлено!</p>
</c:if>

<div class="flex-row mt-4">
    <form action="<c:url value="/statistic/badClients"/>" method="get">
        <button class="btn btn-outline-danger ml-4" type="submit"
                name="showBadClients" value ="/statistic/badClients">
            Клиенты с низким баллом
        </button>
    </form>
    <form action="<c:url value="/statistic/averageClients"/>" method="get">
        <button class="btn btn-outline-warning ml-5" type="submit"
                name="showAverageClients" value ="/statistic/averageClients">
            Клиенты с приемлимым баллом
        </button>
    </form>
    <form action="<c:url value="/statistic/goodClients"/>" method="get">
        <button class="btn btn-outline-success ml-5" type="submit"
                name="showGoodClients" value ="/statistic/goodClients">
            Клиенты с высоким баллом
        </button>
    </form>
</div>

<c:if test="${!empty listBadUsers}">
    <div class="container flex-row">
        <table class="table table-hover">
            <thead class="bk-red">
            <tr>
                <th>Имя Фамилия</th>
                <th>E-mail</th>
                <th>Балл скоринг-анкеты</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listBadUsers}" var="user">
            <tr>
                <td>${user.user.name} ${user.user.surname}</td>
                <td>${user.user.email}</td>
                <td>${user.score}</td>
                <td>
                    <form action="<c:url value="/statistic/anketClientBad/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-danger" type="submit"
                                name="showAnketClientBad" value ="/statistic/anketClientBad/${user.user.id}">
                            Анкета...
                        </button>
                    </form>

                </td>
                <td>
                    <form action="<c:url value="/statistic/showBidForm/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-success" type="submit"
                                name="btnShowBidForm" value ="/statistic/showBidForm/${user.user.id}">
                            Отправить заявку...
                        </button>
                    </form>
                </td>
            </tr>
            </tbody>
            </c:forEach>
        </table>
        <c:if test="${!empty listUserAnswers}">
            <div class="container">
                <table class="table table-hover">
                    <thead class="bk-red">
                    <tr>
                        <th>Вопрос</th>
                        <th>Ответ</th>
                        <th>Балл</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listUserAnswers}" var="info">
                    <c:if test="${!empty info.risk}">
                        <tr class="red1">
                        <td>${info.answers.questions.question}</td>
                        <td>${info.answers.answer}</td>
                        <td>${info.score_for_user}</td>
                    </tr></c:if>
                    <c:if test="${empty info.risk}"><tr>
                        <td>${info.answers.questions.question}</td>
                        <td>${info.answers.answer}</td>
                        <td>${info.score_for_user}</td>
                    </tr></c:if>
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
</c:if>

<c:if test="${!empty listAverageUsers}">
    <div class="container flex-row">
        <table class="table table-hover">
        <thead class="bk-yellow">
            <tr>
                <th>Имя Фамилия</th>
                <th>E-mail</th>
                <th>Балл скоринг-анкеты</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${listAverageUsers}" var="user">
            <tr>
                <td>${user.user.name} ${user.user.surname}</td>
                <td>${user.user.email}</td>
                <td>${user.score}</td>
                <td>
                    <form action="<c:url value="/statistic/anketClientAverage/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-warning" type="submit"
                                name="showAnketClientAverage" value ="/statistic/anketClientAverage/${user.user.id}">
                            Анкета...
                        </button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/statistic/showBidForm/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-success" type="submit"
                                name="btnShowBidForm" value ="/statistic/showBidForm/${user.user.id}">
                            Отправить заявку...
                        </button>
                    </form>
                </td>
            </tr>
        </tbody>
        </c:forEach>
        </table>
        <c:if test="${!empty listUserAnswers}">
            <div class="container">
                <table class="table table-hover">
                    <thead class="bk-yellow">
                    <tr>
                        <th>Вопрос</th>
                        <th>Ответ</th>
                        <th>Балл</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listUserAnswers}" var="info">
                    <c:if test="${!empty info.risk}">
                        <tr class="red1">
                            <td>${info.answers.questions.question}</td>
                            <td>${info.answers.answer}</td>
                            <td>${info.score_for_user}</td>
                        </tr></c:if>
                    <c:if test="${empty info.risk}"><tr>
                        <td>${info.answers.questions.question}</td>
                        <td>${info.answers.answer}</td>
                        <td>${info.score_for_user}</td>
                    </tr></c:if>
                    </tbody>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </div>
</c:if>
<c:if test="${!empty listGoodUsers}">
    <div class="container flex-row">
        <table class="table table-hover">
            <thead class="bk-green">
            <tr>
                <th>Имя Фамилия</th>
                <th>E-mail</th>
                <th>Балл скоринг-анкеты</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listGoodUsers}" var="user">
            <tr>
                <td>${user.user.name} ${user.user.surname}</td>
                <td>${user.user.email}</td>
                <td>${user.score}</td>
                <td>
                    <form action="<c:url value="/statistic/anketClientGood/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-success" type="submit"
                                name="showAnketClientGood" value ="/statistic/anketClientGood/${user.user.id}">
                            Анкета...
                        </button>
                    </form>
                </td>
                <td>
                    <form action="<c:url value="/statistic/showBidForm/${user.user.id}"/>" method="get">
                        <button class="btn btn-outline-success" type="submit"
                                name="btnShowBidForm" value ="/statistic/showBidForm/${user.user.id}">
                            Отправить заявку...
                        </button>
                    </form>
                </td>
            </tr>
            </tbody>
            </c:forEach>
        </table>

                    <c:if test="${!empty listUserAnswers}">
                        <div class="container">
                            <table class="table table-hover">
                                <thead class="bk-green">
                                <tr>
                                    <th>Вопрос</th>
                                    <th>Ответ</th>
                                    <th>Балл</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${listUserAnswers}" var="info">
                                <c:if test="${!empty info.risk}">
                                    <tr class="red1">
                                        <td>${info.answers.questions.question}</td>
                                        <td>${info.answers.answer}</td>
                                        <td>${info.score_for_user}</td>
                                    </tr></c:if>
                                <c:if test="${empty info.risk}"><tr>
                                    <td>${info.answers.questions.question}</td>
                                    <td>${info.answers.answer}</td>
                                    <td>${info.score_for_user}</td>
                                </tr></c:if>
                                </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </c:if>
                </div>
</c:if>
<c:if test="${showBidForm}">
    <span class="bluedark ml-center"> Возможности для ${fio}</span>
    <form action="/statistic/sendBid/${id}" method="post">
        <div class="container">
            <table class="table table-hover">
                <tbody>
                <c:forEach items="${listCredit}" var="credit">
                    <tr>
                        <td><label>
                            <input type="text" name="name${credit.id}"
                                   class="form-control"
                                   value="${credit.type}" readonly/>
                        </label>
                        </td>
                        <td><input type="number" name="period${credit.id}"
                                   max="${credit.max_period*12}"
                                   class="form-control"
                                   placeholder="Максимальный срок в месяцах"/>
                            <small class="form-text text-muted">
                                Максимальный срок выплаты кредита: ${credit.max_period*12} мес
                            </small></td>
                        <td><div class="form-check">
                            <input type="checkbox" name ="guarantor${credit.id}"
                                   class="form-check-input" id="guarantor">
                            <label class="form-check-label" for="guarantor">Поручители</label>
                        </div></td>
                        <td><input type="number" name="sum${credit.id}"
                                   max="${credit.max_summ}"
                                   class="form-control"
                                   placeholder="Максимальная сумма"/>
                            <small class="form-text text-muted">
                                Максимальная возможная сумма на данный кредит ${credit.max_summ} бел. руб.
                            </small></td>
                    </tr>
                </c:forEach>

                <tr>
                    <th>Дата</th>
                    <th>Время</th>
                </tr>
                <tr>
                    <td>
                        <input type="date" name="date"
                               class="form-control"
                               placeholder="Дата"/>
                    </td>
                    <td>
                        <input type="time" name="time"
                               class="form-control"
                               placeholder="Время"/>
                    </td>
                </tr>
                </tbody>
            </table>
            <button type="submit" class="btn btn-outline-primary" name="btnSendBid">Отправить</button>
        </div>
    </form>
</c:if>

<hr class="black"/>


<div class = "flex-row-for-btnDrawDiagramm">

    <input type="button" class="btn btn-primary" onclick="drawChart(${redRisk},${averageRisk},${greenRisk})" value="Статистика кредитоспособности клиентов">
    <input type="button" class="btn btn-primary" onclick="drawBasic(${age},${citizenship},${registration},${housing},${sp},${army},
    ${education},${kindOfActivity},${skillLevel},${experience},
    ${credit},${flat})" value="Статистика рисков">
</div>

<div class="flex-row-for-donut" id="bb">
    <div class="size-block-for-donut">

        <div id="piechart"></div>
        <script>
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart(r,y,g){
                var data = google.visualization.arrayToDataTable([
                    ['Task', 'Hours per Day'],
                    ['Клиенты с низкой вероятностью получения кредиты',r],
                    ['Клиенты с удовлетворительной вероятностью получения кредита',y],
                    ['Клиенты с высокой вероятностью получения кредита',g]
                ]);

                var options = {
                    fontSize:12,
                    width: 700,
                    height:480,
                    slices: {
                        0: { color: '#ff0705' },
                        1: { color: '#fff22a' },
                        2: { color: '#11d626'}
                    },
                    backgroundColor:'transparent'
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                chart.draw(data, options);
            }

        </script>
    </div>
    <div class="size-risk-block">
        <div id="chart_div"></div>
        <script>
            google.charts.load('current', {packages: ['corechart', 'bar']});
            google.charts.setOnLoadCallback(drawBasic);

            function drawBasic(age,citizenship,registration,housing,sp,army, education,kindOfActivity,skillLevel,experience,
                               credit,flat) {

                var data = google.visualization.arrayToDataTable([
                    ['Риск', 'Количество клиентов', { role: 'style' }],
                    ['Возраст < 20 либо > 60 ', age, 'opacity: 0.2'],
                    ['Отсутствие гражданства ', citizenship, 'color: #76A7FA'],
                    ['Отсутствие прописки', registration, 'opacity: 0.2'],
                    ['Съемное жилье', housing, 'color: #76A7FA'],
                    ['Холост/не замужем', sp, 'color: #76A7FA'],
                    ['Отсрочка от армии', army, 'opacity: 0.2'],
                    ['Уровень образованич', education, 'color: #76A7FA'],
                    ['Пенсионеры, ИП, временно безработные', kindOfActivity, 'opacity: 0.2'],
                    ['Отсутствие квалификации', skillLevel, 'color: #76A7FA'],
                    ['Отсутствие стажа', experience, 'color: #76A7FA'],
                    ['Наличие кредита', credit, 'color: #76A7FA'],
                    ['Нет недвижимости', flat, 'color: #76A7FA']
                ]);
                var options = {
                    title: 'Population of Largest U.S. Cities',
                    chartArea: {width: '30%', height:'100%'},
                    backgroundColor:'transparent',
                    width: 700,
                    height:300,
                    fontSize:12,
                    hAxis: {
                        title: 'Количество клиентов',
                        minValue: 0
                    },
                    yAxis:{minValue: 0}
                };

                var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

                chart.draw(data, options);
            }
        </script>
    </div>
</div>


<c:import url="../footer/footer.jsp"/>

</body>
</html>
