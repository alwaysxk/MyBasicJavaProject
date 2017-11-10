<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.simplemvc.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.alwaysxk.cn/mytag/core" prefix="py" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% List<User> list = new ArrayList<>();
		list.add(new User("hello"));
		list.add(new User("xk"));
		list.add(new User("jdjs"));
		request.setAttribute("users", list);
	%>
	-----------------------------------------------
	
	<% session.setAttribute("xk", "xk1"); %>
	
	<!--  <a href="n.jsp">hello</a> -->
	
	<py:forEach items="${users }" var="user">
		-- ${ user.userName }
	</py:forEach>
	
	
	
</body>
</html>