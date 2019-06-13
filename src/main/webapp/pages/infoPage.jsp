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
    <h1><fmt:message key="info.title"/></h1>
    <hr>
    <p style="font-size: 20px"><fmt:message key="info.about"/></p>
    <form action="/registration">
        <button class="accept" onclick="this.form.submit()"><fmt:message key="label.registration"/></button>
    </form>
</div>
</body>
</html>
