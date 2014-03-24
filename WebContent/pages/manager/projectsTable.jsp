<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="language" type=""--%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<head>
    <title>Projects table</title>
    <jsp:include page="header.jsp" />
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
                ${sessionScope.user.firstName} ${sessionScope.user.lastName}  | <a href="${pageContext.request.contextPath}/logout">Log out</a>
            </div>
        </div>
    </nav>

    <!-- PAGE NAV -->
    <ul class="nav nav-tabs nav-justified">
        <li class="active"><a href="${pageContext.request.contextPath}/user/tasks">User tasks</a></li>
        <li><a href="${pageContext.request.contextPath}/user/userEditProfile">Profile</a></li>
        <li><a href="${pageContext.request.contextPath}/user/stats">Statistic</a></li>
    </ul>
    <br/>

    <!-- CONTENT -->
    <div class="container">
        <div class="panel panel-primary" style="width: 1000px; margin: auto;">
            <!-- Default panel contents -->
            <div class="panel-heading"><fmt:message key="project.title"/></div>

            <!-- Table -->
            <table class="table">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="project.name" /></th>
                    <th><fmt:message key="project.description" /></th>
                    <th><fmt:message key="project.notes"/></th>
                    <th><fmt:message key="project.tasks"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="project" items="${requestScope.projects}">
                    <tr>
                        <td>${project.id}</td>
                        <td>${project.projectName}</td>
                        <td>${project.description}</td>
                        <td>${project.notes}</td>
                        <td><a href="${pageContext.request.contextPath}/manager/project?project_id=${project.id}"
                            class="btn btn-primary btn-xs"><fmt:message key="project.tasks"/></a></td>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
