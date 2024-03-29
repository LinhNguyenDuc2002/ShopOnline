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
    <link rel="stylesheet" href="./style/home.css">
</head>

<body >
    <%@ include file="header.jsp" %> 
    <div class="container">
        <div class="gallery-display-area">
            <div class="gallery-wrap">
                <div >
                    <img class="image" src="./image/anh3.png" alt="">
                </div>
                <div >
                    <img class="image" src="./image/anh1.png" alt="">
                </div>
                <div >
                    <img class="image" src="./image/anh4.png" alt="">
                </div>
                <div >
                    <img class="image" src="./image/anh2.png" alt="">
                </div>
                <div >
                    <img class="image" src="./image/6.png" alt="">
                </div>
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${!empty requestScope.key}">
            <span class="alert-search">Results for '${requestScope.key}'</span>
        </c:when>
    </c:choose>
    <div id="wrapper">
        <ul class="products">   
            <c:forEach var="i" items="${requestScope.sanpham}">
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

        <div class="products-button">
            <a href="/shop/categories" class="button1">All product</a>
        </div>
    </div>
    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./script/formatVND.js"></script>
    <script src="./script/home.js"></script>

</body>

</html>