<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<jsp:include page='../pages/mainPage.jsp'/>

<html>
<head>
    <meta charset="utf-8">
    <title>Error</title>
</head>
<body>
<div class="glassBox" style="padding: 100px 50px 70px">
    <h1><fmt:message key="discharge.success"/>!</h1>
    <br>
    <a href="/patients" style="float: left"><fmt:message key="button.return.toPatients"/></a>
    <a href="/" style="float: right"><fmt:message key="button.return.toMain"/></a>
</div>
</body>
</html>
