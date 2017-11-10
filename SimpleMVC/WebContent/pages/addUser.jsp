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

	<%-- 用来回显，判断用户名是否重复 --%>
	<% Object message = request.getAttribute("message"); %>
	<% if(message != null){ %>
		<br>
		<font color="red"><%= message %></font>
		<br>
	
	<% } %>

	<form action="addUser.do" method="post">
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type="text" name="username" value="${param.username }"></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="age" value="${param.age }"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" value="${param.address }"></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>