<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@page pageEncoding="UTF-8"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div class="card bg-light border-none">
    <div class="card-body flex-row space-around mr-5">
        <h5 class="card-title course ml-5">Курсы валют:</h5>
        <div class="flex-row space-between">
            <img src="<c:url value="/resources/images/america-flag.png"/>" class="size-icon"/>
            <span class="color-currencies">USD</span>
            <p class="mr-3">2,12</p>
            <p>2,163</p>
        </div>
        <div class="flex-row space-between">
            <img src="<c:url value="/resources/images/flag-es.png"/>" class="size-icon"/>
            <span class="color-currencies">EUR</span>
            <p class="mr-3">2,408</p>
            <p>2,468</p>
        </div>
        <div class="flex-row space-between">
            <img src="<c:url value="/resources/images/flagRuss.png"/>" class="size-icon"/>
            <span class="color-currencies">RUS</span>
            <p class="mr-3">3,01</p>
            <p>3,2</p>
        </div>
    </div>
</div>



<%---------------------------Паддинг---------------------------%>
<div class="card text-white bg-dark border-none">
    <div class="card-body flex-row mt-4 space-between">
        <h5 class="card-title light-grey-color">Бесплатная<br/>консультация 24/7</h5>
        <div class="flex">
            <div class="flex-row">
                <img src="<c:url value="/resources/images/phone.png"/>" class="bigger-size-icon"/>
                <span>198</span>
            </div>
            <p class="light-grey-color smallText ">Стационарный номер <br/>для физических лиц</p>
        </div>
        <div class="flex">
            <div class="flex-row">
                <img src="<c:url value="/resources/images/smartphone.png"/>" class="bigger-size-icon"/>
                <span>733-32-32</span>
            </div>
            <p class="light-grey-color smallText">МТС, Velcome, Life:)<br/>(физические лица)</p>
        </div>
        <div class="flex">
            <div class="flex-row">
                <img src="<c:url value="/resources/images/phone.png"/>" class="bigger-size-icon"/>
                <span>7464</span>
            </div>
            <p class="light-grey-color smallText">Мобильный номер<br/>для юридических лиц</p>
        </div>
        <c:if test="${!isAdmin}">
            <button type="button" class="btn btn-outline-light size-btn" data-toggle="modal" data-target="#exampleModalCenter">Заказать звонок</button>
        </c:if>

        <c:if test="${isAdmin}">
            <form action="<c:url value="/calls/all"/>" method="post">
                <button type="submit" id ="43" class="btn btn-outline-light size-btn" >Просмотреть заказы звонков</button>
            </form>
        </c:if>


        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Заказать звонок</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <form action="<c:url value="/calls/get"/>" method="post">
                        <div class="modal-body">

                            <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" required name="topic">
                                <option selected disabled>Тема вопроса</option>
                                <option value="Вопрос по скоринг-анкете">Вопрос по скоринг-анкете</option>
                                <option value="Вопрос по калькулятору кредитов">Вопрос по калькулятору кредитов</option>
                                <option value="Вопрос иного характера">Вопрос иного характера</option>
                            </select>

                            <input type="text"  name="name" class="form-control mb-2 mr-sm-2"  placeholder="ИмяОтчество" required>
                            <input type="text"  name="phone" class="form-control mb-2 mr-sm-2"  placeholder="Телефон" required>
                            <input type="text"  name="email" class="form-control mb-2 mr-sm-2"  placeholder="Почта" required>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
                            <button type="submit" class="btn btn-primary">Заказать звонок</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <hr/>
    <div class="mb-4 smallText padding"> © 1999–2018 ЗАО «Банк»
        Лицензия на осуществление банковской деятельности
        НБРБ № 22 от 22.07.2014.
        г. Минск, ул. Сурганова, 43-47
    </div>
</div>

</body>
</html>
