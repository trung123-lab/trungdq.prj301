<%-- 
    Document   : product
    Created on : Mar 3, 2024, 7:08:41 PM
    Author     : trung
--%>

<%--<%@page import="trungdq.tbl_Product.tbl_ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="trungdq.tbl_Product.tbl_ProductDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <c:set var="product" value="${requestScope.PRODUCT_LIST}"/>
        <c:if test="${not empty product}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Add</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${product}" varStatus="counter">
                    <form action="DispatchServlet">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                            </td>
                            <td>
                                ${dto.description}
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>
                                <input type="submit" value="Add Book to Your Cart" name="btAction" />
                                <input type="hidden" name="cboBook" value="${dto.id}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <c:url var="ViewCartLink" value="DispatchServlet">
            <c:param name="btAction" value="View Your Cart"/>
        </c:url>
        <a href="${ViewCartLink}">View Your Cart</a>
    </c:if>
    <c:if test="${empty product }">
        <h2>
            <font color="red">
            No product in here
            </font>
        </h2> 
    </c:if>
    <%--<form action="DispatchServlet">
        <select name="cboBook">
            <c:forEach var="product" items="${requestScope.PRODUCT_LIST}">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select><br/>
        <input type="submit" value="Add Book to Your Cart" name="btAction" />
        <input type="submit" value="View Your Cart" name="btAction" />
    </form>--%>
</body>
<%--<body>
    <h1>Book Store</h1>
    <% 
    tbl_ProductDAO productDAO = new tbl_ProductDAO();
    productDAO.addBook();
    List<tbl_ProductDTO> productList = productDAO.getProducts();
    %>
    <form name="ComboBook" action="DispatchServlet">
        <select name="cboBook">
    <%
         for (tbl_ProductDTO product : productList) { 
    %>    
            <option><%= product.getName() %></option>
        <% } %>
    </select><br/>
    <input type="submit" value="Add Book to Your Cart" name="btAction" />
    <input type="submit" value="View Your Cart" name="btAction" />
    </form>
</body>--%>
</html>
