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
        <div class="title">PRODUCT LIST</div>
        <a href="/shop/products?action=add" class="add-product">Add a product</a>
        <table>
            <tr>
                <td>ID</td>
                <td>Product name</td>
                <td>Category</td>
                <td>Available</td>
                <td>Price</td>
                <td>Sold</td>
                <td>Description</td>
                <td>Actions</td>
            </tr>

            <c:forEach items="${requestScope.sanpham}" var="i">
                <tr class="product-list">
                    <td>${i.id}</td>
                    <td>${i.productName}</td>
                    <td>${i.category.name}</td>
                    <td>${i.available}</td>
                    <td>${i.price}</td>
                    <td>${i.sold}</td>
                    <td>${i.description}</td>
                    <td>
                        <a href="/shop/products?action=edit&id=${i.id}">Update</a>
                        <a href="/shop/products?action=delete&id=${i.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/home.js"></script>

</body>

</html>