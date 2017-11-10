<%@page import="com.simplemvc.domain.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<%
		List<User> users = (List<User>)request.getAttribute("users");
		if(users != null && users.size() > 0){
	
	%>
		<hr><br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>Id</th>
				<th>UserName</th>
				<th>Age</th>
				<th>Address</th>
				<th>Update\Delete</th>
			</tr>
			
			<%
				for(User user : users){
			%>
				<tr>
					<td><%= user.getId() %></td>
					<td><%= user.getUserName() %></td>
					<td><%= user.getAge() %></td>
					<td><%= user.getAddress() %>
					<td>
						<a href="edit.do?id=<%=user.getId() %>">update</a>
						<a href="delete.do?id=<%= user.getId() %>">delete</a>
					</td>
				</tr>
			
			<% 
				}
			%>
			
			<tr>
		</table>
	<%
		}
	%>
</body>

</html>