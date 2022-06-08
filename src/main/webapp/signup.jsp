<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <title>Sign up</title>
</head>
<body>
    <div class="container">
        <h1 class="title">Sign up</h1>
        <form class="form" action="/" method="POST">
            <input type="hidden" name="action" value="signup" />
            <div class="input_container">
                <p><label for="firstName"  class="label">First name<span class="red">*</span></label></p>
                <input name="firstName" class="input" type="text" required value="${firstName}">
            </div>
            <div class="input_container">
                <p><label for="lastName"  class="label">Last name<span class="red">*</span></label></p>
                <input name="lastName" class="input" type="text" required value="${lastName}">
            </div>
            <div class="input_container">
                <p><label for="email"  class="label">Email<span class="red">*</span></label></p>
                <input name="email" class="input" type="email" required value="${email}">
            </div>
            <div class="input_container">
                <p><label for="password"  class="label">Password<span class="red">*</span></label></p>
                <input name="password" class="input" type="password" required>
            </div>
            <div class="input_container">
                <p><label for="passwordRepeat"  class="label">Repeat password<span class="red">*</span></label></p>
                <input name="passwordRepeat" class="input" type="password" required>
            </div>
            <input class="btn btn__fw" type="submit" value="Sign up" />
        </form>
        <p>Already have an account? <a href="login.jsp">Log in</a></p>
        <c:if test="${errorMessageSignup != null}">
            <p style="margin-top: 20px" class="red">${errorMessageSignup}</p>
        </c:if>
    </div>
</body>
</html>