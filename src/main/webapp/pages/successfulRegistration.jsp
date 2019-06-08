<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <h3>Registration success!</h3>
    Now you can log in!
    <form action="/authorisation">
        <button onclick="this.form.submit()">Entrance</button>
    </form>
</div>
</body>
</html>
