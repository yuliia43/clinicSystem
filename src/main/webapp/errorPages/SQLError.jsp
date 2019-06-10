<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<jsp:include page='../pages/mainPage.jsp'/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<p><fmt:message key="error.sql"/></p>
</body>
</html>
