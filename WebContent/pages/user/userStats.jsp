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

<!-- GRAPH SCRIPT -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Tasks per Day' ], [ 'NEW', 2 ],
				[ 'IN PROGRESS', 1 ], [ 'FINISHED', 1 ] ]);

		var options = {
			title : 'My tasks on today',
			pieHole : 0.4,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('donutchart'));
		chart.draw(data, options);
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
				<li><a href="#">Home</a></li>
				<li class="active"><a href="#">Personal page</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${user.firstName} | <a
					href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- /navbar -->

	<!-- PAGE NAV -->

	<ul class="nav nav-tabs nav-justified">
		<li><a href="${pageContext.request.contextPath}/user/tasks">User
				tasks</a></li>
		<li><a
			href="${pageContext.request.contextPath}/user/userEditProfile">Profile</a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/user/stats">Statistic</a></li>
	</ul>

	<br />

	<!-- CONTENT -->
	<div class="container">
		<div id="donutchart" style="width: 900px; height: 500px;"></div>
	</div>
</body>
</html>