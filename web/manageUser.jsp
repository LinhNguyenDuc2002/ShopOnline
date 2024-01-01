<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/manage.css">
</head>

<body >
    <%@ include file="header.jsp" %> 
    
    <div class="content">
        <div class="title">ACCOUNT LIST</div>
        <table>
            <tr class="field">
                <td>ID</td>
                <td>Full name</td>
                <td>Username</td>
                <td>Birthday</td>
                <td>Sex</td>
                <td>Phone number</td>
                <td>Email</td>
                <td>Status</td>
                <td>Actions</td>
            </tr>

            <c:forEach items="${requestScope.users}" var="i">
                <c:choose>
                    <c:when test="${i.status == true}">
                        <tr class="product-list active">
                            <td>${i.id}</td>
                            <td>${i.fullname}</td>
                            <td>${i.username}</td>
                            <td>${i.birthday}</td>
                            <c:choose>
                                <c:when test="${i.sex == true}">
                                    <td>female</td>
                                </c:when>
                                <c:otherwise>
                                    <td>male</td>
                                </c:otherwise>
                            </c:choose>
                            
                            <td>${i.phone}</td>
                            <td>${i.email}</td>
                            <td style="color: green;">Active</td>
                            <td class="actions">
                                <c:choose>
                                    <c:when test="${i.role == 'ADMIN'}">
                                        <p>ADMIN</p>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/shop/users?action=delete&id=${i.id}"><i class="fa-solid fa-trash"></i></a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr class="product-list inactive">
                            <td>${i.id}</td>
                            <td>${i.fullname}</td>
                            <td>${i.username}</td>
                            <td>${i.birthday}</td>
                            <c:choose>
                                <c:when test="${i.sex == true}">
                                    <td>female</td>
                                </c:when>
                                <c:otherwise>
                                    <td>male</td>
                                </c:otherwise>
                            </c:choose>
                            
                            <td>${i.phone}</td>
                            <td>${i.email}</td>
                            <td style="color: red;">Inactive</td>
                            <td class="actions">
                                <a href="/shop/users?action=delete&id=${i.id}"><i class="fa-solid fa-trash"></i></a>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/home.js"></script>

</body>

</html>