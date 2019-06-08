<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page = 'mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <form action="" method="post">
    <table>
        <tr>
            <th></th>
            <th>Medicine</th>
            <th>Patient</th>
            <th>Time</th>
        </tr>
    <c:forEach items="${schedules}" var="schedule">
        <tr>
            <th><input type="checkbox" name="appointed" value="${schedule.getScheduleId()}"></th>
            <th><c:out value="${schedule.getDetails()}"/></th>
            <th><c:out value="${schedule.getPatient()}"/></th>
            <th><c:out value="${schedule.getPursuanceTime()}"/></th>
        </tr>
    </c:forEach>
    </table>
    <button onclick="this.form.submit()">Save changes</button>
    </form>
</div>
</body>
</html>
