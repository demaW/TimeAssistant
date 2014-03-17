	<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<head>
    <title>Projects</title>
    <jsp:include page="header.jsp" />
    <meta http-equiv="refresh" content="/TimeAssistant/pages/manager/projects" />
</head>
<body>
<div class="container">
	<div id="tableContainer-1">
		
		<TABLE class="table table-bordered" >
			<thead>
				<tr>
					<th>Id</th>
					<th><fmt:message key="project.name"/></th>
					<th><fmt:message key="project.description"/></th>
					<th><fmt:message key="project.notes"/></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="project" items="${projects}">
			<form action="/TimeAssistant/pages/manager/udapteprojects" name="updateProject" method="post">
				<tr>
					<td><input type="number" name="id" value="${project.id}"  readonly="readonly"></td>

					<td><input type="text" name="name" value="${project.projectName}"></td>
					<td><input type="text" name="description" value="${project.description}"></td>
					<td><input type="text" name="notes" value="${project.notes}"></td>
					
					<td><button type="submit" name="update" value="${project.id}"> <fmt:message key="button.update"/></button></td>
					<td><button type="submit" name="delete" value="${project.id}"> <fmt:message key="button.delete"/></button></td>
				</tr>
				</form>
            </c:forEach>
			<a href="/TimeAssistant/pages/manager/addproject.jsp"> <fmt:message key="button.addProject"/></a>
			</tbody>
		</TABLE>
		
	</div>
</div>
</body>
</html>
