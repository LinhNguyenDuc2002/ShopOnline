<%-- 
    Document   : thanhtoan
    Created on : Oct 19, 2023, 9:57:39 PM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- 
          This is an HTML comment
          You can write text in a comment and the content won't be visible in the page
        -->

        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="https://glitch.com/favicon.ico" />

        <!--
          This is the page head - it contains info the browser uses
          Like the title, which appears on the browser tab but not inside the page
          Further down you'll see the content that displays in the page
        -->
        <title>Shop online</title>

        <!-- The website stylesheet -->
        <link rel="stylesheet" href="./style/stylett.css" />

        <!-- The website JavaScript file -->
        <script src="/script.js" defer></script>
    </head>
    <body>

        <%@ include file="header.jsp" %>

        <article>
            <div class="content-title">
                <p id="content-title">Cart</p>
            </div>

            <%-- Kiểm tra nếu giỏ hàng không có sản phẩm thì không chuyển tới trang dathang.jsp --%>
            <c:if test="${empty requestScope.cart}">
                <div class="container empty-cart">
                    <p style="color: red;">Your shopping cart is empty!</p></br>
                    <a id="tt" href="/shop/home">TIẾP TỤC MUA HÀNG</a></br>
                </div>
            </c:if>
            
            <c:if test="${not empty requestScope.cart}">

                <div class="container">
                    <div class="cart-content">
                        <div class="cart-content-top">
                            <table>
                                <thead>
                                    <th>Ảnh sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Đơn giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th>Xóa</th>
                                </thead>
                                    
                                <c:set var="totalPrice" value="0" />
                                <c:forEach items="${requestScope.cart}" var="i">
                                    <tr class="product-list" link="/shop/products?action=show&id=${i.product.id}">
                                        <td><img class="anh1" src="data:image/jpg;base64,${Base64.encodeBase64String(i.product.image)}" alt="picture"></td>
                                        <td><p>${i.product.productName}</p></td>
                                        <td><p style="font-weight: bold;" class="price">${i.product.price}</p></td>
                                        <!-- <td><input type="number" value="${i.quantity}" min="1" max="${i.product.available}" readonly></td> -->
                                        <td><p>${i.quantity} products</td>
                                        <td><p style="font-weight: bold;" class="price">${i.product.price * i.quantity}</p></td>
                                        <td><a href="/shop/carts?action=delete&id=${i.id}"><i class="fa-solid fa-trash"></i></a></td>
                                    </tr>

                                    <c:set var="totalPrice" value="${totalPrice + i.product.price * i.quantity}" />
                                </c:forEach>
                                
                            </table>
                        </div>

                        <div class="cart-content-bottom">
                            <table>
                                <tr>
                                    <td>Tạm tính</td>
                                    <td><p class="price">${totalPrice}</p></td>
                                </tr>

                                <tr>
                                    <td>Tổng tiền thanh toán</td>
                                    <td><p style="font-weight: bold;" class="price">${totalPrice}</p></td>
                                </tr>
                            </table>

                            <div class="cart-content-right-button">
                                <a id="tt" href="/shop/home">TIẾP TỤC MUA HÀNG</a>
                                <a id="pay" href="/shop/order">ĐẶT HÀNG</a>
                            </div>
                        </div>
                    </div>

                </div>
            </c:if>
        </article>

        <%@ include file="footer.jsp" %>
        <script src="./script/manage.js"></script>
        <script src="./script/formatVND.js"></script>
    </body>
</html>
