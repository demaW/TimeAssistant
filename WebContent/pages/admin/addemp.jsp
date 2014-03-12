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
    <title>adminpage</title>
    <jsp:include page="header.jsp" />
    
</head>
<body>
<div class="container">
<form action="/TimeAssistant/pages/admin/adduser"  method="post">
<table border = 1>
 <tr>
 <td>   <fmt:message key="employee.email"/> </td>
 <td><input type="text" name="email"> </td>
 </tr>
  <tr>
 <td>   <fmt:message key="employee.password"/> </td>
 <td><input type="text" name="password"> </td>
 </tr>
 <tr>
 <td>   <fmt:message key="employee.firstName"/> </td>
 <td><input type="text" name="firstName"> </td>
 </tr>
 <tr>
 <td>   <fmt:message key="employee.lastName"/> </td>
 <td><input type="text" name="lastName"> </td>
 </tr>
 <tr>
 <td>   <fmt:message key="employee.position"/> </td>
 <td><input type="text" name="position"> </td>
 </tr>
 <tr>
 <td> <fmt:message key="employee.role"/></td>
 <td><select name="role"> 

  <option value="user">user</option>
  <option value="manager">manager</option>
  <option value="admin">admin</option></select> </td>
 </tr>
  <tr>
  <td> </td>
  <td>
  <button name="submit" type="submit" value="Submit">
                                <fmt:message key="button.addUser"/>
                            </button></td>
  </tr>
 </table> 
 </form>
</div>
</body>
</html>
