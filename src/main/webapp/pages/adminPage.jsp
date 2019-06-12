<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <style type="text/css">
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<h1><fmt:message key="title"/></h1>
<nav class="menu">
    <div class="table">
        <ul>
            <li><a href="#" class="text"><fmt:message key="menu.patients"/></a>
            <ul>
                <li><a href="/adminShowPatients" class="text"><fmt:message
                        key="patient.show.diagnosis"/></a></li>
                <li><a href="/adminAdd" class="text"><fmt:message
                        key="label.add"/></a></li>
            </ul>
            </li>
            <li><a href="#" class="text"><fmt:message key="menu.doctors"/></a>
                <ul>
                    <li><a href="/adminShowStaff" class="text"><fmt:message
                            key="patient.show.diagnosis"/></a></li>
                </ul>
            </li>
        </ul>
        <form method="get" style="position: fixed; top: 100px; left: 1000px">
            <select name="locale" id="locale" onchange="this.form.submit()">
                <option value="en" ${locale == 'en' ? 'selected' : ''}> &#x1F1EA; &#x1F1F3;</option>
                <option value="ru" ${locale == 'ru' ? 'selected' : ''}> &#x1F1F7; &#x1F1FA;</option>
            </select>
        </form>
    </div>
</nav>
</body>
</html>

