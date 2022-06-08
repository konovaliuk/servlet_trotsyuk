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
            <c:if test="${not empty subscriptions}">
                <ul class="list">
                    <li class="list_item">
                        <div style="width: 30%; text-align: left;">
                             <p class="periodical_prop">Periodical Name</p>
                        </div>
                        <div style="width: 25%; text-align: left;">
                            <p class="periodical_prop">Date Start</p>
                        </div>
                        <div style="width: 25%; text-align: left;">
                            <p class="periodical_prop">Date End</p>
                        </div>
                        <div style="width: 15%; text-align: left;">
                            <p class="periodical_prop">Status</p>
                        </div>
                    </li>
                    <c:forEach items="${subscriptions}" var="subscription">
                        <li class="list_item">
                            <div style="width: 30%; text-align: left;">
                                <p class="periodical_prop">${subscription.periodical.name}</p>
                            </div>
                            <div style="width: 25%; text-align: left;">
                                <p class="periodical_prop">${subscription.dateStart}</p>
                            </div>
                            <div style="width: 25%; text-align: left;">
                                <p class="periodical_prop">${subscription.dateEnd}</p>
                            </div>
                            <div style="width: 15%; text-align: left;">
                                <c:if test="${subscription.isActive == true}">
                                    <p class="periodical_prop green">Active</p>
                                </c:if>
                                <c:if test="${subscription.isActive == false}">
                                    <p class="periodical_prop red">Expired</p>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                    <li class="btn_container">
                        <a style="margin-right: 10px;" class="btn" href="/?action=main">Back</a>
                        <a style="font-size: 24px;" href="?action=periodicals" class="btn">+</a>
                    </li>
                </ul>
            </c:if>
        </c:if>

        <c:if test="${userId == null}">
           <p style="margin-top: 40px; font-size: 24px;"><a style="font-size: 24px;" href="login.jsp">Log in</a> or <a style="font-size: 24px;" href="signup.jsp">Sign up</a> to surf our site</p>
        </c:if>
    </div>
</body>
</html>