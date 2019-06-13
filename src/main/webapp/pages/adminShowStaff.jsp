<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<jsp:include page='adminPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div class="glassBox">
    <h3><fmt:message key="menu.patients"/></h3>
    <c:if test="${staffList.size() == 0}">
        <p align="center">
            <fmt:message key="patient.list.empty"/>!
        </p>
    </c:if>
    <c:if test="${staffList.size() != 0}">
        <ul class="responsive-table">
            <li class="table-header">
                <div class="col" style="flex-basis: 20%"><fmt:message key="user.surname"/></div>
                <div class="col" style="flex-basis: 20%"><fmt:message key="user.name"/></div>
                <div class="col" style="flex-basis: 15%"><fmt:message key="user.title"/></div>
                <div class="col" style="flex-basis: 35%"><fmt:message key="user.email"/></div>
            </li>
            <form method="get" action="/diagnoses">
                <c:forEach items="${staffList}" var="staff">
                <li class="table-row">
                    <div class="col" style="flex-basis: 20%"><c:out value="${staff.getSurname()}"/></div>
                    <div class="col" style="flex-basis: 20%"><c:out value="${staff.getName()}"/></div>
                    <div class="col" style="flex-basis: 15%">
                        <c:if test="${staff.getTitle()=='doctor'}">
                            <img height="25px"
                                 src="https://png2.kisspng.com/sh/ac3eaedd9c613e585d0b9bb58c3ee340/L0KzQYm3VMA6N51Bj5H0aYP2gLBuTgZmfJZ3gdDqcnnkfn76lPV1cJD4e9H5ZT3zdcW0lvV1baNuhtN7eT32hcPuhgJ6NZNqRdpuYXz3eH68gfNjOWU8fqpsNXS6RHA4UskzOGMAT6MAMkO1RYm7WcU2P2UARuJ3Zx==/kisspng-veterinarian-stethoscope-pet-veterinary-surgery-be-health-5acb147f8c5d74.1292029715232584955749.png">
                        </c:if>
                        <c:if test="${staff.getTitle()=='nurse'}">
                            <img height="20px"
                                 src="https://png2.kisspng.com/sh/032b667526ee58ffc7cefdbfb3124e0b/L0KzQYm3VMIxN6l5iZH0aYP2gLBuTf52eqRqReU2Y3HzPbB8kwNqdpgye95ycD3kgsW0jwVze5YygNN9LUXkdIrsWMBmQWlnUaMALkO0SIq8Vsg2OWY3TKQ8MEG4SIe5VcYveJ9s/kisspng-nurse-s-cap-nursing-clip-art-nurse-hat-5ad9e80e98b915.3189568515242301586256.png">
                        </c:if>
                    </div>
                    <div class="col" style="flex-basis: 35%"><c:out value="${staff.getEmail()}"/></div>
                </li>
                </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
