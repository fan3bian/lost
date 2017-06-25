<%@page import="java.util.Date"%>
<%@page import="vo.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Date date = new Date();
		System.out.println(date);
	%>
	<%
		String dateStr = date.toString();
	%>
	<%
		String name = request.getParameter("name");
		System.out.println(name);
		Class<?> clazz =response.getClass();
		System.out.println(clazz);
		System.out.println(response instanceof HttpServletResponse);
		ServletRequest req =pageContext.getRequest();
		System.out.println(req==request);
		System.out.println(session.getId());
		System.out.println(application.getInitParameter("driver"));
		out.print(name);
		out.print("<br/>");
		out.print(name);
		out.print(this);
		out.print("<br/>");
		out.print(page);
	%>


	<form action="">
		index <input name="name" /> <input name="password" />
	</form>
	<a href="forwardServlet">forward</a>
	<a href="redirectServlet">redirectServlet</a>
</body>
</html>