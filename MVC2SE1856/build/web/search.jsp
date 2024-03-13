<%-- 
    Document   : search
    Created on : Feb 1, 2024, 10:00:26 AM
    Author     : trung
--%>

<%--<%@page import="trungdq.account.AccountDTO"%>
<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
        <c:url var="LogOutLink" value="DispatchServlet">
            <c:param name="btAction" value="LogOut"/>
        </c:url>
        <a href="${LogOutLink}">Log Out</a>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /><br/> 
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <c:set var ="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/> 
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>    
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="ckAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="txtSearchValue" 
                                           value="${searchValue}" />
                                </td>
                            </tr>
                        </form>  
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result }">
            <h2>
                <font color="red">
                No record is matched
                </font>
            </h2> 
        </c:if>
    </c:if>
</body>
<%--
<%
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        Cookie newestCookie = cookies[cookies.length - 1];
        String username = newestCookie.getName();
%>
<font color="red">
Welcome, <%= username%>
</font>
<%
    }//end cookies has existed
%>
<h1>Search Page</h1>
<form action="DispatchServlet">
    Search Value <input type="text" name="txtSearchValue" 
                        value="<%= request.getParameter("txtSearchValue")%>" /><br/> 
    <input type="submit" value="Search" name="btAction" /><br/>
</form>
<br/>
<%
    String searchValue = request.getParameter("txtSearchValue");
    if (searchValue != null) {
        List<AccountDTO> result
                = (List<AccountDTO>) request.getAttribute("SEARCH_RESULT");
        if (result != null) { //search found
%>
<table border="1">
    <thead>
        <tr>
            <th>No.</th>
            <th>Username</th>
            <th>Password</th>
            <th>Full name</th>
            <th>Role</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
    </thead>
    <tbody>
        <%
            int count = 0;
            for (AccountDTO dto : result) {
                String urlRewriting = "DispatchServlet"
                        + "?btAction=delete" //
                        + "&pk=" + dto.getUsername() //lay primary key de query o database
                        + "&lastSearchValue=" + searchValue; //get parameter tu search de search lai
                //khong dc co khoang trang
%>
    <form action="DispatchServlet" method="POST">

            <tr>
                <td>
                    <%= ++count%>
                    .</td>
                <td>
                    <%= dto.getUsername()%>
                    <input type="hidden" name="txtUsername" 
                           value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" 
                           value="<%= dto.getPassword()%>" /> 
                </td>
                <td>
                    <%= dto.getFullName()%>
                </td>
                <td>
                    <input type="checkbox" name="ckAdmin" value="ON" 
                           <%
                               if (dto.isRole()) {
                           %>
                           checked = "checked"
                           <%
                               }//end role admin
%>
                           />
                </td>
                <td>
                    <a href="<%= urlRewriting%>">Delete</a>
                </td>
                <td>
                    <input type="submit" value="Update" name="btAction" />
                    <input type="hidden" name="txtSearchValue" 
                           value="<%= searchValue%>" />

                </td>
            </tr>
        </form>    
        <%
            }//get each DTO from result
        %>   

    </tbody> 
</table>

<%
} else { // search not found 
%>
<h2>
    <font color="red">
    No record is matched
    </font>
</h2>
<%
        } // search not found
    }//search value is request directly
%>
--%>
</html>
