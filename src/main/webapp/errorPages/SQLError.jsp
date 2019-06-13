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
<div class="glassBox" style="padding: 100px 50px 100px">
    <h1><fmt:message key="error.sql"/>!</h1>
</div>
</body>
</html>
