<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page='adminPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div class="glassBox">
    <h3><fmt:message key="menu.patients"/></h3>
    <c:if test="${patients.size() == 0}">
        <p align="center">
            <fmt:message key="patient.list.empty"/>!
        </p>
    </c:if>
    <c:if test="${patients.size() != 0}">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col" style="flex-basis: 20%"><fmt:message key="user.surname"/></div>
                <div class="col" style="flex-basis: 20%"><fmt:message key="user.name"/></div>
                <div class="col" style="flex-basis: 15%"><fmt:message key="user.sex"/></div>
                <div class="col" style="flex-basis: 35%"><fmt:message key="user.birthday"/></div>
            </li>
            <form method="get" action="/diagnoses">
                <c:forEach items="${patients}" var="patient">
                <li class="table-row">
                    <div class="col" style="flex-basis: 20%"><c:out value="${patient.getSurname()}"/></div>
                    <div class="col" style="flex-basis: 20%"><c:out value="${patient.getName()}"/></div>
                    <div class="col" style="flex-basis: 15%">
                        <c:if test="${patient.getSex()=='m'.charAt(0)}">
                            <img height="20px"
                                 src="https://png2.kisspng.com/sh/d221684860fecb4dce0cd30c9758cc40/L0KzQYm3VcE2N6h9iZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TgN6dZN0hJ8AYXblRYeBV8Jia5Q3SpCCMEW8Rom7VME2Omc5SqM6Mke0R4a6TwBvbz==/kisspng-computer-icons-symbol-5afb56872acc22.7059684415264211271753.png">
                        </c:if>
                        <c:if test="${patient.getSex()=='f'.charAt(0)}">
                            <img height="20px"
                                 src="https://png2.kisspng.com/sh/abe9e124adc2e44a6295447ec3a4e667/L0KzQYm3U8MxN6NqfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfZmdZJxfZ9yY3BxPbXskBlodl58h99qbj3pdb7ojPUuPZJnfdUDZEbkSbaAgckvOGU8S6MCOUK0RYO5VMUzPmo5TqsAOT7zfri=/kisspng-computer-icons-female-icon-design-woman-female-5abec8d6a9e7a9.0473179215224526946959.png">
                        </c:if>
                    </div>
                    <div class="col" style="flex-basis: 35%"><c:out value="${patient.getBirthday_date()}"/></div>
                </li>
                </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
