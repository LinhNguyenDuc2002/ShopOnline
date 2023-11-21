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
            <h1>
                Giỏ hàng của bạn
            </h1>

            <div class="container">
                <div class="cart-content">
                    <div class="cart-content-top">
                        <table>

                            <c:if test="${error!=null }">
                                <div class="alert alert-danger" role="alert">
                                    ${error}
                                </div>
                            </c:if>
                            <c:if test="${mess!=null }">
                                <div class="alert alert-success" role="alert">
                                    ${mess}
                                </div>
                            </c:if>

                            <tr>
                                <th>Ảnh sản phẩm</th>
                                <th>Tên sản phẩm</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                                <th>Xóa</th>
                            </tr>

                            <c:forEach items="${listCart}" var="o">
                                <c:forEach items="${listProduct}" var="p">
                                    <c:if test="${o.productID == p.id}">

                                        <tr>
                                            <td><img src="${p.image}" alt=""></td>
                                            <td><p class="name">${p.name} </p></td>
                                            <td><p style="font-weight: bold;">${p.price}<sub>đ</sub></p></td>
                                            <td><input type="number" value="1" min="1" class="form-control">${o.amount}</td>
                                            <td><p style="font-weight: bold;">395.000<sub>đ</sub></p></td>
                                            <td><a href="#"><i class="fa fa-trash-o"></i></a></td>
                                        </tr>

                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                                            
                        </table>
                    </div>

                    <div class="cart-content-bottom">
                        <table>
                            <tr>
                                <td>Tạm tính</td>
                                <td><p class="gia">395.000<sub>đ</sub></p></td>
                            </tr>

                            <tr>
                                <td><b>Tổng tiền thanh toán</b></td>
                                <td><p class="tong" style="font-weight: bold;">395.000<sub>đ</sub></p></td>
                            </tr>
                        </table>

                        <div class="cart-content-right-button">
                            <button id="tt">< TIẾP TỤC MUA HÀNG</button>
                            <button id="pay">THANH TOÁN</button>
                        </div>
                    </div>
                </div>

            </div>
        </article>

        <%@ include file="footer.jsp" %>
        <script src="./script/signup.js"></script>
    </body>
</html>
