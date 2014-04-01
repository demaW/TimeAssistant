<%@ page contentType="text/html;charset=UTF-8" language="java"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/flat-ui.css"/>
<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/css/styles.css" />


<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<%-- <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/application.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/js/classie.js"></script>

<script src="${pageContext.request.contextPath}/js/modernizr.custom.js"></script>


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
				<li>
                    <a href="${pageContext.request.contextPath}/manager/addproject">Add new project</a>
                </li>
				<li>
                    <a href="${pageContext.request.contextPath}/manager/addTask">Add task</a>
                </li>
               
				<li><a href="${pageContext.request.contextPath}/manager/users">Employees</a></li>
				<li><a href="#fakelink">other Features</a></li>
				 <li><a href="${pageContext.request.contextPath}/manager/editProfile">Profile</a></li>
			</ul>
			<div class="navbar-form navbar-right lang">
                ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                <a href="${pageContext.request.contextPath}/logout">Log out</a>
                    <%-- i18n --%>
                <a href="<%= request.getContextPath()%>?language=${requestScope.language == 'uk' ? 'en' : 'uk'}">
                    ${requestScope.language == 'uk' ? 'EN' : 'UKR'}
                </a>
			</div>
		</div>
	</nav>

    
    <br/>
</body>

