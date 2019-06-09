<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<head>
    <style>
        th, td {
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
<div class="box">
    <a href="/patients">
        <img style="position: absolute; padding-top: 15px; padding-left: 5px" height="30px"
             src="https://png2.kisspng.com/sh/6e5588ec068618cd4c06c88aaed56456/L0KzQYm3V8AyN5ZniJH0aYP2gLBuTfFzepD8RdV4bYD4hLb5Tflkd594ReVEbXLyfH7qjPlxNZJ3jJ9wbz3lcbTyTcVjO2o1SNUCOUa3dbSCTsM6PWM7T6gCMUW2QIW9UsQxP2c2Tak3cH7q/kisspng-arrow-computer-icons-symbol-clip-art-go-back-5b3900c7964ec9.3952676715304624076157.png">
    </a>
    <form method="post">
        <input type="hidden" name="method" value="discharge">
    <button style="position: relative; float: right; padding-top: 15px" name="patientId" value="${patient.getId()}">
        <img height="30px"
             src="https://png2.kisspng.com/sh/17b12ca887ef84713255a3a3b494499e/L0KzQYm3VMA1N6hofZH0aYP2gLBuTgN1aZdrRdHvLXjogr7sk710dpJwfZ92ZXTsc7r1hb10gZ5nh942Y4LoccXwlvl1gV46edU9Ykm4SYbtWPZnQF87UaQEOEi4QYK8UsI5PGIATKc8OUG3PsH1h5==/kisspng-staff-of-hermes-snake-medicine-symbol-creativity-5ac4b9595f8ff8.6929885115228419453914.png">
    </button>
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
    <table>
        <tr style="background-color: lightsteelblue">
            <th style="padding-right: 23px;">
                <button onclick="document.getElementById(add_dia).style.display = 'inline'"><img
                        style="float: left; padding-left: 3px; padding-top: 2px" height="20px"
                        src="https://png2.kisspng.com/sh/12c109b3b2f84df88449e16d76b8f4c6/L0KzQYm3VMAyN6h7fZH0aYP2gLBuTfFlbJp5gdH3LXPyfcH8lPVzNZpoh9D8LXPvecG0ggJ1NZJpfJ99bz3mccP7TfJ2fKV0hp8AYXO0SLK4hfJkP2NrTZC7OEK7SIaCVME2OmM7S6U7NEa6R4KCTwBvbz==/kisspng-addition-computer-icons-clip-art-add-to-cart-button-5ac18a1ebc72f5.2828859415226332467719.png">
                </button>
                <form method="post">
                    Diagnosis
                    <div class="box" id="add_dia"
                         style="display: none; width: auto; position: absolute; z-index: 999; font-size: 20px">
                        New Diagnosis: <input name="diagnosis" type="text"/>
                        <br>
                        <input name="method" value="add_diagnosis" type="hidden"/>
                        <button name="patientId" value="${patient.getId()}" onclick="this.form.submit()">Add</button>
                    </div>
                </form>
            </th>
            <th colspan="3" align="left">Set date</th>
        </tr>
        <c:forEach items="${diagnoses}" var="diagnosis">
            <tr style="background-color: white">
                <th><c:out value="${diagnosis.getDiagnosis()}"/></th>
                <th><c:out value="${diagnosis.getSetDate()}"/></th>
                <th>
                    <button class="show">Show appointed</button>
                </th>
                <th>
                    <button>Add appointed</button>
                    <form method="post">
                        <div class="box" id="add_app"
                             style="display: block; width: auto; position: absolute; z-index: 999; font-size: 20px">
                            Type: <select name="type">
                            <option value="operation">
                                <img height="30px"
                                     src="https://png2.kisspng.com/sh/51638d4719fcd5822c9f904ba666279a/L0KzQYm3VMIzN5ptfZH0aYP2gLBuTgNkaZ11fd42c4X1d7b5mb1kd551jeZucj3sc7F1k710faNsfdH3LXLvcbXsTgZma6V0ip8AYXTmQrXoVvU0bWY3UJCCN0O2RoS3VME2OmU4T6s5NEa8Q4S8TwBvbz==/kisspng-scalpel-surgery-computer-icons-surgeon-blade-vector-5adc2da6e3e528.7733630415243790469335.png">
                                Operation
                            </option>
                            <option value="procedure">
                                <img height="30px"
                                     src="https://png2.kisspng.com/sh/2322c4fc9ca88f39833cafb4515423b0/L0KzQYm3U8I1N6V5iZH0aYP2gLBuTflvcpZojNt4bj3mf773lgRmel5ue9H3cz3zeLL5jfFkbaZ5gdVqbD3ngsbuTfh6eJDpReVud3nxd371hfVldJYyTdNrNkPpc4O5WfM1O2czUKQDOUe8QIe4VcIyQGo4S6M9MUe0QXB3jvc=/kisspng-injection-computer-icons-pharmaceutical-drug-hypod-sewing-needle-5ab63fc229c436.8289790615218933141711.png">
                                Procedure
                            </option>
                            <option value="medicine"
                                    style="background-image: url(../images/medicine.png)">
                                Medicine
                            </option>
                        </select>
                            <br>
                            Details: <input name="details" type="text"/>
                            <br>
                            Days: <input name="num_days" type="number" min="1"/>
                            <br>
                            Time: <input name="time" type="time"/><img
                                style="padding-left: 7px; padding-top: 7px" height="15px"
                                src="https://png2.kisspng.com/sh/0a5ee08fd40f309cc81c62ff73edbd8a/L0KzQYm3U8E2N5poj5H0aYP2gLBuTgBtfaQyedDtLX3sfsb6TgNqb594RdV4bYD4hLb5Tflkd594RdV1aYCwccP7TgBtfaQyi9twbj24cbLohMg2P2Q7SdVrMj68RomAWMMzOWI6SqM6NEW8RIS5UsE4NqFzf3==/kisspng-plus-and-minus-signs-computer-icons-clip-art-plus-sign-5aaad857361cb2.9687832115211459432217.png">
                            <br>
                            <input name="time" type="time"/><br>
                            Performer:
                            <br>
                            <select name="performerId" style="float: left; width: 80px">
                                <c:forEach items="${doctors}" var="doctor">
                                    <option value="${doctor.getId()}">${doctor.getSurname()} ${doctor.getName()}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <input name="method" value="add_appointed" type="hidden"/>
                            <button name="diagnosisId" value="${diagnosis.getId()}" onclick="this.form.submit()">Add
                            </button>
                        </div>
                    </form>
                </th>
            </tr>
            <tr style="background-color: white">
                <th colspan="3">
                    <div style="display: block; position: relative; left: 50px" id="appointeds">
                        <table>
                            <tr style="background-color: lightcyan; ">
                                <th width="30px">Type</th>
                                <th width="500px">Details</th>
                            </tr>
                            <c:set var="recommendations" scope="page" value="${diagnosis.getRecommendations()}"/>
                            <c:forEach items="${recommendations}" var="recommendation">
                                <tr style="background-color:ghostwhite; border-width: 0px">
                                    <th>
                                        <c:if test="${recommendation.getType() eq 'operation'}">
                                            <img height="30px"
                                                 src="https://png2.kisspng.com/sh/51638d4719fcd5822c9f904ba666279a/L0KzQYm3VMIzN5ptfZH0aYP2gLBuTgNkaZ11fd42c4X1d7b5mb1kd551jeZucj3sc7F1k710faNsfdH3LXLvcbXsTgZma6V0ip8AYXTmQrXoVvU0bWY3UJCCN0O2RoS3VME2OmU4T6s5NEa8Q4S8TwBvbz==/kisspng-scalpel-surgery-computer-icons-surgeon-blade-vector-5adc2da6e3e528.7733630415243790469335.png">
                                        </c:if>
                                        <c:if test="${recommendation.getType() eq 'procedure'}">
                                            <img height="30px"
                                                 src="https://png2.kisspng.com/sh/2322c4fc9ca88f39833cafb4515423b0/L0KzQYm3U8I1N6V5iZH0aYP2gLBuTflvcpZojNt4bj3mf773lgRmel5ue9H3cz3zeLL5jfFkbaZ5gdVqbD3ngsbuTfh6eJDpReVud3nxd371hfVldJYyTdNrNkPpc4O5WfM1O2czUKQDOUe8QIe4VcIyQGo4S6M9MUe0QXB3jvc=/kisspng-injection-computer-icons-pharmaceutical-drug-hypod-sewing-needle-5ab63fc229c436.8289790615218933141711.png">
                                        </c:if>
                                        <c:if test="${recommendation.getType() eq 'medicine'}">
                                            <img height="30px"
                                                 src="https://png2.kisspng.com/sh/29edaf893cdbca8875bf7d4d634e73c8/L0KzQYm3UsI0N5NAiZH0aYP2gLBuTgBpgaRue9tqbj3wdbXwgBlvbV5ue9H3LXPkgMT8jPUubJDojNH7LXnmf7A0VfE6OGI2etM7YUfmSIm1UMY5OWY2SKg6NUG8Q4q4UcYzOWg5SZD5bne=/kisspng-physician-medicine-icon-capsule-doctor-icon-5a9011ba2a7c88.0681510615193911621741.png">
                                        </c:if>

                                    </th>
                                    <th>
                                        <form method="post">
                                            <c:out value="${recommendation.getDetails()}"/>
                                                <%--<input name="patientId" value="${patient.getId()}" type="hidden"/>--%>
                                            <input name="appointedId" value="${recommendation.getId()}" type="hidden"/>
                                            <button name="method" onclick="this.form.submit()"
                                                    value="delete_recommendation"
                                                    style="position: relative; float: right; right: 5px"><img
                                                    height="20px"
                                                    src="https://png2.kisspng.com/sh/8d174c30808a5943beb25713d6f7d690/L0KzQYm3VME0N5xqfZH0aYP2gLBuTgF2aZ95jd82ZHB3PbXwkCBtaaoyjNd1ZYbsg7r2jr12dpp5fdY2c4TkhLb6TgN1d6Eyi9twbj24cbW4Vcc6bpMATtVqMD61Q4O8VMY3QWI6SqUBNki7SYaAVck2NqFzf3==/kisspng-quantum-dot-display-television-united-states-stop-sign-5ad1579fb96ca0.2325466915236688957595.png">
                                            </button>
                                        </form>
                                    </th>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </th>
                <th></th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
