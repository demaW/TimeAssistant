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
<link rel="stylesheet" type="text/css" href="../../css/docs.css"/>
<link rel="stylesheet" type="text/css" href="../../css/demo.css"/>
<title>adminpage</title>
<jsp:include page="header.jsp" />

<meta http-equiv="refresh" content="http://localhost:8081/TimeAssistant/pages/admin/employees" />

</head>
<body>
	<div class="container">
		<form action="/TimeAssistant/pages/admin/updateemployee" name="updateEmployee" method="post">
		<TABLE cellpadding="15" border="1" style="background-color: #ffffcc;">
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="employee.firstName"/></th>
					<th><fmt:message key="employee.lastName"/></th>
					<th><fmt:message key="employee.email"/></th>
					<th><fmt:message key="employee.password"/></th>
					<th><fmt:message key="employee.role"/></th>
					<th><fmt:message key="employee.position"/></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="employee" items="${employees}">
			
				<tr>
					<td><input type="number" name="id" value="${employee.id}"  readonly="readonly"></td>

					<td><input type="text" name="firstname" value="${employee.firstName}"></td>
					<td><input type="text" name="lastname" value="${employee.lastName}"></td>

					<td><input type="text" name="email" value="${employee.email}"></td>
					<td><input type="text" name="password" value="${employee.password}"></td>
					<td><input type="text" name="role" value="${employee.role.roleName}"></td>
					<td><input type="text" name="position" value="${employee.position}"></td>
					<td><button type="submit" value="update"> <fmt:message key="button.update"/></button>

				</tr>
</c:forEach>
			</tbody>
		</TABLE>
		</form>
		</div>
</body>
</html>
