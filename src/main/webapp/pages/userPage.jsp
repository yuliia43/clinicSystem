<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div class="glassBox" style="padding-top: 20px; height: 200px">
    <table>
        <tr>
            <td width="200px">
                <c:if test="${user.getTitle() eq 'doctor'}">
                    <img height="200px"
                         src="https://png2.kisspng.com/sh/56650fddf63223e96ccbd6f24943b4c2/L0KzQYm3V8E0N5x2eZH0aYP2gLBuTgBpgaRue9tqbj3nf8j1jP9ibF5ohNt5LXH1hH7rjBt1d6Mye9N7dHByfn68gsQ6OGoAfaNrZUmzRXAAVcQxPWoAUKMAM0G4QYS5VMYyOWU4RuJ3Zx==/kisspng-physician-download-clip-art-doktor-cartoon-5b49099e1be905.7540599815315132461143.png">
                </c:if>
                <c:if test="${user.getTitle() eq 'nurse'}">
            <img height="250px" style="margin-left: -50px"
                 src="https://png2.kisspng.com/sh/795cc9127c8f1e9112f1deac00092ee4/L0KzQYm3UcI1N6dnfZH0aYP2gLBuTflzaZ9uedC2cILog7rrhf51cZJxRdd1ZXP3ebF1TcIxOWgygNdqbITrPb7shPlkcZ8yTdMBOEKzdoa6V8dnO2kzUac8MkizSYa4VcE3P2g4TqQ6MkK6Q3B3jvc=/kisspng-iranian-presidential-election-2017-health-medicin-5a6820f5377f38.9532809515167736212273.png">
                </c:if>
            </td>
            <td>
                <fmt:message key="user.surname"/>: <c:out value="${user.getSurname()}"/>
                <br>
                <fmt:message key="user.name"/>: <c:out value="${user.getName()}"/>
                <br>
                <fmt:message key="user.email"/>: <c:out value="${user.getEmail()}"/>
            </td>

        </tr>
    </table>
</div>
<br>
</body>
</html>
