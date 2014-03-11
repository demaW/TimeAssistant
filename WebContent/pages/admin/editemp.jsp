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
<table border = 1>
 <tr>
 <td>email </td>
 <td><input type="text" name="email"> </td>
 </tr>
 <tr>
 <td>FirstName: </td>
 <td><input type="text" name="firstName"> </td>
 </tr>
 <td>LastName: </td>
 <td><input type="text" name="lastName"> </td>
 </tr>
 <td>Posittion: </td>
 <td><input type="text" name="posittion"> </td>
 </tr>
 <td>Role </td>
 <td><select> 

  <option value="user">1 - user</option>
  <option value="manager">2 - manager</option>
  <option value="admin">3 - admin</option></select> </td>
 </tr>
 
 </table> 
</div>
</body>
</html>
