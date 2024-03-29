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
    <link rel="stylesheet" href="./style/category.css">
</head>

<body >
    <%@ include file="header.jsp" %> 
    
    <div id="wrapper">
        <div class="category-product">
            <div class="category-menu">
                <div class="category-header">
                    <p>Category</p>
                    <i class="category-bar fa-solid fa-chevron-down"></i>
                </div>

                <div class="category-content">
                    <a href="/shop/categories" class="category-items">All items</a>
                    <c:forEach var="i" items="${requestScope.categories}">
                        <a href="/shop/categories?id=${i.id}" onclick="displayCategoryName(event, '${i.name}')" class="category-items">${i.name}</a>
                    </c:forEach>
                </div>
            </div>

            <div class="category-wrapper">
                <div class="category-wrapper-header">
                    <c:set var="category" value="All items"/>
                    <c:forEach var="i" items="${requestScope.categories}" varStatus="loop">
                        <c:if test="${i.id eq requestScope.id}">
                            <c:set var="category" value="${i.name}"/>
                        </c:if>
                    </c:forEach>

                    <p class="category-title" idCategory="${requestScope.id}">${category}</p>

                    <div class="sort">
                        <label for="">Sort by: </label>
                        <select id="sort-by">
                            <option value="price">Price</option>
                            <option value="sold">Quantity sold</option>
                        </select>

                        <i class="fa-solid fa-arrow-up sort-price" id="sort-price"></i>
                    </div>
                </div>

                <ul class="products">   
                    <c:forEach var="i" items="${requestScope.products}">
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="hien-thi">
                                        <img src="data:image/png;base64, ${Base64.encodeBase64String(i.image)}" alt="Picture" />
                                    </a>
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="buy-now">Buy now</a>
                                </div>
                                <div class="product-info">
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="product-name">${i.getProductName()}</a>
                                    <div class="product-price">
                                        <p class="price">${i.getPrice()}</p>
                                        <p class="status">
                                            <c:choose>
                                                <c:when test="${i.available > 0}">
                                                    <span>Available: ${i.available} | Sold: ${i.sold}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color: red;">Sold out</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <c:if test="${requestScope.totalPage > 1}">
                    <div class="pages">
                        <c:forEach var="i" begin="1" end="${requestScope.totalPage}">
                            <c:choose>
                                <c:when test="${requestScope.currentPage == i-1}">
                                    <button class="index-page" page="${i-1}" onclick="loadPage(this)" style="background-color: black"></button>
                                </c:when>
                                <c:otherwise>
                                    <button class="index-page" page="${i-1}" onclick="loadPage(this)" style="background-color: lightgrey"></button>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./script/category.js"></script>
    <script src="./script/formatVND.js"></script>

</body>

</html>