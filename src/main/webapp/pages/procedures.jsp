<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page = 'mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <table>
        <tr>
            <th>Procedure</th>
            <th>Patient</th>
            <th>Time</th>
        </tr>
    <c:forEach items="${schedules}" var="schedule">
        <tr>
            <th><c:out value="${schedule.getDetails()}"/></th>
            <th><c:out value="${schedule.getPatient()}"/></th>
            <th><c:out value="${schedule.getPursuanceTime()}"/></th>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>
