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
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="i" items="${sanpham}">
                                    <tr class="b_${i.getBill().getId()}" style="position: relative; ">
                                        <td>${i.getBill().getId()}</td>
                                        <td>${i.getBill().isStatus()}</td>
                                        <td class="price">i.getTotal()</td>
                                        <td>${i.getBill().getOrderDate()}</td>
                                        <td>
                                            <div>
                                                <p><i class="fa fa-chevron-down"
                                                        onclick="Show('a_${i.getBill().getId()}', this, 'b_${i.getBill().getId()}')"
                                                        aria-hidden="true"></i></p>
                                            </div>
                                        </td>
                                    </tr>
                                    <c:forEach var="item" items="${i.getProducts()}">

                                        <tr class="a_${i.getBill().getId()}"
                                            style="display: none; transform: translateX(-1000%); width: 0;">


                                            <td>
                                                <div onclick="close('a_${i.getBill().getId()}')"
                                                    style="margin-left: 200px;">
                                                    <i class="fa fa-window-close" aria-hidden="true"></i>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="product-top"
                                                    style=" width: 100px; height:  100px; margin: 0 350px; border: 1px solid black;">
                                                    <a href="" class="hien-thi">
                                                        <img src="data:image/png;base64, ${Base64.encodeBase64String(item.getImage())}"
                                                            style="width: 100px; height: 100px;" alt="Picture" />
                                                    </a>
                                                    <a href="/shop/products?action=show&id=${i.getBill().getId()}"
                                                        class="buy-now">${i.getBill().getDeliveryAddress()}</a>
                                                </div>
                                            </td>
                                            <td>
                                                <div style="margin-right: 420px;">
                                                    <p>${item.getProductName()}</p>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="product-price">
                                                    <p class="price">${item.getPrice()}</p>
                                                    <p class="status" style="margin: 0 100px;">
                                                        <c:choose>
                                                            <c:when test="${item.getAvailable() > 0}">
                                                                <span>Available: ${item.getAvailable()}</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span style="color: red;">Sold out</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                            </td>
                                            <td>
                                                <div>
                                                    <button
                                                        style="padding: 2px 30px; border: none; outline: none; background-color: #00d2d3">
                                                        Mua lại
                                                    </button>
                                                </div>

                                            </td>

                                        </tr>

                                    </c:forEach>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <%@ include file="footer.jsp" %>

                        <script src="./script/home.js"></script>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                        <script type="text/javascript">
                            var a = 0;
                            function Show(type, element, type2) {
                                a += 1;
                                let sum = a;
                                const displays = document.querySelector("." + type + "");
                                const typeClass = document.querySelector("." + type2 + "");
                                if (sum % 2 != 0) {
                                    if (element.classList.contains("fa-chevron-down")) {
                                        element.classList.remove("fa-chevron-down");
                                        element.classList.add("fa-chevron-up");
                                    }
                                    displays.style.display = "block";
                                    displays.style.transition = "1.5s ease-in-out";

                                    displays.style.transform = "translateX(0)";

                                    console.log(displays);
                                } else if (sum % 2 == 0) {
                                    if (element.classList.contains("fa-chevron-up")) {
                                        element.classList.remove("fa-chevron-up");
                                        element.classList.add("fa-chevron-down");
                                    }
                                    //                displays.style.display = "none";
                                    //                console.log("Đã đóng");

                                    close(type);
                                    typeClass.style.height = "auto";
                                    displays.style.transform = "translateX(-200%)";

                                }
                                console.log(sum);
                            }

                            //        function Show(type){
                            //            const displays = document.querySelector("."+type+"");
                            //            displays.style.display = "block";
                            //                console.log(displays);
                            //        }
                            //        
                            function close(types) {
                                const displays1s = document.querySelector("." + types + "");
                                displays1s.style.display = "none";
                                console.log(types);
                            }

                        </script>
            <script src="./script/formatVND.js"></script>
            </body>

            </html>