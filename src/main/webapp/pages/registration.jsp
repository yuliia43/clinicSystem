<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
    <form action="registration" method="post">
        <h3>Registration</h3>
        First Name<input type="text" name="firstName">
        <br>
        Last Name<input type="text" name="lastName">
        <br>
        E-mail<input type="email" name="email">
        <br>
        Title<input type="radio" name="title" value="doctor">Doctor</input>
        <input type="radio" name="title" value="nurse">Nurse</input>
        <br>
        Password<input type="password" name="password">
        <br>
        Confirm password<input type="password" name="confirmedPassword">
        <br>
        <c:if test="${fail != null}">
            <p>You should fill in all fields!</p><br>
        </c:if>
        <c:if test="${notEqual == true}">
            <p>Passwords are not equal!</p><br>
        </c:if>

        <button onclick="this.form.submit()">Register</button>
    </form>
</div>
</body>
</html>
