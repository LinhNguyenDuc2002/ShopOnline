<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.io.*, model.* ,DAO.*, java.sql.Date"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <!-- <link rel="stylesheet" href="./style/home.css"> -->
    <link rel="stylesheet" href="./style/product-style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />

</head>

<body>
    <%@ include file="header.jsp" %>
    
    <section class="san-pham" <c:if test="${requestScope.user.role == 'ADMIN'}">style="pointer-events: none;"</c:if>>
        <c:if test="${requestScope.user.role == 'ADMIN'}">
            <div class="view-mode"><i class="fa-solid fa-eye"></i>View mode</div>
        </c:if>

        <form action="/shop/carts" method="POST">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="id" value="${requestScope.sanphamchitiet.id}">

            <div class="product-content">
                <div class="product-left">
                    <div><img class="anh1" src="data:image/jpg;base64,${Base64.encodeBase64String(requestScope.sanphamchitiet.image)}" alt="picture"></div>
    
    
                </div>
                <div class="product-right">
                    <h1>${requestScope.sanphamchitiet.getProductName()}</h1>

                    <p class="category">Category: <span>${requestScope.sanphamchitiet.category.name}</span></p>
    
                    <p class="text"> 
                        <c:choose>
                            <c:when test="${requestScope.sanphamchitiet.available > 0}">
                                <span id="available">Available: ${requestScope.sanphamchitiet.available}</span>
                            </c:when>
    
                            <c:otherwise>
                                <span style="color: red;">Sold out</span>
                            </c:otherwise>
                        </c:choose>
                        | Sold: ${requestScope.sanphamchitiet.sold}
                    </p>
    
                    <p class="price text main">${requestScope.sanphamchitiet.getPrice()}</p>    
                    
                    <div class="product-quantity">
                        <div class="quantity">
                            <p>Quantity</p>
                            <div style="position: relative; display: flex; width: fit-content; margin-top: 10px;">
                                <input type="number" value="1" min="1" max="${requestScope.sanphamchitiet.available}" name="quantity" required>
                            </div>
                        </div>  

                        <p class="error-quantity">Out of order</p>
                    </div>
                    
                    <button type="submit" class="button1" style="margin-top: 30px; margin-left: 0; width: fit-content;">ADD TO CART</button>
                </div>
            </div>
        </form>
        
        <!-- MÔ TẢ -->
        <div class="mo-ta">
                 <h2>DESCRIPTION</h2>
                 <div class="khung-mo-ta">
                     <p>${requestScope.sanphamchitiet.getDescription()}<p>
                 </div>
        </div>
        <!-- MÔ TẢ-->
        <div>
            <h2>Products of the same type</h2>
            <div class="swiper">
                <ul class="swiper-wrapper products">
                    <c:forEach var="i" items="${requestScope.list_category}">
                        <li class="swiper-slide">
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="hien-thi">
                                        <img src="data:image/png;base64, ${Base64.encodeBase64String(i.image)}" alt="Picture" />
                                    </a>
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="buy-now">BUY NOW</a>
                                </div>

                                <div class="product-info">
                                    <a href="/shop/products?action=show&id=${i.getId()}" class="product-name">${i.getProductName()}</a>
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
                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>
        </div>
    </section>
    <%@ include file="footer.jsp" %>


    <script src="./script/product.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
    <script src="./script/product-detail.js"></script>
    <script src="./script/formatVND.js"></script>

</body>
</html>