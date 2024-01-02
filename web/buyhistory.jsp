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
    <link rel="stylesheet" href="./style/buyhistory.css">
</head>

<body>
    <%@ include file="header.jsp" %>

    <div id="wrapper">
        <div class="container">
            <table class="table">
                <thead class="field">
                    <tr>
                        <th>Order ID code</th>
                        <th>Total payment</th>
                        <th>Delivery address</th>
                        <th>Purchase date</th>
                        <th>Status</th>
                        <th>Confirm</th>
                    </tr>
                </thead>

                <tbody class="content">
                    <c:forEach items="${requestScope.bill}" var="i">
                        <tr class="product-list">
                            <td>${i.id}</td>
                            <td class="price">${i.getTotal()}</td>
                            <td>${i.deliveryAddress}</td>
                            <td>${i.orderDate}</td>
                            <c:choose>
                                <c:when test="${i.status eq false}">
                                    <td style="color: red; font-weight: bold;" class="status${i.id}">Payment</td>
                                    <td class="confirm"><button onclick="updateStatus(this)" idBill="${i.id}">Paid</button></td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: green; font-weight: bold;">Payment</td>
                                    <td class="confirm"><button style="background-color: darkred; color: white;" disabled>Paid</button></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>

                        <tr class="sub-container" style="display: none;">
                            <td colspan="6">
                                <c:forEach items="${i.detailOrders}" var="a">
                                    <div class="sub-product-list" link="/shop/products?action=show&id=${a.product.id}">
                                        <div class="image"><img class="anh1" src="data:image/png;base64, ${Base64.encodeBase64String(a.product.image)}" alt="picture"></div>
                                        <div class="sub-name"><p>${a.product.productName}</p></div>
                                        <div>Unit price:<p class="price number">${a.unit_price}</p></div>
                                        <div>Quantity:<p class="number">${a.quantity}</p></div>
                                        <div>Total:<p class="price number">${a.unit_price * a.quantity}</p></div>
                                    </div>
                                </c:forEach>
    
                                <div class="total-payment">
                                    <div class="sub-total">
                                        <div>Shipping method:<p class="number">${i.transport.name}</p></div>
                                        <div>Shipping fee:<p class="price number">${i.transport.price}</p></div>
                                    </div>
    
                                    <div class="sub-total">
                                        <div style="font-weight: bold;">Total payment:<p class="price number" style="color: orange; font-size: 18px;">${i.getTotal()}</p></div>
                                    </div>
                                </div>
                            </td>
                        </tr> 
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="./script/buyhistory.js"></script>
    <script src="./script/formatVND.js"></script>
</body>
</html>