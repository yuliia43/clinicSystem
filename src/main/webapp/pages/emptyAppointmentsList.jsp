<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<jsp:include page='mainPage.jsp'/>
<html>
<head>
    <meta charset="UTF-8">
    <title>NoAppointments</title>
</head>
<body>
<h1><fmt:message key="patient.appointments.list.empty"/>!</h1>
</body>
</html>
