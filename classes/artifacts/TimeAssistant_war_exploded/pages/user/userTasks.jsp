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
<jsp:include page="import.jsp" />

<!-- Timeline Script -->
<script type="text/javascript"
	src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization',
       'version':'1','packages':['timeline']}]}"></script>
<script type="text/javascript">
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var container = document.getElementById('timeline');

		var chart = new google.visualization.Timeline(container);

		var dataTable = new google.visualization.DataTable();

		dataTable.addColumn({
			type : 'string',
			id : 'Task'
		});
		dataTable.addColumn({
			type : 'date',
			id : 'Start'
		});
		dataTable.addColumn({
			type : 'date',
			id : 'End'
		});

		var table = document.getElementById('data');

		for (var r = 0, n = table.rows.length; r < n; r++) {
			dataTable.addRows([ [ table.rows[r].cells[0].innerHTML,
					new Date(table.rows[r].cells[1].innerHTML),
					new Date(table.rows[r].cells[2].innerHTML) ] ]);
		}

		var tableHeight = table.rows.length * 51 + 40;
		document.getElementById('timeline').style.height = tableHeight + "px";

		if (table.rows.length != 0) {
			chart.draw(dataTable);
		} else {
			var fieldNameElement = document.getElementById('message');
			fieldNameElement.innerHTML = "You haven't tasks!";
		}
		table.style.display = "none";
	}
</script>
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
				Looged in as ${sessionScope.user.firstName} | <a
					href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
	</nav>
	<br />

	<!-- CONTENT -->
	<div class="container">
		<div class="panel panel-primary" style="width: 1000px; margin: auto;">
			<!-- Default panel contents -->
			<div class="panel-heading">Tasks</div>

			<div class="panel-body">
				<ul class="pagination pagination-sm">
					<li <c:if test="${param.status == null}">class="active"</c:if>><a
						href="${pageContext.request.contextPath}/user/tasks">ALL</a></li>
					<li <c:if test="${param.status == 'NEW'}">class="active"</c:if>><a
						href="${pageContext.request.contextPath}/user/tasks?status=NEW">NEW</a></li>
					<li
						<c:if test="${param.status == 'IN PROGRESS'}">class="active"</c:if>><a
						href="${pageContext.request.contextPath}/user/tasks?status=IN PROGRESS">IN
							PROGRESS</a></li>
					<li
						<c:if test="${param.status == 'FINISHED'}">class="active"</c:if>><a
						href="${pageContext.request.contextPath}/user/tasks?status=FINISHED">FINISHED</a></li>
				</ul>
			</div>

			<!-- Table -->
			<table class="table" id="task_table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Description</th>
						<th>State</th>
						<th>Time</th>
						<th>RTime</th>
						<th>Project</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${requestScope.tasks}">
						<tr>
							<td><c:out value="${task.taskId}"></c:out></td>
							<td><c:out value="${task.title}"></c:out></td>
							<td><c:out value="${task.description}"></c:out></td>
							<td><span class="label label-default"><c:out
										value="${task.state}"></c:out></span></td>
							<td><c:out value="${task.estimateTime}"></c:out></td>
							<td><c:out value="${task.realTime}"></c:out></td>
							<td><c:out value="${task.projectId}"></c:out></td>
							<td><a
								href="${pageContext.request.contextPath}/user/task?task_id=${task.taskId}"
								class="btn btn-primary btn-xs">Start work</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="message" align="center"></div>
		</div>

		<br />
		<div id="timeline" style="width: 1000px; margin: auto;"></div>

	</div>

	<!-- CHART DATA -->
	<table id="data">
		<tbody>
			<c:forEach var="task" items="${requestScope.tasks}">
				<tr>
					<td>${task.title}</td>
					<td><fmt:formatDate value="${task.startDate}"
							pattern="yyyy,MM,dd" /></td>
					<td><fmt:formatDate value="${task.endDate}"
							pattern="yyyy,MM,dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>