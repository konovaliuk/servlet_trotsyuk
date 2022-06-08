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
        <h1 class="title">Periodicals</h1>
        <c:if test="errorMessage != null">
             <p style="margin-top: 20px" class="red">${errorMessage}</p>
        </c:if>
        <c:if test="${userId != null}">
            <c:if test="${not empty periodicals}">
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
                            <c:if test="${isAdmin==false}">
                                <p style="width: 120px;" class="periodical_prop">Add to order</p>
                            </c:if>
                        </div>
                    </li>
                    <c:if test="${isAdmin==true}">
                        <form style="width: 100%;" action="/?action=createPeriodical" method="POST">
                            <li class="list_item">
                                <div style="width: 30%; text-align: left;">
                                    <div style="padding-right: 30px">
                                        <input style="margin: 0;" name="name" class="input" type="text" required>
                                    </div>
                                </div>
                                <div style="width: 20%; text-align: left;">
                                    <div style="padding-right: 30px;">
                                        <input style="margin: 0;" name="price" class="input" type="text" required>
                                    </div>
                                </div>
                                <div style="width: 40%; text-align: left; display: flex; justify-content: space-between; align-items: center;">
                                    <div>
                                        <div style="display: inline-block; width: 75px;">
                                            <input style="margin: 0;" name="periodicityValue" class="input" type="text" required>
                                        </div>
                                        <span style="display: inline-block; padding: 0 8px; font-size: 20px;">times per</span>
                                        <select class="select" name="periodicityType">
                                            <option value="WEEKLY" selected>week</option>
                                            <option value="MONTHLY">month</option>
                                        </select>
                                        <div class="triangle"></div>
                                    </div>
                                    <div>
                                        <input style="width: 40px; height: 32px; padding: 0; border-radius: 4px; font-size: 24px;" class="btn" type="submit" value="+" />
                                    </div>
                                </div>
                            </li>
                        </form>
                    </c:if>
                    <c:if test="${editPeriodical == true}">
                    <form style="width: 100%;" action="/?action=addPeriodicalsToOrder" method="POST">
                    </c:if>
                    <c:if test="${editPeriodical == null}">
                    <form style="width: 100%;" action="/?action=createOrder" method="POST">
                    </c:if>
                        <c:forEach items="${periodicals}" var="periodical">
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
                                    <c:if test="${isAdmin==true}">
                                        <a href="?action=deletePeriodical&periodicalId=${periodical.id}" class="btn btn__delete">
                                            <img src="../assets/icon-trash.svg" width="24px" height="24px" />
                                        </a>
                                    </c:if>
                                    <c:if test="${isAdmin==false}">
                                        <div style="width: 120px; display: flex; justify-content: center;">
                                            <c:if test="${editPeriodical == true}">
                                                <c:set var="inOrder" value="false" />
                                                <c:forEach var="orderPeriodical" items="${orderPeriodicals}">
                                                  <c:if test="${orderPeriodical.id == periodical.id}">
                                                    <c:set var="inOrder" value="true" />
                                                  </c:if>
                                                </c:forEach>
                                                <c:if test="${inOrder == true}">
                                                    <input class="checkbox" type="checkbox" name="periodicals" value="${periodical.id}" checked disabled />
                                                </c:if>
                                                <c:if test="${inOrder == false}">
                                                    <input class="checkbox" type="checkbox" name="periodicals" value="${periodical.id}" />
                                                </c:if>
                                            </c:if>
                                            <c:if test="${editPeriodical == null}">
                                                <input id="periodical_${periodical.id}" class="checkbox" type="checkbox" name="periodicals" value="${periodical.id}" />
                                            </c:if>
                                        </div>
                                    </c:if>
                                </div>
                            </li>
                        </c:forEach>
                        <li class="btn_container">
                            <c:if test="${isAdmin==false}">
                                <c:if test="${editPeriodical == null}">
                                    <a style="margin-right: 10px;" class="btn" href="/?action=main">Back</a>
                                    <input class="btn" type="submit" value="Create order" />
                                </c:if>
                                <c:if test="${editPeriodical == true}">
                                    <a style="margin-right: 10px;" class="btn" href="/?action=order&orderId=${order.id}">Back</a>
                                    <input class="btn" type="submit" value="Update order" />
                                </c:if>
                            </c:if>
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