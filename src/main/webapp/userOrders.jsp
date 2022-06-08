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
        <h1 class="title">My orders</h1>
        <c:if test="errorMessage != null">
             <p style="margin-top: 20px" class="red">${errorMessage}</p>
        </c:if>
        <c:if test="${userId != null}">
            <ul class="list order_list">
                <li class="list_item">
                    <div style="width: 85%; display: flex; align-items: center; justify-content: space-between;">
                        <div style="width: 15%; text-align: left;">
                             <p class="periodical_prop">Id</p>
                        </div>
                        <div style="width: 25%; text-align: left;">
                            <p class="periodical_prop">Creation Date</p>
                        </div>
                        <div style="width: 20%; text-align: left;">
                            <p class="periodical_prop">Total Price</p>
                        </div>
                        <div style="width: 20%;">
                            <p class="periodical_prop">Status</p>
                        </div>
                    </div>
                </li>
                <c:forEach items="${orders}" var="order">
                    <li class="list_item">
                        <a href="/?action=order&orderId=${order.id}" style="display: block; width: 85%; display: flex; align-items: center; justify-content: space-between;">
                             <div style="width: 15%; text-align: left;">
                                 <p class="periodical_prop">${order.id}</p>
                             </div>
                             <div style="width: 25%; text-align: left;">
                                 <p class="periodical_prop">${order.creationDate}</p>
                             </div>
                             <div style="width: 20%; text-align: left;">
                                 <p class="periodical_prop">${df.format(order.totalPrice)}&#8372;</p>
                             </div>
                            <div style="width: 20%; text-align: left; display: flex; justify-content: space-between; align-items: center;">
                                <c:if test="${order.status == 'NOT_PAID'}"><p class="periodical_prop">Not Paid</p></c:if>
                                <c:if test="${order.status == 'PAID'}"><p class="periodical_prop">Paid</p></c:if>
                            </div>
                        </a>
                        <c:if test="${order.status == 'NOT_PAID'}">
                            <div style="width: 15%; display: flex; justify-content: space-between; align-items: center;">
                                <a href="?action=payOrder&orderId=${order.id}" style="height: 32px; padding: 5px 10px; border-radius: 4px; " class="btn">Pay</a>
                                <a href="?action=deleteOrder&orderId=${order.id}" class="btn btn__delete">
                                    <img src="../assets/icon-trash.svg" width="24px" height="24px" />
                                </a>
                            </div>
                        </c:if>
                    </li>
                </c:forEach>
                <li class="btn_container">
                    <a style="margin-right: 10px;" class="btn" href="/?action=main">Back</a>
                </li>
            </ul>
        </c:if>

        <c:if test="${userId == null}">
           <p style="margin-top: 40px; font-size: 24px;"><a style="font-size: 24px;" href="login.jsp">Log in</a> or <a style="font-size: 24px;" href="signup.jsp">Sign up</a> to surf our site</p>
        </c:if>
    </div>
</body>
</html>