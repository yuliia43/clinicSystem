<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <form method="post">
        <h3><fmt:message key="registration.label.patient"/></h3>
        <fmt:message key="user.name"/><input type="text" name="firstName">
        <br>
        <fmt:message key="user.surname"/><input type="text" name="lastName">
        <br>
        <fmt:message key="user.sex"/>
        <select name="sex">
            <option value="m">male</option>
            <option value="f">female</option>
        </select>
        <br>
        <fmt:message key="user.birthday"/><input type="date" name="birthday">
        <br>
        <c:if test="${fail != null}">
            <p><fmt:message key="error.fieldsNotFilled"/>!</p><br>
        </c:if>

        <button onclick="this.form.submit()"><fmt:message key="registration.submit"/></button>
    </form>
</div>
</body>
</html>

