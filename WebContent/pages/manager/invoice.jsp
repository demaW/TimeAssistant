<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text_en_US" />

<html lang="${language}">
<head>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css" />
    <title>Create invoice</title>
    <jsp:include page="header.jsp" />
</head>
<body>
<div class="container">
  <h3>Project name: ${project.projectName}</h3> <br>
  <p> Project id: ${project.projectId }</p>
  <br>
	  <table  class="table table-striped"  >
	  	<thead>
	  	<tr>
	  	<th>Employee</th>
	  	<th>Position</th>
	  	<th>Task</th>
	  	<th>Worked hours</th>
	  	<th>Salary rate</th>
	  	<th>Cost per customer</th>
	  	</tr>
	  	</thead>
	  	<c:forEach var="invoice" items="${invoices}">
	  	<tr>
	  		<td>user.name+user.last.name</td>
	  		<td>user.position</td>
	  		<td>task.name</td>
	  		<td>task.realTime</td>
	  		<td>user.salaryRate</td>
	  		<td>task.realtime*user.salatyRate</td>
	  	</tr>
	  	</c:forEach>
	  </table>
	  	 <div class="row">
    <div class="col-md-6" ></div><div class="col-md-6"><span class="pull-right"> Total cost:  $42</span></div>
  </div>
  </div>

			

	
	

</body>
</html>