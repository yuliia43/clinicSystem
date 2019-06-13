<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='adminPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Patient</title>
</head>
<body>
<div class="glassBox">
    <form method="post">
        <h3><fmt:message key="registration.label.patient"/></h3>
        <fmt:message key="user.name"/><input type="text" name="firstName">
        <br>
        <fmt:message key="user.surname"/><input type="text" name="lastName">
        <br>
        <fmt:message key="user.sex"/>
            <input type="radio" style="margin-left: 30px; margin-right: 0px" class="radio" name="sex" value="m">
        <img height="20px"
             src="https://png2.kisspng.com/sh/d221684860fecb4dce0cd30c9758cc40/L0KzQYm3VcE2N6h9iZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TgN6dZN0hJ8AYXblRYeBV8Jia5Q3SpCCMEW8Rom7VME2Omc5SqM6Mke0R4a6TwBvbz==/kisspng-computer-icons-symbol-5afb56872acc22.7059684415264211271753.png">
        </input>
            <input type="radio" style="margin-left: 20px; margin-right: 0px" class="radio" name="sex"  value="f">
        <img height="20px"
             src="https://png2.kisspng.com/sh/abe9e124adc2e44a6295447ec3a4e667/L0KzQYm3U8MxN6NqfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfZmdZJxfZ9yY3BxPbXskBlodl58h99qbj3pdb7ojPUuPZJnfdUDZEbkSbaAgckvOGU8S6MCOUK0RYO5VMUzPmo5TqsAOT7zfri=/kisspng-computer-icons-female-icon-design-woman-female-5abec8d6a9e7a9.0473179215224526946959.png">
        </input>
        <br>
        <fmt:message key="user.birthday"/><input type="date" name="birthday">
        <br>
        <c:if test="${fail != null}">
            <p><fmt:message key="error.fieldsNotFilled"/>!</p><br>
        </c:if>

        <button class="accept" onclick="this.form.submit()"><fmt:message key="registration.submit"/></button>
    </form>
</div>
</body>
</html>
