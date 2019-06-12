<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="glassBox">
    <h3>
    <c:if test="${type eq 'medicine'}"><fmt:message key="menu.myEvents.medicines"/></c:if>
    <c:if test="${type eq 'operation'}"><fmt:message key="menu.myEvents.operations"/></c:if>
    <c:if test="${type eq 'procedure'}"><fmt:message key="menu.myEvents.procedures"/></c:if>
        <small><fmt:message key="hint.checkmark"/></small>
    </h3>
    <form method="post">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col" style="flex-basis: 10%"></div>
                <div class="col" style="flex-basis: 30%"><fmt:message key="label.details"/>
                </div>
                <div class="col" style="flex-basis: 30%"><fmt:message key="label.patient"/></div>
                <div class="col" style="flex-basis: 30%"><fmt:message key="label.time"/></div>
            </li>
            <c:forEach items="${schedules}" var="schedule">
                <li class="table-row">
                    <div class="col" style="flex-basis: 10%">
                        <input class="checkbox" type="checkbox" name="appointment" value="${schedule.getScheduleId()}">
                    </div>
                    <div class="col" style="flex-basis: 30%"><c:out value="${schedule.getDetails()}"/></div>
                    <div class="col" style="flex-basis: 30%"><c:out value="${schedule.getPatient()}"/></div>
                    <div class="col" style="flex-basis: 30%"><c:out value="${schedule.getPursuanceTime()}"/></div>
                </li>
            </c:forEach>
        </ul>
        <button class="accept" onclick="this.form.submit()"><fmt:message key="changes.save"/></button>
    </form>
    <form method="get">
        <input type="hidden" name="type" value="${type}">
        <button ${startIdx == '0' ? 'disabled' : ''} name="startIdx" value="${startIdx-10}">
            <img style="-webkit-transform: scaleX(-1);
                transform: scaleX(-1);" height="20px"
                 src="https://png2.kisspng.com/sh/730f02aa7d82e2487ba866fa5fe86dcb/L0KzQYm3U8E5N6t6j5H0aYP2gLBuTfFzepD8RdV4bYD4hLb5Tflkd594RdN7cnB6PbrqjB4ucZ8yft5qdD32hMrzhb1ieqN0j598eX3lf720lBVjNZVqi9twbj24cbLsUMYxPZY5edNuMD66Roa6UMQyPGI6SqM8NUS1RIaCU8Y3NqFzf3==/kisspng-arrow-computer-icons-arrow-icon-in-flat-style-arrow-symbol-web-design-5aae0605e4aae0.7653041415213542459366.png">
        </button>
        <button ${endOfList != null ? 'disabled' : ''} name="startIdx" value="${startIdx+10}">
            <img height="20px"
                 src="https://png2.kisspng.com/sh/730f02aa7d82e2487ba866fa5fe86dcb/L0KzQYm3U8E5N6t6j5H0aYP2gLBuTfFzepD8RdV4bYD4hLb5Tflkd594RdN7cnB6PbrqjB4ucZ8yft5qdD32hMrzhb1ieqN0j598eX3lf720lBVjNZVqi9twbj24cbLsUMYxPZY5edNuMD66Roa6UMQyPGI6SqM8NUS1RIaCU8Y3NqFzf3==/kisspng-arrow-computer-icons-arrow-icon-in-flat-style-arrow-symbol-web-design-5aae0605e4aae0.7653041415213542459366.png">
        </button>
    </form>
</div>
</body>
</html>
