<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Header</title>
        
    </head>
    <%@ include file="header.jsp" %>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/home.css">

    <body >
        
        <div id="wrapper">
            <ul style= " "class="products">   
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
                                <div class="product-price">${i.getPrice()}</div>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <button class="button1">All product</button>
        </div>
        <%@ include file="footer.jsp" %>

    </body>

</html>