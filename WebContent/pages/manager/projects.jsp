<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>

<html lang="${language}">
<head>
    <title>Projects</title>
    <jsp:include page="header.jsp" />
</head>

<body>
<div class="container">
    <div id="tableContainer-1">
        <TABLE class="table table-bordered" >
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message key="project.name"/></th>
                <th><fmt:message key="project.description"/></th>
                <th><fmt:message key="project.notes"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="project" items="${sessionScope.projects}">
                <form action="${pageContext.request.contextPath}/pages/manager/updateprojects" name="updateProject" method="post">
                    <tr>
                        <td>${project.id}</td>
                        <td>${project.projectName}</td>
                        <td>${project.description}</td>
                        <td>${project.notes}</td>

                        <td><button class="btn btn-inverse" type="submit" name="update" value="${project.id}">
                            <fmt:message key="button.update"/></button></td>
                        <td><button class="btn btn-danger" type="submit" name="delete" value="${project.id}">
                            <fmt:message key="button.delete"/></button></td>
                        <td><button class="btn btn-info" type="submit" name="project_id" value="${project.id}">
                            <fmt:message key="project.tasks"/></button></td>
                    </tr>
                </form>
            </c:forEach>

            <a href="${pageContext.request.contextPath}/pages/manager/addproject.jsp"> <fmt:message key="button.addProject"/></a>
            </tbody>
        </TABLE>
    </div>
</div>
</body>
</html>
