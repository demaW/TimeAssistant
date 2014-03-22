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

		var values = [ [ 'Task', 'Tasks per Day' ] ];

		var table = document.getElementById('data');
		for (var r = 0, n = table.rows.length; r < n; r++) {
			values.push([ table.rows[r].cells[0].innerHTML,
					parseInt(table.rows[r].cells[1].innerHTML, 10) ]);
		}

		var data = google.visualization.arrayToDataTable(values);

		var options = {
			title : 'My tasks on today',
			pieHole : 0.4,
		};

		var chart = new google.visualization.PieChart(document
				.getElementById('donutchart'));
		chart.draw(data, options);
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
				<li><a href="${pageContext.request.contextPath}/user/tasks">User
						tasks</a></li>
				<li><a
					href="${pageContext.request.contextPath}/user/userEditProfile">Profile</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/user/stats">Statistic</a></li>
			</ul>
			<div class="navbar-form navbar-right">
				Looged in as ${user.firstName} | <a
					href="${pageContext.request.contextPath}/logout">Log out</a>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>
	<!-- /navbar -->

	<br />

	<!-- CONTENT -->
	<div class="container">
		<div id="donutchart"
			style="width: 900px; height: 500px; margin: auto;"></div>
	</div>

	<!-- CHART DATA -->
	<table id="data">
		<tbody>
			<tr>
				<td>NEW</td>
				<td>2</td>
			</tr>
			<tr>
				<td>IN PROGRESS</td>
				<td>1</td>
			</tr>
			<tr>
				<td>FINISHED</td>
				<td>1</td>
			</tr>
		</tbody>
	</table>

</body>
</html>