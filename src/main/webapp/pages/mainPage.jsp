<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<style>
    h1 {
        font-family: 'Libre Baskerville', serif;
        font-size: 40px;
        text-align: center;
        margin-top: 10px;
        margin-bottom: 30px;
        color: black;
        text-shadow: 1px 1px 2px #5D5D5D;
    }

    .table {
        display: table;
        margin: 0 auto;
    }

    .text {
        font-family: 'Libre Baskerville', serif;
        font-size: 25px;
        letter-spacing: 1px;
        transition: .3s linear;
        color: white;
        text-shadow: 1px 1px 2px #5D5D5D;
    }

    .menu ul li {
        padding: 10px;
        padding-left: 35px;
        padding-right: 35px;
    }

    .menu ul > li:hover {
        background-color: #6A9BBA;
    }

    .menu ul ul > li:hover {
        background-color: #69e;
    }

    .menu ul li, .menu ul {
        display: inline-block;
    }

    .menu ul {
        position: relative;
        margin: 0;
        padding: 0;
        background-color: #A3D7F7;
    }

    .menu ul ul {
        display: none;
        position: absolute;
        background-color: #035284;
        margin-top: 10px;
        margin-left: -20px;
    }

    .menu ul a {
        color: #fff;
        text-decoration: none;
    }

    .menu ul ul a {
        color: #fff;
        text-decoration: none;
    }

    .menu li:hover ul {
        display: block;
    }

    .menu li:hover li {
        display: block;
    }

    select {
        text-align: right;
        position: absolute;
        margin: 10px;
        margin-left: 40px;
        width: 70px;
        text-align: center;
    }

    .box {
        font: 28px/1.5 Helvetica, Arial, serif;
        font-family: 'Kaushan Script', cursive;
        font-style: normal;
        color: #052831;
        background-color: #C3E1E9;
        width: 500px;
        border-radius: 20px;
        margin: auto;
        box-shadow: 0 2px 2px 0 #757575, 0 3px 1px -2px #757575, 0 1px 5px 0 #757575;
        padding-left: 10px;
        padding-right: 10px;
        padding-bottom: 10px;
    }

    body {
        background-image: url("https://us.123rf.com/450wm/wikorn/wikorn1509/wikorn150900007/44625735-white-clean-clear-brick-wall-texture.jpg?ver=6");
    }

</style>

<h1><fmt:message key="title"/></h1>
<nav class="menu">
    <div class="table">
        <ul>
            <li><a href="/userPage" class="text"><fmt:message key="menu.myPage"/></a></li>
            <li><a href="/patients" class="text"><fmt:message key="menu.myPatients"/></a></li>
            <li><a href="#" class="text"><fmt:message key="menu.myEvents"/></a>
                <ul>
                    <li><a href="/appointments?type=procedure" class="text"><fmt:message key="menu.myEvents.procedures"/></a></li>
                    <li><a href="/appointments?type=operation" class="text"><fmt:message key="menu.myEvents.operations"/></a></li>
                    <li><a href="/appointments?type=medicine" class="text"><fmt:message key="menu.myEvents.medicines"/></a></li>
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
        <form method="get" style="position: relative;">
            <select name="locale" onchange="this.form.submit()">
                <option value="en" ${locale == 'en' ? 'selected' : ''}>EN</option>
                <option value="ru" ${locale == 'ru' ? 'selected' : ''}>RU</option>
            </select>
        </form>
    </div>
</nav>
