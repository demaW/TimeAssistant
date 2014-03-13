<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>--%>
<link href="http://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Cabin" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="../../css/flat-ui.css"/>
<link rel="stylesheet" type="text/css" href="../../css/styles.css"/>
<link rel="stylesheet" type="text/css" href="../../css/docs.css"/>
<link rel="stylesheet" type="text/css" href="../../css/demo.css"/>
<script src="../../js/jquery-2.0.3.min.js"></script>
<script src="../../js/modernizr.custom.js"></script>
<body>

<nav class="navbar navbar-default" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
      <span class="sr-only">Toggle navigation</span>
    </button>
    <a class="navbar-brand" href="/TimeAssistant/pages/manager/manager.jsp">Display all projects</a>
  </div>
  <div class="collapse navbar-collapse" id="navbar-collapse-01">
    <ul class="nav navbar-nav">           
      <li class="active"><a href="/TimeAssistant/pages/manager/addproject.jsp">Add new project</a></li>
      <li><a href="#fakelink">Teams</a></li>
       <li><a href="#fakelink">Employees</a></li>
       <li><a href="#fakelink">other Features</a></li>
    </ul>           
    <div class="navbar-form navbar-right"> Looged in as ${userName}
    </div>
  </div><!-- /.navbar-collapse -->
</nav><!-- /navbar -->
</body>
