<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text_en_US" />

<html lang="${language}">
<head>
    <title>Add task</title>
    <jsp:include page="/pages/user/import.jsp" />
</head>

<body>
	<!-- NAVBAR -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse-01">
				<span class="sr-only">Toggle navigation</span>
			</button>
			<a class="navbar-brand" href="#">TimeAssistant</a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse-01">
			<ul class="nav navbar-nav">
				<li><a href="#">Home</a></li>
				<li class="active"><a href="#">Personal page</a></li>
			</ul>
			<div class="navbar-form navbar-right">
                ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
	</nav>

	<!-- PAGE NAV -->
	<ul class="nav nav-tabs nav-justified">
		<li><a href="#">Projects</a></li>
	</ul>
	<br />

	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/manager/addTask"
			method="post">
			<input type="hidden" name="project_id" value="${requestScope.project_id}">
			<table class="table">
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
					<td><input type="text" name="estimate_time"
						pattern="[0-5][0-9]:[0-5][0-9]:[0-5][0-9]" required
						value="00:00:00"></td>
				</tr>
				<tr>
					<td>Assign user:</td>
					<td><select name="user_id">
							<c:forEach var="user" items="${requestScope.users_in_project}">
								<option value="${user.id}">${user.firstName}
									${user.lastName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right"><a href="#projectListLink"
						class="btn btn-default">Cancel</a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary btn-hg"
							value="Submit">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>