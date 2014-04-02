<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />
<html lang="${language}">


<head>
<title>Create invoice</title>
<jsp:include page="header.jsp" />


<script>
<!-- GRAPH SCRIPT -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/amcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/serial.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/themes/light.js"></script>
<script type="text/javascript">
	var chart;
	var chartCursor;

	var chartLineData = [];
	//AmCharts.theme = AmCharts.themes.light;
	AmCharts.ready(function() {
		drawBarChart();
		drawLineChart();
	});
	function drawBarChart() {
		var chart = AmCharts.makeChart("chartdiv", {
		    "type": "serial",
		    "theme": "chalk",
		    "columnWidth:": 0.6,
		    "columnSpacing": 5,
		    "dataProvider": [{
		        "year": 2005,
		        "income": 23.5,
		        "expenses": 18.1
		    }, {
		        "year": 2006,
		        "income": 26.2,
		        "expenses": 22.8
		    }, {
		        "year": 2007,
		        "income": 30.1,
		        "expenses": 23.9
		    }, {
		        "year": 2008,
		        "income": 29.5,
		        "expenses": 25.1
		    }, {
		        "year": 2009,
		        "income": 24.6,
		        "expenses": 25
		    }],
		    "valueAxes": [{
		        "axisAlpha": 0,
		        "position": "top"
		    }],
		    "startDuration": 1,
		    "graphs": [{
		        "balloonText": "Income:[[value]]",
		        "fillAlphas": 0.8,
		        "lineAlpha": 0.2,
		        "title": "Income",
		        "type": "column",
		        "valueField": "income"
		    }, {
		        "balloonText": "Expenses:[[value]]",
		        "fillAlphas": 0.8,
		        "lineAlpha": 0.2,
		        "title": "Expenses",
		        "type": "column",
		        "valueField": "expenses"
		    }],
		    "rotate": false,
		    "categoryField": "year",
		    "categoryAxis": {
		        "gridPosition": "start",
		        "position": "left"
		    },
			"exportConfig":{
				"menuBottom":"20px",
		        "menuRight":"20px",
		        "menuItems": [{
		        "icon": '/lib/3/images/export.png',
		        "format": 'png'	  
		        }]  
		    }
		});
	}
</script>



</head>
<body>

<!-- BAR CHART DATA -->
	<table id="totalfinisheddata">
		<tbody>
			
			<tr>
				<td>estimate</td>
				<td><c:out value="${esttime}" /></td>
				
			
			<tr>
				<td>real</td>
				<td><c:out value="${realtime}" /></td>
			</tr>
			<tr>
				<td>names </td>
				<td><c:out value="${uniqueusers}" /></td>
			</tr>
			
		</tbody>
	</table>




</body>
</html>