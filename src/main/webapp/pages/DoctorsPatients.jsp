<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page = 'mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<body>
<table>
<tr>
<th><fmt:message key="user.surname"/></th>
<th><fmt:message key="user.name"/></th>
<th><fmt:message key="user.sex"/></th>
<th><fmt:message key="user.birthday"/></th>
</tr>
<c:forEach items="${patients}" var="patient">
<tr>
<th><c:out value="${patient.getSurname()}"/></th>
<th><c:out value="${patient.getName()}"/></th>
<th><c:out value="${patient.getSex()}"/></th>
<th><c:out value="${patient.getBirthday_date()}"/></th>
</tr>
</c:forEach>
</table>
</body>
</html>