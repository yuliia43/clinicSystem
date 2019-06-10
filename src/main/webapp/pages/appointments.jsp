<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <form action="" method="post">
        <table>
            <tr>
                <th></th>
                <th>
                    <c:if test="${type eq 'medicine'}"><fmt:message key="menu.myEvents.medicines"/></c:if>
                    <c:if test="${type eq 'operation'}"><fmt:message key="menu.myEvents.operations"/></c:if>
                    <c:if test="${type eq 'procedure'}"><fmt:message key="menu.myEvents.procedures"/></c:if>
                </th>
                <th><fmt:message key="label.patient"/></th>
                <th><fmt:message key="label.time"/></th>
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
        <button onclick="this.form.submit()"><fmt:message key="changes.save"/></button>
    </form>
    <form method="get">
        <input type="hidden" name="type" value="${type}">
        <button ${startIdx == '0' ? 'disabled' : ''} name="startIdx" value="${startIdx-10}">prev</button>
        <button ${endOfList != null ? 'disabled' : ''} name="startIdx" value="${startIdx+10}">next</button>
    </form>
</div>
</body>
</html>
