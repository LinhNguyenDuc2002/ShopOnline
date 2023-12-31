<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Header</title>
                <link rel="stylesheet" href="./css/all.min.css">
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
                    integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg=="
                    crossorigin="anonymous" referrerpolicy="no-referrer" />
                <link rel="stylesheet" href="./style/buyhistory.css">
                <style>
                    .display {
                        display: none;
                    }
                </style>
            </head>

            <body>
                <%@ include file="header.jsp" %>

                    <div id="wrapper">
                        <table class="table">
                            <thead>
                                <tr class="start">
                                    <th>Mã đơn hàng</th>
                                    <th>Trạng thái</th>
                                    <th>Giá trị</th>
                                    <th>Ngày mua</th>
                                    <th>Xem</th>
                                    <th>Xác nhận</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="i" items="${sanpham}">

                                    <tr class="b_${i.getBill().getId()}" style="position: relative; ">
                                        <td class="bill_id">${i.getBill().getId()}</td>
                                        <td class="bill_status">${i.getBill().isStatus() == "true" ? "Đã nhận" : "Chưa
                                            nhận"}</td>
                                        <td class="bill_price">
                                            <div class="price">${i.getTotal()}</div>
                                        </td>
                                        <td class="order_date">${i.getBill().getOrderDate()}</td>
                                        <td class="more_info_bill">
                                            <div>
                                                <p><i class="fa fa-chevron-down"
                                                        onclick="Show('a_${i.getBill().getId()}', this, 'b_${i.getBill().getId()}')"
                                                        aria-hidden="true"></i></p>
                                            </div>
                                        </td>
                                        <td class="bill_confirm">
                                            <c:choose>
                                                <c:when
                                                    test="${i.getBill().isStatus() == 'false' && requestScope.user.role != 'ADMIN'}">
                                                    <a href="UpdateStatusController?id=${i.getBill().getId()}"
                                                        style="display: block;">
                                                        <button class="button2" style="padding: 5px 10px;
                                                        font-size:20px; 
                                                        border: none; 
                                                        outline: none; 
                                                        background-color: #d32e00;
                                                        color: white;">
                                                            Đã nhận được hàng
                                                        </button>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <p style="padding: 5px 5px;
                                                    font-size:20px; 
                                                    ">Đã nhận</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <c:forEach var="item" items="${i.getProducts()}">
                                        <tr class="a_${i.getBill().getId()}"
                                            style="display: none; transform: translateX(-1000%); width: 0; margin-bottom: 15px; ">
                                            <td colspan="6">
                                                <a href="products?action=show&id=${item.getId()}"
                                                            class="hien-thi">
                                                    <div class="product-name">
                                                        <p>${item.getProductName()}</p>
                                                    </div>
                                                    <div class="product-top"
                                                        style="width: 100px; height:  100px; margin: 0 350px; border: 1px solid black;">
                                                        
                                                            <img src="data:image/png;base64, ${Base64.encodeBase64String(item.getImage())}"
                                                                style="width: 100px; height: 100px;" alt="Picture" />
                                                        
                                                    </div>
                                                    <div class="product-price">
                                                        <p class="price">${item.getPrice()}</p>
                                                    </div>
                                                    <div>
                                                    </div>
                                                </a>
                                            </td>
                                        </tr>
                                        <br>

                                    </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%@ include file="footer.jsp" %>

                        <script src="./script/home.js"></script>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                        <script src="./script/buyhistory.js"></script>
                        <script src="./script/formatVND.js"></script>
            </body>

            </html>