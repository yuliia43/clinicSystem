<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserPage</title>
</head>
<body>
<fmt:message key="user.surname"/>: <c:out value="${user.getSurname()}"/>
<br>
<fmt:message key="user.name"/>: <c:out value="${user.getName()}"/>
<br>
<fmt:message key="user.title"/>: <c:out value="${user.getTitle()}"/>
<br>
<fmt:message key="user.email"/>: <c:out value="${user.getEmail()}"/>
<br>
</body>
</html>
