<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
 <script type="text/javascript">
   function validateForm()
{	 alert(document.edituser.salaryRate.value);
    if(isNaN(document.edituser.salaryRate.value))
    {	
    	
    
    	document.edituser.salaryRate.value=0;	
      alert("Salary input invalid 0.0 setted as dafault value");
      document.edituser.username.salaryRate.focus();
      return false;
    }
 </script>
    
    <title>admin page</title>
    <jsp:include page="header.jsp" />
 
</head>

<body>
	<div class="container">
		<form name = "edituser" action="${pageContext.request.contextPath}/admin/updateemployee" method="post" onsubmit="return validateForm()">
			<table border=1>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" value="${userToEdit.id}"
						readonly></td>
				</tr>

				<tr>
					<td><fmt:message key="user.email" /></td>
					<td><input type="text" name="email" value="${userToEdit.email}" required id="email"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.password" /></td>
					<td><input type="text" name="password"
						value="${userToEdit.password}" required id="password"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.firstName" /></td>
					<td><input type="text" name="firstName"
						value="${userToEdit.firstName}" required id="firstName"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.lastName" /></td>
					<td><input type="text" name="lastName"
						value="${userToEdit.lastName}" required id="lastName"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.position" /></td>
					<td><input type="text" name="position"
						value="${userToEdit.position}" required id="position"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.salary" /></td>
					<td><input type="text" name="salaryRate"
						value="${userToEdit.salaryRate}"required id="salaryRate"></td>
				</tr>
				<tr>
					<td><fmt:message key="user.role" /></td>
					<td><select name="role">

							<option value="user" ${role.roleName == 'user' ? 'selected' :''}>user</option>
							<option value="manager"${role.roleName == 'manager' ? 'selected' :''}>manager</option>
							<option value="admin" ${role.roleName == 'admin' ? 'selected' :''}>admin</option>
					</select></td>
				</tr>
				<tr>
				<td> Send email notification ? </td>
				<td><input type="radio" name="mailNotification" value="yes">  yes 
				<input type="radio" name ="mailNotification" value = "no" checked> no </td>
				</tr>
				<tr>
					
					<td align="right"><a
						href="${pageContext.request.contextPath}/admin/users"
						class="btn btn-default"><fmt:message key="button.cancel" /></a></td>
					<td>
						<button name="update" type="submit" value="update" class="btn btn-primary btn-hg">
							<fmt:message key="button.update" />
						</button>
						<button name="delete" type="submit" value="delete" class="btn btn-primary btn-hg">
							<fmt:message key="button.delete" />
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
