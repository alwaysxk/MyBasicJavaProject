<%@page import="com.simplemvc.domain.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		
	<form action = "query.do" mathod = "post">
		<table>
			<tr>
				<td>UserName:</td>
				<td> <input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td> <input type="text" name="age"/></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td> <input type="text" name="address"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Query"></td>
				<td><a href="addUser.jsp">add</a></td>
			</tr>
			
		</table>
	</form>
	
	<c:if test="${! empty requestScope.users}">
		<hr><br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>Id</th>
				<th>UserName</th>
				<th>Age</th>
				<th>Address</th>
				<th>Update\Delete</th>
			</tr>
		<c:forEach items="${requestScope.users }" var="user">
		<tr>
			<td>${user.id }</td>
			<td>${user.userName }</td>
			<td>${user.age }</td>
			<td>${user.address }</td>
			<td>
				<c:url value="/edit.do" var="editurl">
					<c:param name="id" value="${user.id }">
					</c:param>
				</c:url>
				<a href="${editurl }">update</a>
				<c:url value="/delete.do" var="deleteurl">
					<c:param name="id" value="${user.id }"></c:param>
				</c:url>
				<a href="${deleteurl }">delete</a>
			</td> 
		</tr>
		</c:forEach>
		</table>
	
	</c:if>

</body>

</html>