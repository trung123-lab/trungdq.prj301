<%-- 
    Document   : viewCart
    Created on : Feb 26, 2024, 11:33:15 AM
    Author     : trung
--%>

<%--<%@page import="java.util.Map"%>
<%@page import="trungdq.cart.CartObject"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Book Store</h1> 
        <c:if test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}" />
            <c:if test="${not empty cart.items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="grandTotal" value="0" />
                            <c:forEach var="item" items="${cart.items}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${item.value.name}</td>
                                    <td>${item.value.quantity}</td>
                                    <td>${item.value.price}</td>
                                    <td>${String.format("%.2f", item.value.quantity * item.value.price)}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.key}" />
                                    </td>
                                </tr>
                                <c:set var="grandTotal" value="${grandTotal + (item.value.quantity * item.value.price)}" />
                            </c:forEach>
                            <tr>
                                <c:url var="GoToShoppingLink" value="DispatchServlet">
                                    <c:param name="btAction" value="Go To Shopping"/>
                                </c:url>
                                <td colspan="4">
                                    <a href="${GoToShoppingLink}">Add More Books to Your Cart</a>
                                </td>
                                <td>
                                    ${String.format("%.2f",grandTotal)}
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    Name <input type="text" name="txtUsername" value=""/>
                    <input type="submit" value="CheckOut" name="btAction" />
                    <input type="hidden" name="grandTotal" value="${grandTotal}" />
                </form>
            </c:if>
            <c:if test="${empty cart.items}">
                <h2>
                    <font color="red">
                    No cart is existed!!!!!
                    </font>
                </h2>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.CART}">
            <h2>
                <font color="red">
                No cart is existed!!!!!
                </font>
            </h2>    
        </c:if>

    </body>
    <%--<body>
        <h1>Book Store</h1>
        <%
            //1. Cust goes to the cart place
            if (session != null) {
                //2. Cust takes his/her cart 
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Items will be shown 
        %> 
        
        <form action="DispatchServlet">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                int count = 0;
                for(String key : items.keySet()) {
                    %>
                <tr>    
                    <td> 
                        <%= ++count%> 
                    </td>
                    <td>
                        <%= key %>
                    </td>
                    <td>
                        <%= items.get(key) %>
                    </td>
                    <td>
                        <input type="checkbox" name="chkItem" 
                               value="<%= key %>" /> 
                    </td>
                </tr>
                <%
                }//travese items
                %>
                 <tr>
                     <td colspan="3">
                         <a href="product.html">Add More Books to Your Cart</a>       
                     </td>
                     <td>
                         <input type="submit" 
                                value="Remove Selected Items" 
                                name="btAction" />
                     </td>
                </tr>
            </tbody>
        </table>
        </form>
        <%
                        return;
                    }
                }//cart has existed
            }//session has existed
%>

        <h2>
            <font color="red">
            No cart is existed!!!!!
            </font>
        </h2>
    </body>--%>
</html>
