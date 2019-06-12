<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<head>
    <style>
        th, td {
            border-bottom: 1px solid #ddd;
        }
    </style>
    <script>
        function openOneAddBox(row) {
            var boxes = document.getElementsByName('add_appointment');
            if (boxes.item(row).style.display == 'none') {
                var inputList = Array.prototype.slice.call(boxes);
                inputList.forEach(closeItem);
                document.getElementsByName('add_appointment').item(row).style.display = 'inline';
            } else {
                document.getElementsByName('add_appointment').item(row).style.display = 'none';
            }
        }

        function closeItem(value, index, ar) {
            document.getElementsByName('add_appointment').item(index).style.display = 'none';
        }

        function openOne(row) {
            if (document.getElementsByName('show_appointment').item(row).style.display == 'none') {
                document.getElementsByName('show_appointment').item(row).style.display = 'inline';
            } else {
                document.getElementsByName('show_appointment').item(row).style.display = 'none';
            }
        }
        function addTime(idx) {
            var time = document.createElement("input");
            time.type = "time";
            time.name = "time";
            var newline = document.createElement("br");
            var element = document.getElementsByName("timeHolder").item(idx);
            element.appendChild(time);
            element.appendChild(newline);
        }
    </script>
</head>
<body>
<div class="glassBox">
    <a href="/patients">
        <img style="position: absolute; padding-top: 15px; padding-left: 5px" height="30px"
             src="https://png2.kisspng.com/sh/6e5588ec068618cd4c06c88aaed56456/L0KzQYm3V8AyN5ZniJH0aYP2gLBuTfFzepD8RdV4bYD4hLb5Tflkd594ReVEbXLyfH7qjPlxNZJ3jJ9wbz3lcbTyTcVjO2o1SNUCOUa3dbSCTsM6PWM7T6gCMUW2QIW9UsQxP2c2Tak3cH7q/kisspng-arrow-computer-icons-symbol-clip-art-go-back-5b3900c7964ec9.3952676715304624076157.png">
    </a>
    <form method="post">
        <input type="hidden" name="method" value="discharge">
        <button class="transparentButton" style="position: relative; float: right; padding-top: 15px" name="patientId"
                value="${patient.getId()}">
            <img height="30px"
                 src="https://png2.kisspng.com/sh/17b12ca887ef84713255a3a3b494499e/L0KzQYm3VMA1N6hofZH0aYP2gLBuTgN1aZdrRdHvLXjogr7sk710dpJwfZ92ZXTsc7r1hb10gZ5nh942Y4LoccXwlvl1gV46edU9Ykm4SYbtWPZnQF87UaQEOEi4QYK8UsI5PGIATKc8OUG3PsH1h5==/kisspng-staff-of-hermes-snake-medicine-symbol-creativity-5ac4b9595f8ff8.6929885115228419453914.png">
        </button>
    </form>
    <h3 align="center">
        <c:out value="${patient.getName()} ${patient.getSurname()}"/>
        <c:if test="${patient.getSex()=='m'.charAt(0)}">
            <img height="25px"
                 src="https://png2.kisspng.com/sh/d221684860fecb4dce0cd30c9758cc40/L0KzQYm3VcE2N6h9iZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TgN6dZN0hJ8AYXblRYeBV8Jia5Q3SpCCMEW8Rom7VME2Omc5SqM6Mke0R4a6TwBvbz==/kisspng-computer-icons-symbol-5afb56872acc22.7059684415264211271753.png">
        </c:if>
        <c:if test="${patient.getSex()=='f'.charAt(0)}">
            <img height="25px"
                 src="https://png2.kisspng.com/sh/abe9e124adc2e44a6295447ec3a4e667/L0KzQYm3U8MxN6NqfZH0aYP2gLBuTfNwdaF6jNd7LXnmf7B6TfZmdZJxfZ9yY3BxPbXskBlodl58h99qbj3pdb7ojPUuPZJnfdUDZEbkSbaAgckvOGU8S6MCOUK0RYO5VMUzPmo5TqsAOT7zfri=/kisspng-computer-icons-female-icon-design-woman-female-5abec8d6a9e7a9.0473179215224526946959.png">
        </c:if></h3>
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col" style="width: 5%;  padding-top: 0px">
                <button class="transparentButton" onclick="showDiv()"><img
                        style="float: left" height="15px"
                        src="https://png2.kisspng.com/sh/12c109b3b2f84df88449e16d76b8f4c6/L0KzQYm3VMAyN6h7fZH0aYP2gLBuTfFlbJp5gdH3LXPyfcH8lPVzNZpoh9D8LXPvecG0ggJ1NZJpfJ99bz3mccP7TfJ2fKV0hp8AYXO0SLK4hfJkP2NrTZC7OEK7SIaCVME2OmM7S6U7NEa6R4KCTwBvbz==/kisspng-addition-computer-icons-clip-art-add-to-cart-button-5ac18a1ebc72f5.2828859415226332467719.png">
                </button>
            </div>
            <div class="col" style="flex-basis: 30%; padding-left: 0px">
                <fmt:message key="label.diagnosis"/>
            </div>
            <form method="post">
                <div class="box" id="add"
                     style="display:${openedDiaMenu ? 'inline' : 'none'}; width: auto; position: absolute; z-index: 999; font-size: 20px; height: 45px; padding-top: 10px">
                    <fmt:message key="label.diagnosis"/>: <input name="diagnosis" type="text"/>
                    <br>
                    <input name="method" value="add_diagnosis" type="hidden"/>
                    <c:if test="${diaFail}">
                        <p class="error"><fmt:message key="error.fieldsNotFilled"/>!</p>
                    </c:if>
                    <br>
                    <button class="accept" name="patientId" value="${patient.getId()}" onclick="this.form.submit()">
                        <fmt:message key="label.add"/>
                    </button>
                </div>
            </form>
            <div class="col" style="flex-basis: 20%"><fmt:message key="set.date"/></div>
            <div class="col" style="flex-basis: 44%"><fmt:message key="button.appointment"/></div>
        </li>
        <c:forEach items="${diagnoses}" var="diagnosis" varStatus="loop">
            <li class="table-row" style="background-color: white">
                <div class="col" style="flex-basis: 36%"><c:out value="${diagnosis.getDiagnosis()}"/></div>
                <div class="col" style="flex-basis: 20%"><c:out value="${diagnosis.getSetDate()}"/></div>
                <div class="col" style="flex-basis: 22%">
                    <button ${diagnosis.getRecommendations().size() == 0 ? 'disabled' : ''}
                            class="accept" style="padding: 5px"
                            onclick="openOne(${loop.index})">
                        <fmt:message key="button.showAppointment"/>
                    </button>
                </div>
                <div class="col" style="flex-basis: 22%">
                    <button class="accept" style="padding: 5px"
                            onclick="openOneAddBox(${loop.index})">
                        <fmt:message key="label.add"/></button>
                    <div class="box" name="add_appointment" align="left"
                         style="display: ${openedMenu == loop.index ? 'inline' : 'none'};
                                 width: auto; position: absolute; z-index: 999; font-size: 20px;
                                 right: 200px;">
                        <form method="get" style="display: inline;">
                            <input type="hidden" value="${patient.getId()}" name="patientId">
                            <input type="hidden" value="${loop.index}" name="openedMenu">
                            <fmt:message key="label.type"/>:
                            <select name="type" style="width: 150px; display: inline"
                                    onchange="this.form.submit()">
                                <option value="operation" ${type == 'operation' ? 'selected' : ''}>
                                    <img height="30px"
                                         src="https://png2.kisspng.com/sh/51638d4719fcd5822c9f904ba666279a/L0KzQYm3VMIzN5ptfZH0aYP2gLBuTgNkaZ11fd42c4X1d7b5mb1kd551jeZucj3sc7F1k710faNsfdH3LXLvcbXsTgZma6V0ip8AYXTmQrXoVvU0bWY3UJCCN0O2RoS3VME2OmU4T6s5NEa8Q4S8TwBvbz==/kisspng-scalpel-surgery-computer-icons-surgeon-blade-vector-5adc2da6e3e528.7733630415243790469335.png">
                                    <fmt:message key="menu.myEvents.operations"/>
                                </option>
                                <option value="procedure" ${type == 'procedure' ? 'selected' : ''}>
                                    <img height="30px"
                                         src="https://png2.kisspng.com/sh/2322c4fc9ca88f39833cafb4515423b0/L0KzQYm3U8I1N6V5iZH0aYP2gLBuTflvcpZojNt4bj3mf773lgRmel5ue9H3cz3zeLL5jfFkbaZ5gdVqbD3ngsbuTfh6eJDpReVud3nxd371hfVldJYyTdNrNkPpc4O5WfM1O2czUKQDOUe8QIe4VcIyQGo4S6M9MUe0QXB3jvc=/kisspng-injection-computer-icons-pharmaceutical-drug-hypod-sewing-needle-5ab63fc229c436.8289790615218933141711.png">
                                    <fmt:message key="menu.myEvents.procedures"/>
                                </option>
                                <option value="medicine" ${type == 'medicine' ? 'selected' : ''}>
                                    <fmt:message key="menu.myEvents.medicines"/>
                                </option>
                            </select>
                        </form>
                        <form method="post" style="display: inline;">
                            <table style="text-align: left" border="0px">
                                <tr>
                                    <th>
                                        <fmt:message key="label.details"/>: <input name="details" type="text"/></th>
                                </tr>
                                <tr>
                                    <th>
                                        <fmt:message key="label.days"/>: <input name="num_days" type="number" min="1"/>
                                    </th>
                                </tr>
                                <tr>
                                    <th>
                                        <fmt:message key="label.time"/>:
                                        <button type="button" class="transparentButton & addTime" onclick="addTime(${loop.index})">
                                            <img class="addTime" height="15px"
                                                 src="https://png2.kisspng.com/sh/0a5ee08fd40f309cc81c62ff73edbd8a/L0KzQYm3U8E2N5poj5H0aYP2gLBuTgBtfaQyedDtLX3sfsb6TgNqb594RdV4bYD4hLb5Tflkd594RdV1aYCwccP7TgBtfaQyi9twbj24cbLohMg2P2Q7SdVrMj68RomAWMMzOWI6SqM6NEW8RIS5UsE4NqFzf3==/kisspng-plus-and-minus-signs-computer-icons-clip-art-plus-sign-5aaad857361cb2.9687832115211459432217.png">
                                        </button>
                                        <div name="timeHolder" class="time">
                                            <input name="time" type="time"/>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <th>
                                        <fmt:message key="label.performer"/>:
                                        <select name="performerId" style="float: left; width: 150px">
                                            <c:if test="${type eq 'operation'}">
                                                <c:forEach items="${doctors}" var="doctor">
                                                    <option value="${doctor.getId()}">${doctor.getSurname()} ${doctor.getName()}</option>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${type ne 'operation'}">
                                                <c:forEach items="${staff}" var="doctor">
                                                    <option value="${doctor.getId()}">${doctor.getSurname()} ${doctor.getName()}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </th>
                                </tr>
                                <tr>
                                    <th height="80px">
                                        <input name="type" value="${type}" type="hidden">
                                        <input name="method" value="add_appointed" type="hidden"/>
                                        <button class="accept" name="diagnosisId" value="${diagnosis.getId()}"
                                                onclick="this.form.submit()">
                                            <fmt:message key="label.add"/>
                                        </button>
                                    </th>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </li>
            <div style="display: none;" name="show_appointment">
                <ul class="responsive-table">
                    <li class="table-header" style="background-color: lightcyan; ">
                        <div class="col" style="flex-basis: 10%"><fmt:message key="label.type"/></div>
                        <div class="col" style="flex-basis: 90%"><fmt:message key="label.details"/></div>
                    </li>
                    <c:set var="recommendations" scope="page" value="${diagnosis.getRecommendations()}"/>
                    <c:forEach items="${recommendations}" var="recommendation">
                        <li class="table-header" style="background-color:ghostwhite; padding-bottom: 0px">
                            <div class="col" style="flex-basis: 10%">
                                <c:if test="${recommendation.getType() eq 'operation'}">
                                    <img height="20px"
                                         src="https://png2.kisspng.com/sh/51638d4719fcd5822c9f904ba666279a/L0KzQYm3VMIzN5ptfZH0aYP2gLBuTgNkaZ11fd42c4X1d7b5mb1kd551jeZucj3sc7F1k710faNsfdH3LXLvcbXsTgZma6V0ip8AYXTmQrXoVvU0bWY3UJCCN0O2RoS3VME2OmU4T6s5NEa8Q4S8TwBvbz==/kisspng-scalpel-surgery-computer-icons-surgeon-blade-vector-5adc2da6e3e528.7733630415243790469335.png">
                                </c:if>
                                <c:if test="${recommendation.getType() eq 'procedure'}">
                                    <img height="20px"
                                         src="https://png2.kisspng.com/sh/2322c4fc9ca88f39833cafb4515423b0/L0KzQYm3U8I1N6V5iZH0aYP2gLBuTflvcpZojNt4bj3mf773lgRmel5ue9H3cz3zeLL5jfFkbaZ5gdVqbD3ngsbuTfh6eJDpReVud3nxd371hfVldJYyTdNrNkPpc4O5WfM1O2czUKQDOUe8QIe4VcIyQGo4S6M9MUe0QXB3jvc=/kisspng-injection-computer-icons-pharmaceutical-drug-hypod-sewing-needle-5ab63fc229c436.8289790615218933141711.png">
                                </c:if>
                                <c:if test="${recommendation.getType() eq 'medicine'}">
                                    <img height="20px"
                                         src="https://png2.kisspng.com/sh/29edaf893cdbca8875bf7d4d634e73c8/L0KzQYm3UsI0N5NAiZH0aYP2gLBuTgBpgaRue9tqbj3wdbXwgBlvbV5ue9H3LXPkgMT8jPUubJDojNH7LXnmf7A0VfE6OGI2etM7YUfmSIm1UMY5OWY2SKg6NUG8Q4q4UcYzOWg5SZD5bne=/kisspng-physician-medicine-icon-capsule-doctor-icon-5a9011ba2a7c88.0681510615193911621741.png">
                                </c:if>

                            </div>
                            <div class="col" style="flex-basis: 80%">
                                <c:out value="${recommendation.getDetails()}"/>
                                <input name="appointedId" value="${recommendation.getId()}"
                                       type="hidden"/>
                            </div>
                            <form method="post">
                                <div class="col" style="flex-basis: 10%">
                                    <button class="transparentButton" name="method" onclick="this.form.submit()"
                                            value="delete_recommendation"><img
                                            height="20px"
                                            src="https://png2.kisspng.com/sh/8d174c30808a5943beb25713d6f7d690/L0KzQYm3VME0N5xqfZH0aYP2gLBuTgF2aZ95jd82ZHB3PbXwkCBtaaoyjNd1ZYbsg7r2jr12dpp5fdY2c4TkhLb6TgN1d6Eyi9twbj24cbW4Vcc6bpMATtVqMD61Q4O8VMY3QWI6SqUBNki7SYaAVck2NqFzf3==/kisspng-quantum-dot-display-television-united-states-stop-sign-5ad1579fb96ca0.2325466915236688957595.png">
                                    </button>
                                </div>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
