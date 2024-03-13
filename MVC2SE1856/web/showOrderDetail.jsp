<%-- 
    Document   : index
    Created on : Mar 12, 2024, 3:21:29 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Success</title>
    </head>
    <body>
        <h1>Checkout Successful!</h1>
        <c:set var="user" value="${sessionScope.USER_ORDER}" />
        <c:if test="${not empty user}">
            <c:if test="${not empty sessionScope.PRODUCT_ITEM}">
                <table border="1">
                    <thead>
                        <tr>
                            <th colspan="5">Order Information</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="5" style="text-align: center;">
                                Order ID: ${user.id}<br>
                                Customer Name: ${user.name}<br>
                                Order Date: ${user.date}
                            </td>   
                        </tr>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        <c:forEach var="item" items="${sessionScope.PRODUCT_ITEM}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.name}</td>
                                <td>${item.unitPrice}</td>
                                <td>${item.quantity}</td>
                                <td>${String.format("%.2f",item.total)}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4" style="text-align: center;">
                                Total Product
                            </td>
                            <td>
                                ${String.format("%.2f",user.total)}
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty sessionScope.PRODUCT_ITEM}">
                <h2>
                    <font color="red">
                    No Product List is existed!!!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.USER_ORDER}">
            <h2>
                <font color="red">
                No User Order is existed!!!!!
                </font>
            </h2>
        </c:if>
        <p>Thank you for your order.</p>
        <c:url var="GoToShoppingLink" value="DispatchServlet">
            <c:param name="btAction" value="Go To Shopping"/>
        </c:url>
        <a href="${GoToShoppingLink}">Go To Shopping</a>
    </body>
</html>
