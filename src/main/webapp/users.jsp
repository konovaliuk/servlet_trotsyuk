<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <title>Periodicals</title>
</head>
<body>
    <div class="container">
        <h1 class="title">Subscriptions</h1>
        <c:if test="${userId != null}">
            <c:if test="${not empty users}">
                <ul class="list">
                    <li class="list_item">
                        <div style="width: 25%; text-align: left;">
                             <p class="periodical_prop">First Name</p>
                        </div>
                        <div style="width: 25%; text-align: left;">
                            <p class="periodical_prop">Last Name</p>
                        </div>
                        <div style="width: 30%; text-align: left;">
                            <p class="periodical_prop">Email</p>
                        </div>
                        <div style="width: 15%; text-align: left;">
                            <p class="periodical_prop">Role</p>
                        </div>
                    </li>
                    <c:forEach items="${users}" var="user">
                        <li class="list_item">
                            <div style="width: 25%; text-align: left;">
                                <p class="periodical_prop">${user.firstName}</p>
                            </div>
                            <div style="width: 25%; text-align: left;">
                                <p class="periodical_prop">${user.lastName}</p>
                            </div>
                            <div style="width: 30%; text-align: left;">
                                <p class="periodical_prop">${user.email}</p>
                            </div>
                            <div style="width: 15%; text-align: left;">
                                <p class="periodical_prop">${user.role}</p>
                            </div>
                        </li>
                    </c:forEach>
                    <li class="btn_container">
                        <a style="margin-right: 10px;" class="btn" href="/?action=main">Back</a>
                    </li>
                    </form>
                </ul>
            </c:if>
        </c:if>

        <c:if test="${userId == null}">
           <p style="margin-top: 40px; font-size: 24px;"><a style="font-size: 24px;" href="login.jsp">Log in</a> or <a style="font-size: 24px;" href="signup.jsp">Sign up</a> to surf our site</p>
        </c:if>
    </div>
</body>
</html>