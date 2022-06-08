<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <title>Login</title>
</head>
<body>
    <div class="container">
        <h1 class="title">Log in</h1>
        <form class="form" action="/" method="POST">
            <input type="hidden" name="action" value="login" />
            <div class="input_container">
                <p><label for="email" class="label">Email<span class="red">*</span></label></p>
                <input name="email" class="input" type="email" required value="${email}">
            </div>
            <div class="input_container">
                <p><label for="password" class="label">Password<span class="red">*</span></label></p>
                <input name="password" class="input" type="password" required>
            </div>
            <input class="btn btn__fw" type="submit" value="Log in" />
        </form>
        <p>Don't have an account? <a href="signup.jsp">Create a new one</a></p>
        <c:if test="${errorMessageLogin != null}">
            <p style="margin-top: 20px" class="red">${errorMessageLogin}</p>
        </c:if>
    </div>
</body>
</html>