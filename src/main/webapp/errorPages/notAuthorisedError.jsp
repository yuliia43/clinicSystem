<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<jsp:include page='../pages/mainPage.jsp'/>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div class="glassBox" style="padding: 100px 50px 70px">
    <h1><fmt:message key="error.notAuthorised"/>!</h1>
    <br>
    <a href="/authorisation" style="float: right"><fmt:message key="authorisation.check"/></a>
</div>
</body>
</html>
