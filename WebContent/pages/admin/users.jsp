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

		<TABLE class="table"   style="overflow-x:scroll">
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="user.firstName"/></th>
					<th><fmt:message key="user.lastName"/></th>
					<th><fmt:message key="user.email"/></th>
					<th><fmt:message key="user.password"/></th>
					<th><fmt:message key="user.role"/></th>
					<th><fmt:message key="user.position"/></th>
					<th><fmt:message key="user.salary"/></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="user" items="${users}">
				<form action="${pageContext.request.contextPath}/admin/updateemployee" name="updateEmployee" method="post">
				<tr>
					<td><input type="number" name="id" value="${user.id}"  readonly="readonly" ></td>

					<td><input type="text" name="firstName" value="${user.firstName}"></td>
					<td><input type="text" name="lastName" value="${user.lastName}"></td>

					<td><input type="text" name="email" value="${user.email}"></td>
					<td><input type="text" name="password" value="${user.password}"></td>
							
					<td><input type="text" name="role" value="${user.roleId}"></td>
					<td><input type="text" name="position" value="${user.position}"></td>
					<td><input type="text" name="salaryRate" value="${user.salaryRate}"></td>
					<td><button type="submit" name="update" value="${user.id}"> <fmt:message key="button.update"/></button></td>
					<td><button type="submit" name="delete" value="${user.id}"> <fmt:message key="button.delete"/></button></td>
			</tr>
			</form>
		
</c:forEach>
			<a href="${pageContext.request.contextPath}/admin/adduser"> <fmt:message key="button.addUser"/></a>
			</tbody>
		</TABLE>
		
		
		
</body>
</html>
