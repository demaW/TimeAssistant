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
    <title>User Task</title>
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
				<li class="active"><a
					href="${pageContext.request.contextPath}/user/tasks">User tasks</a></li>
				<li><a
					href="${pageContext.request.contextPath}/user/userEditProfile">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/user/stats">Statistic</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${sessionScope.userName} <a href="#logout">Log out</a>
			</div>
		</div>
	</nav>
	<br />

	<!-- CONTENT -->
	<div class="container">
		<p class="lead">
			<c:out value="${task.title}"></c:out>
		</p>
		<form action="${pageContext.request.contextPath}/user/task"
			method="post">
			<table class="table">
				<tr>
					<td>ID:</td>
					<td><c:out value="${task.taskId}"></c:out></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><c:out value="${task.description}"></c:out></td>
				</tr>
				<tr>
					<td>Status:</td>
					<td><span class="label label-default"><c:out
								value="${task.state}"></c:out></span></td>
				</tr>
				<tr>
					<td>Project id:</td>
					<td><c:out value="${task.projectId}"></c:out></td>
				</tr>
				<tr>
					<td>Time estimate:</td>
					<td><c:out value="${task.estimateTime}"></c:out></td>
				</tr>
				<tr>
					<td>Real time:</td>
					<td><input type="hidden" value="${task.taskId}" name="task_id">
						<div class="input-group input-group-sm">
							<input type="text" name="realTime" value="${task.realTime}"
								pattern="[0-5][0-9]:[0-5][0-9]:[0-5][0-9]" required>
						</div></td>
				</tr>
				<tr>
					<td>Is finished:</td>
					<td><input type="checkbox" name="finished"></td>
				</tr>
				<tr>
					<td></td>
					<td><button name="submit" type="submit"
							class="btn btn-primary btn-hg" value="Submit">Save</button></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>