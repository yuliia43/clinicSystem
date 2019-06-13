<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<div class="glassBox">
    <form action="registration" method="post">
        <h3><fmt:message key="label.registration"/></h3>
        <fmt:message key="user.name"/>:<input type="text" name="firstName">
        <br>
        <fmt:message key="user.surname"/>:<input type="text" name="lastName">
        <br>
        <fmt:message key="user.email"/>:<input type="email" name="email">
        <br>
        <fmt:message key="user.title"/>:
        <span style="float: right; margin-right: 100px"><input type="radio" class="radio" name="title" value="doctor">Doctor
            <input type="radio" class="radio" name="title" value="nurse">Nurse</span>
        <br>
        <fmt:message key="label.password"/>:<input type="password" name="password">
        <br>
        <fmt:message key="label.password.confirm"/>:
        <input type="password" name="confirmedPassword" style="margin-right: 0px; float: unset">
        <br>
        <c:if test="${fail != null}">
            <p class="error"><fmt:message key="error.fieldsNotFilled"/>!</p><br>
        </c:if>
        <c:if test="${notEqual == true}">
            <p class="error"><fmt:message key="error.passwordsNotEqual"/>!</p><br>
        </c:if>

        <button class="accept" onclick="this.form.submit()"><fmt:message key="registration.submit"/></button>
    </form>
</div>
</body>
</html>
