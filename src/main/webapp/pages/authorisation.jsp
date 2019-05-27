<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page = 'mainPage.jsp'/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>
<html>
<body>
<div class="box">
	<h3><fmt:message key="authorisation"/>!</h3>
	<form action="/authorisation" method="POST">
	<fmt:message key="authorisation.email"/>:<input type="text" name="email"><br>
	<fmt:message key="authorisation.password"/>:<input type="password" name="password"><br>
	<script type="text/javascript">
	if("${fail}"){
	document.writeln(<div><fmt:message key = "entrance.fail"/></div>);
	alert("Incorrect data!");
	}
	</script>
	<button onclick="this.form.submit()">
	<fmt:message key="authorisation.check"/>
	</button>
	</form>
</div>
</body>
</html>