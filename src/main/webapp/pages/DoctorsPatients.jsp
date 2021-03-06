<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div class="glassBox">
    <h3><fmt:message key="menu.myPatients"/>
        <button class="transparentButton" onclick="showDiv();">
            <img height="25px"
                 src="https://png2.kisspng.com/sh/978cdd494fa133b275ec734d633b04f5/L0KzQYm3U8E6N6F9j5H0aYP2gLBuTfNwdaF6jNd7LXnmf7B6Tflkd58yfNd8aXfxPbXskBt1d6Eyj9N1bIDkgLb5Tflkd58yedZtLXT1ccjwjvcuPZJnSKcAYUizQIbphsgvO2c4TKcEM0K0RYO4VcA2P2E5SKI6NT7zfri=/kisspng-computer-icons-icon-design-desktop-wallpaper-icon-add-drawing-5ab055a8005bf8.3634593215215057040015.png">
        </button>
    </h3>
    <form method="post">
        <div class="box"
             style="display: ${openedMenu ? 'inline' : 'none'}; width: 300px; height: 80px; position: absolute; top: 200px; z-index: 999; font-size: 20px; "
             id="add">
            <fmt:message key="label.patient"/>:
            <select name="patientId" style="float: left; width: 147px">
                <c:forEach items="${allPatients}" var="patient">
                    <option value="${patient.getId()}">${patient.getSurname()} ${patient.getName()}</option>
                </c:forEach>
            </select>
            <br>
            <fmt:message key="label.diagnosis"/>: <input name="diagnosis" type="text"/>
            <br>
            <c:if test="${fail}">
                <p class="error"><fmt:message key="error.fieldsNotFilled"/>!</p>
            </c:if>
            <input name="method" value="add_diagnosis" type="hidden"/>
            <button class="accept" onclick="this.form.submit()"><fmt:message key="registration.label.patient"/></button>
        </div>
    </form>
    <c:if test="${doctorsPatients.size() == 0}">
        <p align="center">
            <fmt:message key="patient.list.empty"/>!
        </p>
    </c:if>
    <c:if test="${doctorsPatients.size() != 0}">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col" style="flex-basis: 15%"><fmt:message key="user.surname"/></div>
                <div class="col" style="flex-basis: 15%"><fmt:message key="user.name"/></div>
                <div class="col" style="flex-basis: 10%"><fmt:message key="user.sex"/></div>
                <div class="col" style="flex-basis: 30%"><fmt:message key="user.birthday"/></div>
                <div class="col" style="flex-basis: 20%"><fmt:message key="patient.diagnoses"/></div>
            </li>
            <form method="get" action="/diagnoses">
                <c:forEach items="${doctorsPatients}" var="patient">
                <li class="table-row">
                    <div class="col" style="flex-basis: 15%"><c:out value="${patient.getSurname()}"/></div>
                    <div class="col" style="flex-basis: 15%"><c:out value="${patient.getName()}"/></div>
                    <div class="col" style="flex-basis: 10%">
                        <c:if test="${patient.getSex()=='m'.charAt(0)}">
                            <img height="20px"
                                 src="https://png2.kisspng.com/sh/d221684860fecb4dce0cd30c9758cc40/L0KzQYm3VcE2N6h9iZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TgN6dZN0hJ8AYXblRYeBV8Jia5Q3SpCCMEW8Rom7VME2Omc5SqM6Mke0R4a6TwBvbz==/kisspng-computer-icons-symbol-5afb56872acc22.7059684415264211271753.png">
                        </c:if>
                        <c:if test="${patient.getSex()=='f'.charAt(0)}">
                            <img height="20px"
                                 src="https://png2.kisspng.com/sh/abe9e124adc2e44a6295447ec3a4e667/L0KzQYm3U8MxN6NqfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfZmdZJxfZ9yY3BxPbXskBlodl58h99qbj3pdb7ojPUuPZJnfdUDZEbkSbaAgckvOGU8S6MCOUK0RYO5VMUzPmo5TqsAOT7zfri=/kisspng-computer-icons-female-icon-design-woman-female-5abec8d6a9e7a9.0473179215224526946959.png">
                        </c:if>
                    </div>
                    <div class="col" style="flex-basis: 30%"><c:out value="${patient.getBirthday_date()}"/></div>
                    <div class="col" style="flex-basis: 20%">
                        <button class="accept" style=" margin: 0px" name="patientId"
                                value="${patient.getId()}" scope="request"><fmt:message
                                key="patient.show.diagnosis"/>
                        </button>
                    </div>
                </li>
                </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
