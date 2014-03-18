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
<title>User Tasks</title>
<jsp:include page="header.jsp" />
</head>

<body>
	<div class="container">
		<div class="panel panel-primary" style="width: 800px;">
			<!-- Default panel contents -->
			<div class="panel-heading">Tasks</div>

			<div class="panel-body">
				<ul class="pagination pagination-sm">
					<li <c:if test="${param.status == null}">class="active"</c:if> ><a href="${pageContext.request.contextPath}/user/tasks">ALL</a></li>
					<li <c:if test="${param.status == 'NEW'}">class="active"</c:if> ><a href="${pageContext.request.contextPath}/user/tasks?status=NEW">NEW</a></li>
					<li <c:if test="${param.status == 'IN PROGRESS'}">class="active"</c:if> ><a href="${pageContext.request.contextPath}/user/tasks?status=IN PROGRESS">IN PROGRESS</a></li>
					<li <c:if test="${param.status == 'FINISHED'}">class="active"</c:if> ><a href="${pageContext.request.contextPath}/user/tasks?status=FINISHED">FINISHED</a></li>
				</ul>
			</div>

			<!-- Table -->
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Description</th>
						<th>State</th>
						<th>Time</th>
						<th>RTime</th>
						<th>Project id</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${tasks}">
						<tr>
							<td><c:out value="${task.taskId}"></c:out></td>
							<td><c:out value="${task.title}"></c:out></td>
							<td><c:out value="${task.description}"></c:out></td>
							<td><span class="label label-default"><c:out
										value="${task.state}"></c:out></span></td>
							<td><c:out value="${task.estimateTime}"></c:out></td>
							<td><c:out value="${task.realTime}"></c:out></td>
							<td><c:out value="${task.projectId}"></c:out></td>
							<td><a href="#" class="btn btn-primary btn-xs">Start
									work</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>