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
            border-radius: 10px;
            position: relative;
            margin: 0;
            padding: 0;
            background: rgb(185, 205, 223);
            box-shadow: 0 2px 2px 0 #757575, 0 3px 1px -2px #757575, 0 1px 5px 0 #757575;
        }

        .menu ul ul {
            display: none;
            position: absolute;
            background-color: #035284;
            margin-top: 10px;
            margin-left: -20px;
            z-index: 999;
        }

        .menu ul a {
            color: rgb(242, 242, 242);
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

        .glassBox {
            font: 28px/1.5 Helvetica, Arial, serif;
            font-family: 'Kaushan Script', cursive;
            font-style: normal;
            color: #052831;
            background: rgba(204, 242, 255, 0.4);
            width: 500px;
            border-radius: 20px;
            margin: auto;
            margin-top: 30px;
            box-shadow: 0 2px 2px 0 #757575, 0 3px 1px -2px #757575, 0 1px 5px 0 #757575;
            padding-left: 30px;
            padding-right: 30px;
            padding-bottom: 70px;
        }

        .box {
            font: 28px/1.5 Helvetica, Arial, serif;
            font-family: 'Kaushan Script', cursive;
            font-style: normal;
            color: #052831;
            background: rgb(220, 230, 239);
            width: 500px;
            border-radius: 20px;
            margin: auto;
            margin-top: 20px;
            box-shadow: 0 2px 2px 0 #757575, 0 3px 1px -2px #757575, 0 1px 5px 0 #757575;
            padding-left: 20px;
            padding-right: 20px;
            padding-bottom: 70px;
            text-align: left;
        }

        .box input {
            float: right;
            margin-top: 7px;
            margin-right: 15px;
            width: 150px;
        }

        .accept {
            margin: 25px 20px 0px;
            position: relative;
            display: inline-block;
            color: #777674;
            font-weight: bold;
            text-decoration: none;
            text-shadow: rgba(255, 255, 255, .5) 1px 1px, rgba(100, 100, 100, .3) 3px 7px 3px;
            user-select: none;
            padding: 7px 1em;
            outline: none;
            border-radius: 3px / 100%;
            background-image: linear-gradient(45deg, rgba(255, 255, 255, .0) 30%, rgba(255, 255, 255, .8), rgba(255, 255, 255, .0) 70%),
            linear-gradient(to right, rgba(255, 255, 255, 1), rgba(255, 255, 255, 0) 20%, rgba(255, 255, 255, 0) 90%, rgba(255, 255, 255, .3)),
            linear-gradient(to right, rgba(125, 125, 125, 1), rgba(255, 255, 255, .9) 45%, rgba(125, 125, 125, .5)),
            linear-gradient(to right, rgba(125, 125, 125, 1), rgba(255, 255, 255, .9) 45%, rgba(125, 125, 125, .5)),
            linear-gradient(to right, rgba(223, 190, 170, 1), rgba(255, 255, 255, .9) 45%, rgba(223, 190, 170, .5)),
            linear-gradient(to right, rgba(223, 190, 170, 1), rgba(255, 255, 255, .9) 45%, rgba(223, 190, 170, .5));
            background-repeat: no-repeat;
            background-size: 200% 100%, auto, 100% 2px, 100% 2px, 100% 1px, 100% 1px;
            background-position: 200% 0, 0 0, 0 0, 0 100%, 0 4px, 0 calc(100% - 4px);
            box-shadow: rgba(0, 0, 0, .5) 3px 10px 10px -10px;
            float: right;
            cursor: pointer;
        }
        .accept:disabled{
            cursor: none;
            background-image: none;
            text-shadow: none;
            box-shadow: none;
            color: darkgray;
        }

        .transparentButton {
            margin: 0px;
            float: unset;
            background-color: Transparent;
            border: none;
            cursor: pointer;
            overflow: hidden;
            outline: none;
            background-image: none;
            box-shadow: transparent;
            text-shadow: transparent;
        }

        input {
            float: right;
            margin-right: 70px;
            margin-top: 12px;
        }

        input.checkbox{
            float: unset;
            margin-right: 0px;
            margin-top: 0px;
        }

        input.radio {
            margin: 0px;
            margin-right: 20px;
            float: unset;
        }

        .time{
            float: right;
            margin-left: 50px;
            width: 70px;
        }
        .time input{
            width: 70px;
        }

        .addTime{
            float: right;
            margin-right: 100px;
            margin-top: 3px;
        }

        select {
            overflow-scrolling: auto;
        }

        body {
            background-image: url("http://dcfixru.ru/images/site/catalog/hoelzer/200-5393.jpg");
        }

        .error {
            color: red;
            font-size: 15px;
            display: inline;
        }

        .responsive-table {
            margin: 0px;
        }

        .responsive-table li {
            border-radius: 3px;
            padding: 10px 25px;
            display: flex;
            justify-content: space-between;
            margin-bottom: 7px;
        }

        .table-header {
            background-color: lightsteelblue;
            font-size: 12px;
            text-transform: uppercase;
            letter-spacing: 0.03em;
            text-align: center;
        }
        .table-header button{
            margin: 0px 5px 0px;
        }

        .table-row {
            background-color: #ffffff;
            box-shadow: 0px 0px 9px 0px rgba(0, 0, 0, 0.1);
            font-size: 13px;
            text-align: center;
        }

        .table-row button{
            font-size: 13px;
            width: content-box;
            padding: 5px 7px;
            margin: 7px 5px 0px;
        }

        @media all and (max-width: 767px) {
            .table-header {
                display: none;
            }

            .table-row {

            }

            li {
                display: block;
            }

            .col {

                flex-basis: 100%;

            }

            .col {
                display: flex;
                padding: 10px 0;
            }

        }

        small {
            font-size: 0.3em;
        }


        select#name option[value="ru"] {
            background-image: url(https://png2.kisspng.com/sh/addd04e28c16a7bad574459ac43b4a37/L0KzQYm3WMAzN6F9gJH0aYP2gLBuTfZtaZgyh9g2coX2g7roTfZtaZgyh9g2c3zyhrb1ifEudpJ5gdH3YXywdr3oh71laaoygug2YX3zPcL8jCQua5J3etH3LYDohMP2jPV2dV5mheI2cYXyhH73mgJwdKp4gZ8AYka1doOCWPQ0aZQ8UJC7OEC0QYGBWcE2O2Q3SaM7OEi7Roi1kP5o/kisspng-flag-of-russia-flag-of-slovenia-national-flag-day-jv-amp-quot-carbon-petroleum-amp-quot-pyrolysi-5b62f298d3ac78.280110891533211288867.png);
        }

        select#name option[value="en"] {
            background-image: url(https://png2.kisspng.com/sh/addd04e28c16a7bad574459ac43b4a37/L0KzQYm3WMAzN6F9gJH0aYP2gLBuTfZtaZgyh9g2coX2g7roTfZtaZgyh9g2c3zyhrb1ifEudpJ5gdH3YXywdr3oh71laaoygug2YX3zPcL8jCQua5J3etH3LYDohMP2jPV2dV5mheI2cYXyhH73mgJwdKp4gZ8AYka1doOCWPQ0aZQ8UJC7OEC0QYGBWcE2O2Q3SaM7OEi7Roi1kP5o/kisspng-flag-of-russia-flag-of-slovenia-national-flag-day-jv-amp-quot-carbon-petroleum-amp-quot-pyrolysi-5b62f298d3ac78.280110891533211288867.png);
        }


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
