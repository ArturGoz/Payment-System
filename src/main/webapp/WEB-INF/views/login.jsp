<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
        .form-group {
            margin-bottom: 10px;
        }
        label {
            display: inline-block;
            width: 80px;
        }
    </style>
</head>
<body>
<h2>Login</h2>

<!-- Display error message if exists -->
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="form-group">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required/>
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required/>
    </div>
    <div class="form-group">
        <input type="submit" value="Login"/>
    </div>
</form>
</body>
</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<form action="/login" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>