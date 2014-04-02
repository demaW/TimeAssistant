<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text_en_US" />

<html lang="${language}">
<head>
<title>User Profile</title>
<jsp:include page="import.jsp" />
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
				<li><a href="${pageContext.request.contextPath}/user/tasks">User
						tasks</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/user/userEditProfile">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/user/stats">Statistic</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${sessionScope.user.firstName} | <a
					href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
	</nav>
	<br />

	<!-- CONTENT -->
	<div class="container">
		<form action="${pageContext.request.contextPath}/user/userEditProfile"
			method="post">
			<table class="table">
				<tr>
					<td>First Name:</td>
					<td><input type="text" name="firstName" required
						value="${sessionScope.user.firstName}"></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName" required
						value="${sessionScope.user.lastName}"></td>
				</tr>
				<tr>
					<td>email:</td>
					<td><input type="text" name="email"
						pattern="[^ @]*@[^ @]*\.[^ @]{2,}" required
						value="${sessionScope.user.email}"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value = ""></td>
				</tr>
				<tr>
					<td align="right"><a
						href="${pageContext.request.contextPath}/user/tasks"
						class="btn btn-default">Cancel</a></td>
					<td>
						<button name="submit" type="submit" class="btn btn-primary"
							value="Submit">Save</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>