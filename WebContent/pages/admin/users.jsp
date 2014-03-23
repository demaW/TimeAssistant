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
   
    <title>admin page</title>
    <jsp:include page="header.jsp" />
<%-- <meta http-equiv="refresh" content="${pageContext.request.contextPath}/admin/users" /> --%>
</head>

<body>

<div class="panel panel-primary" >
<form action="${pageContext.request.contextPath}/admin/updateemployee" name="updateEmployee" method="get">
		<TABLE class="table"   >
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="user.firstName"/></th>
					<th><fmt:message key="user.lastName"/></th>
					<th><fmt:message key="user.email"/></th>
					<th><fmt:message key="user.role"/></th>
					<th><fmt:message key="user.position"/></th>
					<th><fmt:message key="user.salary"/></th>
					
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="user" items="${users}">
				
				<tr>
					<td>${user.id}</td>

					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
					<td>${user.email}</td>
					<td>${user.roleId}</td>
					<td>${user.position}</td>
					<td>${user.salaryRate}</td>
					<td><input type="radio" name="notification" value="${user.id}"></td>
					
			</tr>
			
		
		
</c:forEach>

			<a href="${pageContext.request.contextPath}/admin/adduser"> <fmt:message key="button.addUser"/></a>
			</tbody>
			
		</TABLE>
		<td><button type="submit" name="edit" class="btn btn-primary btn-hg" > <fmt:message key="button.edit" /></button></td>
			</form>
		</div>
		
		
</body>
</html>
