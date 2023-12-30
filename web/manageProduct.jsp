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

        <div class="sort">
            <a href="/shop/products?action=add" class="add-product-btn"><i class="fa-solid fa-plus"></i>Add a product</a>
            
            <div class="sort-right">
                <label for="">Filter:</label>
                <select id="filters" name="category" onchange="sortData()">
                    <option value="0">All items</option>
                    <c:forEach items="${requestScope.categories}" var="i">
                        <c:choose>
                            <c:when test="${i.id eq requestScope.filter}">
                                <option value="${i.id}" selected>${i.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${i.id}">${i.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

                <label for="">Sort by:</label>
                <select name="" id="sortBy" onchange="sortData()">
                    <c:forEach items="${['id', 'available', 'price', 'sold']}" var="option">
                        <option value="${option}" <c:if test="${option eq requestScope.sort}">selected</c:if>>
                            <script>
                                var optionText = "${option}";
                                var capitalizedOption = optionText.charAt(0).toUpperCase() + optionText.slice(1);
                                document.write(capitalizedOption);
                            </script>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        
        
        <div class="container">
            <table>
                <thead class="field"> 
                    <td>ID</td>
                    <td>Product name</td>
                    <td>Category</td>
                    <td>Available</td>
                    <td>Price</td>
                    <td>Sold</td>
                    <td>Description</td>
                    <td>Actions</td>
                </thead>

                <tbody class="product-body">
                    <c:forEach items="${requestScope.sanpham}" var="i">
                        <tr class="product-list active" link="/shop/products?action=show&id=${i.id}" >
                            <td>${i.id}</td>
                            <td>${i.productName}</td>
                            <td>${i.category.name}</td>
                            <td>${i.available}</td>
                            <td class="price">${i.price}</td>
                            <td>${i.sold}</td>
                            <td>${i.description}</td>
                            <td class="actions">
                                <a href="/shop/products?action=edit&id=${i.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                <a href="/shop/products?action=delete&id=${i.id}"><i class="fa-solid fa-trash"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./script/manage.js"></script>
    <script src="./script/formatVND.js"></script>

</body>

</html>