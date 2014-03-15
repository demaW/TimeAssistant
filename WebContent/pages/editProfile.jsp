<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text_en_US"/>

<html lang="${language}">
<head>
    <title>Profile Info</title>
    <jsp:include page="parts/header.jsp"/>
</head>

<body>
<div class="container-fluid users-table">
    <%--navbar--%>
    <div class="row">
        <div class="col-md-10 col-xs-12 col-md-offset-1 table-nav">
            <jsp:include page="parts/navbar.jsp"/>
        </div>
    </div>
    <%--drop down list for user functionality--%>
    <div class="row">
        <div class="col-md-10 col-sm-12 col-xs-12 col-md-offset-1 tables-menu">
            <h1><fmt:message key="profile.title"/></h1>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <fmt:message key="profile.title"/>
                        <b class="caret"></b>
                    </a>
                    <span class="dropdown-arrow"></span>
                    <ul class="dropdown-menu">
                        <li>
                            <%--todo pages for current project + curr. tasks for user--%>
                            <a href="/projects"><fmt:message key="projects.title"/></a>
                        </li>
                        <li>
                            <a href="/tasks"><fmt:message key="tasks.title"/></a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <form action="/edit" enctype="multipart/form-data"  method="post" id="usertable-form">
        <div class="row">
            <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                <table class="table table-striped table-hover">
                    <%--Titles--%>
                    <thead>
                    <tr>
                        <%--<th></th>--%>
                        <th>#</th>
                        <th><fmt:message key="user.firstName"/></th>
                        <th><fmt:message key="user.lastName"/></th>
                        <th><fmt:message key="user.email"/></th>
                        <th><fmt:message key="user.password"/></th>
                        <th><fmt:message key="user.position"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.password}</td>
                            <td>${user.position}</td>
                                <%--edit photo--%>
                            <td>
                                <span class="fui-photo modal-icon" data-toggle="modal"
                                    data-target="#modalImage${user.id}"></span>

                                <div class="modal fade" id="modalImage${user.id}" tabindex="-1" userRole="dialog"
                                     aria-labelledby="modalImageLabel${user.id}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">${user.firstName} ${user.lastName}</h4>
                                            </div>
                                            <div class="modal-body">
                                                <img src="../img/employees/${user.image}">

                                                <p>${user.image}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                                <%--edit profile pop up window--%>
                            <td>
                                    <span class="fui-new modal-icon" data-toggle="modal"
                                          data-target="#modalEdit${user.id}"></span>

                                <div class="modal fade" id="modalEdit${user.id}" tabindex="-1" userRole="dialog"
                                     aria-labelledby="modalEditLabel${user.id}" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">ID: ${user.id}</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <span><fmt:message key="user.firstName"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="user.firstName"/>"
                                                           name="first_name-${user.id}"
                                                           value="${user.firstName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.lastName"/></span>
                                                    <input class="form-control" placeholder="<fmt:message key="user.lastName"/>"
                                                           name="last_name-${user.id}"
                                                           value="${user.lastName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.image"/></span>
                                                    <div class="input-group">
                                                            <span class="input-group-btn">
                                                                <span class="btn btn-primary btn-file">
                                                                    <fmt:message key="button.browse"/>
                                                                    <input type="file" name="userImage-${user.id}"
                                                                           accept="image/*"/>
                                                                </span>
                                                            </span>
                                                        <input type="text" class="form-control" readonly="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.email"/></span>
                                                    <input class="form-control" type="email" placeholder="<fmt:message key="user.email"/>"
                                                           name="email-${user.id}"
                                                           pattern="[^ @]*@[^ @]*\.[^ @]{2,}" value="${user.email}"/>
                                                </div>
                                                <div class="form-group">
                                                    <span><fmt:message key="user.position"/></span>
                                                    <input class="form-control" type="text" placeholder="<fmt:message key="user.position"/>"
                                                           name="discount-${user.id}"
                                                           value="${user.position}"/>
                                                </div>
                                                <div class="form-group">
                                                    <button class="btn btn-primary btn-lg btn-block" name="update"
                                                            type="submit" value="${user.id}">
                                                        <fmt:message key="button.update"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </form>
</div>
<jsp:include page="parts/scripts.jsp"/>
</body>
</html>
