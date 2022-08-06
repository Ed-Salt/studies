<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hi there!</h1>
<p>This is my first JSP page.</p>
<p><%= new Date().toString() %></p>
<form action="HelloWorldServlet" method="post">
Enter your name: <input type="text" name="sayHelloTo" /><br />
<input type="submit" value="Say Hello!" />
Pick Greeting: <br/>
<input type="radio" name="greeting" value="Hello" /> Hello,
<input type="radio" name="greeting" value="Hi" /> Hi,
<input type="radio" name="greeting" value="Hey" /> Hey,
</form>
</body>
</html>