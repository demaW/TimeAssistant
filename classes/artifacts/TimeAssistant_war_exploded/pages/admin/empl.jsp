<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
    <link href="http://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Cabin" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/flat-ui.css"/>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css"/>
    <title>admin page</title>
    <jsp:include page="../parts/header.jsp" />
<meta http-equiv="refresh" content="/TimeAssistant/pages/admin/users" />
</head>

<body>
	<div class="tableContainer-1">
		<TABLE class="table" align="center">
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="user.firstName"/></th>
					<th><fmt:message key="user.lastName"/></th>
					<th><fmt:message key="user.email"/></th>
					<th><fmt:message key="user.password"/></th>
					<th><fmt:message key="user.role"/></th>
					<th><fmt:message key="user.position"/></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="user" items="${users}">
				<form action="/TimeAssistant/pages/admin/updateemployee" name="updateEmployee" method="post">
				<tr>
					<td><input type="number" name="id" value="${user.id}"  readonly="readonly"></td>

					<td><input type="text" name="firstName" value="${user.firstName}"></td>
					<td><input type="text" name="lastName" value="${user.lastName}"></td>

					<td><input type="text" name="email" value="${user.email}"></td>
					<td><input type="text" name="password" value="${user.password}"></td>
							
					<td><input type="text" name="role" value="${user.roleId}"></td>
					<td><input type="text" name="position" value="${user.position}"></td>
					<td><button type="submit" name="update" value="${employe.id}"> <fmt:message key="button.update"/></button></td>
					<td><button type="submit" name="delete" value="${user.id}"> <fmt:message key="button.delete"/></button></td>
			</tr>
			</form>
		
</c:forEach>
			<a href="/TimeAssistant/pages/admin/addemp.jsp"> <fmt:message key="button.addUser"/></a>
			</tbody>
		</TABLE>
		
		
		</div>
		
		
</body>
</html>
