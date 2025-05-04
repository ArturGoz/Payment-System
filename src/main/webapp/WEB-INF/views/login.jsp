<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<form action="/api/login" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>