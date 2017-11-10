<%@page import="com.simplemvc.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<%
		User user = (User)request.getAttribute("user");
		
		String name = null, address = null; String age = null;
		String id = null;
		String oldName = null;
		if(user == null){
		oldName = request.getParameter("oldName");
		name = request.getParameter("oldname");
		age = request.getParameter("age");
		address = request.getParameter("address");
		id = request.getParameter("id");
		}else{
			name = user.getUserName();
			oldName = user.getUserName();
			age = user.getAge() + "";
			address = user.getAddress();
			id = user.getId() + "";
		}
		
	%>
	<%= id %>
	<br>
	<form action="update.do" method="post">
			<input type="hidden" name="id" value="<%= id %>"/>
		    <input type="hidden" name="oldName" value="<%= oldName %>"/>
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type="text" name="username" value="<%= name %>"></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><input type="text" name="age" value="<%= age %>"></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" value="<%= address %> "></td>
			</tr>
			<tr>
				<td><input type="submit" value="update" /></td>
			</tr>
		</table>
	</form>

</body>
</html>