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

<body>
    <%@ include file="header.jsp" %>
    
    <div id="wrapper">
        <ul class="products">   
            <c:forEach var="i" items="${requestScope.sanpham}">
                <li>
                    <div class="product-item">
                        <div class="product-top">
                            <a href="" class="hien-thi">
                                <img src="data:image/png;base64, ${Base64.encodeBase64String(i.image)}" alt="Picture" />
                            </a>
                            <a href="/shop/products?action=edit&id=${i.getId()}" class="buy-now">Edit</a>
                        </div>
                        <div class="product-info">
                            <a href="" class="product-name">${i.getProductName()}</a>
                            <div class="product-price">
                                <p class="price">${i.getPrice()}</p>
                                <p class="status">
                                    <c:choose>
                                        <c:when test="${i.available > 0}">
                                            <span>Available: ${i.available}</span>
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

        <div class="pages">
            <c:forEach var="i" begin="1" end="${requestScope.totalPage}">
                <c:choose>
                    <c:when test="${requestScope.currentPage == i-1}">
                        <a class="index-page" href="/shop/home?page=${i}" style="background-color: black"></a>
                    </c:when>
                    <c:otherwise>
                        <a class="index-page" href="/shop/home?page=${i}" style="background-color: lightgrey"></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <div class="products-button">
            <a href="/shop/categories" class="button1">All product</a>
        </div>
    </div>
    <%@ include file="footer.jsp" %>

    <script src="./script/formatVND.js"></script>
</body>

</html>