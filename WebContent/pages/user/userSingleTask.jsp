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
				<li><a href="#">Home</a></li>
				<li class="active"><a href="#">Personal page</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${userName} <a href="#logout">Log out</a>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- /navbar -->

	<!-- CONTENT -->
	<div class="container">
		<p class="lead">
			<c:out value="${task.title}"></c:out>
		</p>

		<table>
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
				<td>
					<div class="input-group input-group-sm">
						<input type="text" class="form-control"> <span
							class="input-group-addon">h</span>
					</div>
				</td>
			</tr>

			<tr>
				<td><a href="#" class="btn btn-default">Mark as finished</a></td>
				<td><a href="#" class="btn btn-primary btn-hg">Save</a></td>
			</tr>
		</table>
	</div>

</body>
</html>