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
    <title>Project info</title>
    <jsp:include page="header.jsp" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script>
        $(function() {
            $("#startDate").datepicker();
            $("#endDate").datepicker();
        });
    </script>
</head>

<body>
<!-- CONTENT -->
<div class="container tasks-table">
    <div class="panel panel-primary" style="width: 1000px; margin: auto;">
        <!-- Default panel contents -->
        <div class="panel-heading"><fmt:message key="task.title"/></div>

        <!-- Table -->
        <form action="${pageContext.request.contextPath}/manager/project" method="post" id="tasks-form">
            <div class="row">
                <div class="table-responsive tile col-md-10 col-sm-12 col-xs-12 col-md-offset-1">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="task.name" /></th> <!-- title property -->
                            <th><fmt:message key="task.description" /></th>
                            <th><fmt:message key="task.state" /></th>
                            <th><fmt:message key="task.estimate" /></th>
                            <th><fmt:message key="task.real" /></th>
                            <th><fmt:message key="task.start" /></th>
                            <th><fmt:message key="task.end" /></th>
                            <th><fmt:message key="task.finished" /></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="task" items="${requestScope.tasks}">
                            <tr>
                                <td>
                                    <label class="checkbox" for="checkbox${task.id}">
                                        <input type="checkbox" name="checkedTasks" value="${task.id}"
                                               id="checkbox${task.id}" data-toggle="checkbox">
                                    </label>
                                </td>
                                <td>${task.title}</td>
                                <td>${task.description}</td>
                                <td>${task.state}</td>
                                <td>${task.estimateTime}</td>
                                <td>${task.realTime}</td>
                                <td>${task.startDate}</td>
                                <td>${task.endDate}</td>
                                <td>${task.finished}</td>

                                    <%--edit task icon => edit user info pop up window--%>
                                <td>
                                <span class="fui-new modal-icon" data-toggle="modal"
                                      data-target="#modalEdit${task.id}"></span>

                                    <div class="modal fade" id="modalEdit${task.id}" tabindex="-1" role="dialog"
                                         aria-labelledby="modalEditLabel${task.id}" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">&times;</button>
                                                    <h4 class="modal-title">ID: ${task.id}</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <span><fmt:message key="task.name"/></span>
                                                        <input class="form-control" placeholder="<fmt:message key="task.name"/>"
                                                               name="task_name-${task.id}"
                                                               value="${task.title}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <span><fmt:message key="task.description"/></span>
                                                        <input class="form-control" placeholder="<fmt:message key="task.description"/>"
                                                               name="task_description-${task.id}"
                                                               value="${task.description}"/>
                                                    </div>
                                                    <div class="form-group">
                                                            <%--@declare id="tasks-form"--%>
                                                        <span><fmt:message key="task.state"/></span>
                                                        <select name="state-${task.id}" class="select-block"
                                                                form="tasks-form">
                                                            <c:choose>
                                                                <c:when test="${task.state == 'NEW'}">
                                                                    <option value="NEW" selected="selected">
                                                                        <fmt:message key="state.new"/>
                                                                    </option>
                                                                    <option value="IN PROGRESS">
                                                                        <fmt:message key="state.progres"/>
                                                                    </option>
                                                                    <option value="FINISHED">
                                                                        <fmt:message key="state.done"/>
                                                                    </option>
                                                                </c:when>
                                                                <c:when test="${task.state == 'IN PROGRESS'}">
                                                                    <option value="NEW">
                                                                        <fmt:message key="state.new"/>
                                                                    </option>
                                                                    <option value="IN PROGRESS" selected="selected">
                                                                        <fmt:message key="state.progres"/>
                                                                    </option>
                                                                    <option value="FINISHED">
                                                                        <fmt:message key="state.done"/>
                                                                    </option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="NEW">
                                                                        <fmt:message key="state.new"/>
                                                                    </option>
                                                                    <option value="IN PROGRESS">
                                                                        <fmt:message key="state.progres"/>
                                                                    </option>
                                                                    <option value="FINISHED" selected="selected">
                                                                        <fmt:message key="state.done"/>
                                                                    </option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <span><fmt:message key="task.estimate"/></span>
                                                        <input class="form-control" placeholder="<fmt:message key="task.estimate"/>"
                                                               name="estimate_time-${task.id}" id="estimateTime" type="time"
                                                               value="${task.estimateTime}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <span><fmt:message key="task.start"/></span>
                                                        <input class="form-control" placeholder="<fmt:message key="task.start"/>"
                                                               name="start_date-${task.id}" id="startDate"
                                                               value="${task.startDate}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <span><fmt:message key="task.end"/></span>
                                                        <input class="form-control" placeholder="<fmt:message key="task.end"/>"
                                                               name="end_date-${task.id}" id="endDate"
                                                               value="${task.endDate}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <button class="btn btn-primary btn-lg btn-block" name="update"
                                                                type="submit" value="${task.id}">
                                                            <fmt:message key="button.submit"/>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7 col-sm-7 col-xs-6 col-md-offset-1">
                    <button id="trigger-overlay" type="button" class="btn btn-primary" name="addTask">
                        <fmt:message key="button.addTask"/>
                    </button>
                    <button class="btn btn-danger" name="delete" type="submit" value="Delete">
                        <fmt:message key="button.delete"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../parts/scripts.jsp"/>
</body>
</html>
