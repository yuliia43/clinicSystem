<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <form method="post">
        <h3>Add patient</h3>
        First Name<input type="text" name="firstName">
        <br>
        Last Name<input type="text" name="lastName">
        <br>
        Sex
        <select name="sex">
            <option value="m">male</option>
            <option value="f">female</option>
        </select>
        <br>
        Birthday Date<input type="date" name="birthday">
        <br>
        <c:if test="${fail != null}">
            <p>You should fill in all fields!</p><br>
        </c:if>

        <button onclick="this.form.submit()">Register</button>
    </form>
</div>
</body>
</html>

