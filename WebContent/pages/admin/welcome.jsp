<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>

    <title>admin page</title>
    <jsp:include page="header.jsp" />

</head>

<body>
<div class = "container">
	<div style="text-align: center;">
	<img  src="${pageContext.request.contextPath}/img/logo.png" width="300">
	</div>
	<h1  style = "text-align: center"> Welcome to admin page</h1> <br>
	<h2	 style = "text-align: center"> As administrator u can:</h2><br>
	<ul>
			<li><h3>Add user</h3></li>
			<li><h3>Edit user information and send email notitfication</h3></li>
			
		</ul>
	
	<p> </p>
</div>
</body>
</html>