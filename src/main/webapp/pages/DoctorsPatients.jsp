<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
</head>
<body>
<div class="box">
    <h3>My patients</h3>
<table>
    <tr>
        <th><fmt:message key="user.surname"/></th>
        <th><fmt:message key="user.name"/></th>
        <th><fmt:message key="user.sex"/></th>
        <th><fmt:message key="user.birthday"/></th>
        <th><fmt:message key="patient.diagnoses"/></th>
    </tr>
    <form method="get" action="/diagnoses">
        <c:forEach items="${patients}" var="patient">
        <tr>
            <th><c:out value="${patient.getSurname()}"/></th>
            <th><c:out value="${patient.getName()}"/></th>
            <th><c:out value="${patient.getSex()}"/></th>
            <th><c:out value="${patient.getBirthday_date()}"/></th>
            <th>
                <button name="patientId" value="${patient.getId()}" scope="request"><fmt:message
                        key="patient.show.diagnosis"/>
                </button>
            </th>
        </tr>
        </c:forEach>
</table>
</div>
</body>
</html>
