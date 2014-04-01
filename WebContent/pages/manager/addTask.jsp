<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
    <title>Add task</title>
    <jsp:include page="/pages/user/import.jsp" />
    <jsp:include page="header.jsp" />
</head>

<body>
    <%--todo add opportunity to choose project / fix task creation --%>
	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/manager/addTask" method="post">
			<input type="hidden">
			<table class="table">
                <tr>
                    <td>Project:</td>
                    <td>
                        <select name="assigned_project">
                            <c:forEach var="project" items="${sessionScope.projects}">
                                <option value="${project.id}">
                                    <c:out value="${project.projectName}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
				<tr>
					<td>Title:</td>
					<td>
						<input type="text" name="title" required>
					</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><textarea name="description" required></textarea></td>
				</tr>
				<tr>
					<td>Estimate time:</td>
					<td><input type="number" name="estimate_time" required></td>
				</tr>
				<tr>
					<td>Assign to user:</td>
					<td><select name="user_id">
							<c:forEach var="user" items="${requestScope.users_in_project}">
								<option value="${user.id}">
								    <c:out value="${user.firstName} ${user.lastName}"/>
                                </option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right"><a href="${pageContext.request.contextPath}/manager/projectstable"
						class="btn btn-danger">Cancel</a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary"
							value="Submit"><fmt:message key="button.addTask"/></button>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>