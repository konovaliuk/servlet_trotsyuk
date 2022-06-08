<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.DecimalFormat"%>
<%DecimalFormat df = new DecimalFormat("0.00");
 request.setAttribute("df", df);%>
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
        <h1 class="title">Order №${order.id} periodicals</h1>
        <c:if test="errorMessage != null">
             <p style="margin-top: 20px" class="red">${errorMessage}</p>
        </c:if>
        <c:if test="${userId != null}">
            <c:if test="${not empty orderPeriodicals}">
                <ul class="list">
                    <li class="list_item">
                        <div style="width: 30%; text-align: left;">
                             <p class="periodical_prop">Name</p>
                        </div>
                        <div style="width: 20%; text-align: left;">
                            <p class="periodical_prop">Price</p>
                        </div>
                        <div style="width: 40%; text-align: left; display: flex; justify-content: space-between; align-items: center;">
                            <p class="periodical_prop">Periodicity</p>
                        </div>
                    </li>
                    <c:forEach items="${orderPeriodicals}" var="periodical">
                        <li class="list_item">
                            <div style="width: 30%; text-align: left;">
                                <p class="periodical_prop">${periodical.name}</p>
                            </div>
                            <div style="width: 20%; text-align: left;">
                                <p class="periodical_prop">${df.format(periodical.price)}&#8372;</p>
                            </div>
                            <div style="width: 40%; text-align: left; display: flex; justify-content: space-between; align-items: center;">
                                <p class="periodical_prop">${periodical.periodicityValue} times per
                                    <c:if test="${periodical.periodicityType == 'WEEKLY'}">week</c:if>
                                    <c:if test="${periodical.periodicityType == 'MONTHLY'}">month</c:if>
                                </p>
                                <c:if test="${order.status == 'NOT_PAID'}">
                                    <a href="?action=removePeriodicalFromOrder&periodicalId=${periodical.id}" class="btn btn__delete">
                                        <img src="../assets/icon-trash.svg" width="24px" height="24px" />
                                    </a>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                    <li class="btn_container">
                        <a style="margin-right: 10px;" class="btn" href="/?action=userOrders">Back</a>
                        <c:if test="${order.status == 'NOT_PAID'}">
                            <a style="font-size: 24px;" href="?action=editToOrder" class="btn">+</a>
                        </c:if>
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