<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text_en_US"/>

<nav class="navbar navbar-inverse navbar-embossed" role="navigation">
    <div class="collapse navbar-collapse" id="navbar-collapse-01">
        <%--search field--%>
        <%--<form class="navbar-form navbar-right" action="#" role="search">
            <div class="form-group">
                <div class="input-group">
                    <input class="form-control" id="navbarInput-01" type="search" placeholder="Search">
                      <span class="input-group-btn">
                        <button type="submit" class="btn"><span class="fui-search"></span></button>
                      </span>
                </div>
            </div>
        </form>--%>

        <% if (request.getSession().getAttribute("user") != null) { %>
        <div class="navbar-right logged-user">
            <div class="profile-image flow-img" style="background-image:url(../img/users/${user.image})"></div>
            <p>${user.firstName} ${user.lastName}</p>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${user.role} eq admin">
                    <li>
                        <%--todo link to some admin functionality ???--%>
                        <a href="<c:url value="/admin/employeestable"/>"><fmt:message key="nav.dropdown.admin"/></a>
                    </li>
                    <li class="divider"></li>
                </c:if>
                <c:if test="${user.role} eq manager">
                    <li>
                        <%--todo link to some manager features ??? --%>
                        <a href="<c:url value="/manager/project"/>"><fmt:message key="nav.dropdown.manager"/></a>
                    </li>
                    <li class="divider"></li>
                </c:if>

                <%--edit profile link--%>
                <li>
                    <a href="<c:url value="/edit?edit=${user.id}"/>"><fmt:message key="nav.dropdown.profile"/></a>
                </li>
                <li class="divider"></li>

                <%--log out link--%>
                <li>
                    <a href="<c:url value="/login?logout=${user.id}"/>"><fmt:message key="nav.dropdown.logout"/></a>
                </li>
                <li class="lang">
                    <a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
                        ${language == 'uk' ? 'EN' : 'UKR'}
                    </a>
                </li>
            </ul>
        </div>
        <% } else {%>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="<c:url value="/login"/>"><fmt:message key="nav.dropdown.login"/></a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="<c:url value="/registration"/>"><fmt:message key="nav.dropdown.singup"/></a>
            </li>
            <li class="lang">
                <a href="<%= request.getContextPath()%>?language=${language == 'uk' ? 'en' : 'uk'}">
                    ${language == 'uk' ? 'EN' : 'UKR'}
                </a>
            </li>
        </ul>
        <% } %>
    </div><!-- /.navbar-collapse -->
</nav><!-- /navbar -->