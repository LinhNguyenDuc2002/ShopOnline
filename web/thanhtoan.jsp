<%-- 
    Document   : thanhtoan
    Created on : Oct 19, 2023, 9:57:39 PM
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
        <title>Shop online</title>

        <!-- The website stylesheet -->
        <link rel="stylesheet" href="./style/stylett.css" />

        <!-- The website JavaScript file -->
        <script src="/script.js" defer></script>
    </head>
    <body>

        <%@ include file="Header.jsp" %>

        <article>
            <div class="content-title">
                <p id="content-title">Cart</p>
            </div>

            <div class="container">
                <div class="cart-content">
                    <div class="cart-content-top">
                        <table>
                            <tr>
                                <th>Ảnh sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                                <th>Xóa</th>
                            </tr>

                            <tr>
                                <td><img src="https://bizweb.dktcdn.net/thumb/compact/100/318/614/products/mt-4-compressed.jpg" alt=""></td>
                                <td><p>ANGEL TEE - BLACK <br/> S </p></td>
                                <td><p style="font-weight: bold;">395.000<sub>đ</sub></p></td>
                                <td><input type="number" value="1" min="1"></td>
                                <td><p style="font-weight: bold;">395.000<sub>đ</sub></p></td>
                                <td><span>X</span></td>
                            </tr>
                        </table>
                    </div>

                    <div class="cart-content-bottom">
                        <table>
                            <tr>
                                <td>Tạm tính</td>
                                <td><p>395.000<sub>đ</sub></p></td>
                            </tr>

                            <tr>
                                <td>Tổng tiền thanh toán</td>
                                <td><p style="font-weight: bold;">395.000<sub>đ</sub></p></td>
                            </tr>
                        </table>

                        <div class="cart-content-right-button">
                            <button id="tt">TIẾP TỤC MUA HÀNG</button>
                            <button id="pay">THANH TOÁN</button>
                        </div>
                    </div>
                </div>

            </div>
        </article>

        <%@ include file="Footer.jsp" %>
        <script src="./script/signup.js"></script>
    </body>
</html>
