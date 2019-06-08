<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<jsp:include page='../pages/mainPage.jsp'/>

<html>
<body>
<h1>Page can not be reached!</h1>
<a href="/main">Go to main page</a>
</body>
</html>
