<%-- 
    Document   : dathang
    Created on : Oct 19, 2023, 9:46:21 PM
    Author     : thanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- 
          This is an HTML comment
          You can write text in a comment and the content won't be visible in the page
        -->

        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Dat hang</title>

        <!-- The website stylesheet -->
        <link rel="stylesheet" href="./style/styledathang.css"/>

    </head>
    <body>
        <%@ include file="header.jsp" %>

        <<form action="order" method="post">
            <div class="order">

                <div class="logo-header">
                    <a href="/"><img class="logo" src="https://bizweb.dktcdn.net/100/318/614/themes/667160/assets/checkout_logo.png?1681444077990" alt=""></a>
                </div>

                <div class="order-left">
                    <div class="main-order-left">
                        <h2>
                            Thông tin nhận hàng
                        </h2>
                        <div class="nhap">
                            
                            
                            <input type="text" placeholder="Email" value="${requestScope.user.getEmail()}"><br/>
                            <input type="text" placeholder="Họ và tên" value="${requestScope.user.getFullname()}"><br/>
                            <input type="text" placeholder="Số điện thoại" value="${requestScope.user.getPhone()}"><br/>
                            <input type="text" name="deliveryAddress" placeholder="Địa chỉ" value="${requestScope.user.getDetail_address()}" required><br/>
                            <input type="text" placeholder="Tỉnh thành" value="${requestScope.user.getCity()}"><br/>
                            <input type="text" placeholder="Quận huyện"><br/>
                            <input type="text" placeholder="Phường xã"><br/>
                            <textarea id="w3review" name="w3review" rows="4" cols="50" placeholder="Ghi chú (tùy chọn)"></textarea><br/>
                            
                        </div>
                    </div>

                </div>

                <div class="order-right">
                    <h2>
                        Đơn hàng (2 sản phẩm)
                    </h2>

                    <table class="san-pham">
                        <c:forEach items="${requestScope.cart}" var="i">
                            <tr>
                                <td><img class="anh-sp" src="data:image/jpg;base64,${Base64.encodeBase64String(i.product.image)}" alt="">
                                    <span class="so-san-pham">${i.quantity}</span>
                                </td>
                                <td><p>
                                        ${i.product.productName}
                                    </p></td>
                                <td><p>
                                        ${i.product.price * i.quantity}<sub>đ</sub>
                                    </p></td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="ma-giam-gia">
                        <input type="text" placeholder="Nhập mã giảm giá">
                        <button class="ap-dung">Áp dụng</button>
                    </div>

                    <table class="tam-tinh">
                        <tr>
                            <td>Tạm tính</td>
                            <td><p>
                                    395.000<sub>đ</sub>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>Phí vận chuyển</td>
                        </tr>
                    </table>

                    <table class="dat-hang">
                        <tr>
                            <td>Tổng cộng</td>
                            <td><p class="tong">
                                    395.000<sub>đ</sub>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="#" class="previous-link">
                                    <i class="previous-link__arrow">❮</i>
                                    <span class="previous-link__content">Quay về giỏ hàng</span>
                                </a>
                            </td>
                            <td>
                                <button class="dat-hang-button">Đặt hàng</button>
                            </td>
                        </tr>
                    </table>
                    <div class="message">
                        <% 
                        String message = (String) request.getAttribute("message");
                        if (message != null && !message.isEmpty()) {
                        %>
                        <p><%= message %></p>
                        <% 
                        }
                        %>
                    </div>
                </div>
            </div>
        </form>
        <%@ include file="footer.jsp" %>
    </body>
</html>
