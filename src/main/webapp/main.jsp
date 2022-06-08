<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/style.css">
    <title>Main</title>
</head>
<body>
    <div class="container">
        <h1 class="title">Welcome to Our Periodical Publishing</h1>
        <c:if test="${userId != null}">
            <nav class="menu">
                <ul class="menu__list">
                    <li class="menu__item">
                        <a href="?action=periodicals" class="menu__item__btn btn btn__fw">Periodicals</a>
                    </li>
                    <c:if test="${isAdmin == false}">
                        <li class="menu__item">
                            <a href="?action=userOrders" class="menu__item__btn btn btn__fw">My orders</a>
                        </li>
                        <li class="menu__item">
                            <a href="?action=subscriptions" class="menu__item__btn btn btn__fw">My subscriptions</a>
                        </li>
                    </c:if>
                    <c:if test="${isAdmin == true}">
                        <li class="menu__item">
                            <a href="?action=users" class="menu__item__btn btn btn__fw">Users</a>
                        </li>
                    </c:if>
                    <li class="menu__item">
                        <a href="?action=logout" class="menu__item__btn btn btn__fw btn__logot">Log out</a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <c:if test="${userId == null}">
            <p style="margin-top: 40px; font-size: 24px;"><a style="font-size: 24px;" href="login.jsp">Log in</a> or <a style="font-size: 24px;" href="signup.jsp">Sign up</a> to surf our site</p>
        </c:if>
    </div>
</body>
</html>