<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function showDiv() {
            if (document.getElementById('add').style.display == 'none') {
                document.getElementById('add').style.display = "inline";
            } else {
                document.getElementById('add').style.display = "none";
            }
        }
    </script>
    <style type="text/css">
        <%@include file="../styles/style.css" %>
    </style>
</head>
<body>
<h1><fmt:message key="title"/></h1>
<nav class="menu">
    <div class="table">
        <ul>
            <li><a href="/userPage" class="text"><fmt:message key="menu.myPage"/></a></li>
            <li><a href="/patients" class="text"><fmt:message key="menu.myPatients"/></a></li>
            <li><a href="#" class="text"><fmt:message key="menu.myEvents"/></a>
                <ul>
                    <li><a href="/appointments?type=procedure" class="text"><fmt:message
                            key="menu.myEvents.procedures"/></a></li>
                    <li><a href="/appointments?type=operation" class="text"><fmt:message
                            key="menu.myEvents.operations"/></a></li>
                    <li><a href="/appointments?type=medicine" class="text"><fmt:message
                            key="menu.myEvents.medicines"/></a></li>
                </ul>
            </li>
            <li><a href="#" class="text"><fmt:message key="menu.entrance"/></a>
                <ul>
                    <li><a href="/authorisation" class="text">
                        <div><fmt:message key="menu.entrance.signIn"/></div>
                    </a></li>
                    <li><a href="/registration" class="text">
                        <div><fmt:message key="menu.entrance.signUp"/></div>
                    </a></li>
                </ul>
            </li>
        </ul>
        <form method="get" style="position: fixed; top: 100px; left: 1000px">
            <select name="locale" id="locale" onchange="this.form.submit()">
                <option value="en" ${locale == 'en' ? 'selected' : ''}>&#x1F1EA; &#x1F1F3;</option>
                <option value="ru" ${locale == 'ru' ? 'selected' : ''}> &#x1F1F7; &#x1F1FA;</option>
            </select>
        </form>
    </div>
</nav>
</body>
</html>
