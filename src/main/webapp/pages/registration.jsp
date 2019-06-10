<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<div class="box">
    <form action="registration" method="post">
        <h3><fmt:message key="label.registration"/></h3>
        <fmt:message key="user.name"/><input type="text" name="firstName">
        <br>
        <fmt:message key="user.surname"/><input type="text" name="lastName">
        <br>
        <fmt:message key="user.email"/><input type="email" name="email">
        <br>
        <fmt:message key="user.title"/><input type="radio" name="title" value="doctor">Doctor</input>
        <input type="radio" name="title" value="nurse">Nurse</input>
        <br>
        <fmt:message key="label.password"/><input type="password" name="password">
        <br>
        <fmt:message key="label.password.confirm"/><input type="password" name="confirmedPassword">
        <br>
        <c:if test="${fail != null}">
            <p><fmt:message key="error.fieldsNotFilled"/>!</p><br>
        </c:if>
        <c:if test="${notEqual == true}">
            <p><fmt:message key="error.passwordsNotEqual"/>!</p><br>
        </c:if>

        <button onclick="this.form.submit()"><fmt:message key="registration.submit"/></button>
    </form>
</div>
</body>
</html>
