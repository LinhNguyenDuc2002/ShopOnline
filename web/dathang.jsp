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
        <link rel="icon" href="https://glitch.com/favicon.ico" />

        <!--
          This is the page head - it contains info the browser uses
          Like the title, which appears on the browser tab but not inside the page
          Further down you'll see the content that displays in the page
        -->
        <title>Dat hang</title>

        <!-- The website stylesheet -->
        <link rel="stylesheet" href="./style/styledathang.css"/>

    </head>
    <body>
        <%@ include file="header.jsp" %>
        
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
                        <input type="text" placeholder="Email"><br/>
                        <input type="text" placeholder="Họ và tên"><br/>
                        <input type="text" placeholder="Số điện thoại"><br/>
                        <input type="text" placeholder="Địa chỉ"><br/>
                        <input type="text" placeholder="Tỉnh thành"><br/>
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
                    <tr>
                        <td><img class="anh-sp" src="https://bizweb.dktcdn.net/thumb/thumb/100/318/614/products/mt-4-compressed.jpg?v=1694857631083" alt="">
                            <span class="so-san-pham">1</span>
                        </td>
                        <td><p>
                                ANGEL TEE - BLACK<br/>S
                            </p></td>
                        <td><p>
                                395.000<sub>đ</sub>
                            </p></td>
                    </tr>
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

            </div>
        </div>
        
        <%@ include file="footer.jsp" %>
    </body>
</html>
