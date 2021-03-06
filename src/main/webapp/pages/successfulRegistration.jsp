<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Successful Registration</title>
</head>
<body>
<div class="glassBox">
    <h3><fmt:message key="registration.success.info"/>!</h3>
    <fmt:message key="registration.success.log.in"/>!
    <form action="/authorisation">
        <button class="accept" onclick="this.form.submit()"><fmt:message key="menu.entrance"/></button>
    </form>
</div>
</body>
</html>
