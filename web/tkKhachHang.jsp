<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/manage.css">
    <link rel="stylesheet" href="./style/thongke.css">
</head>

<body >
    <%@ include file="header.jsp" %> 
    
    <div class="content">
        <div class="title">THỐNG KÊ KHÁCH HÀNG</div>

        <div class="time">
            <div class="time-filter">
                <label for="">Start at:</label>
                <input type="date" name="start" id="startAt" value="${requestScope.start}" onchange="find()">
            </div>
            
            <div class="time-filter">
                <label for="">End at:</label>
                <input type="date" name="end" id="endAt" value="${requestScope.end}" onchange="find()">
            </div>
        </div>

        <table>
            <thead class="field">
                <td>STT</td>
                <td>Full name</td>
                <td>Birthday</td>
                <td>Address</td>
                <td>Phone number</td>
                <td>Total amount</td>
                <td>Joined date</td>
            </thead>

            <tbody class="customer">
                <c:set var="stt" value="1" />
                <c:forEach items="${requestScope.users}" var="i">
                    <tr class="product-list active" link="/shop/bill?id=${i.id}">
                        <td>${stt}</td>
                        <td>${i.fullname}</td>
                        <td>${i.birthday}</td>
                        <td>${i.city} - ${i.country}</td>
                        <td>${i.phone}</td>
                        <td class="price">${i.totalAmount}</td>
                        <td>${i.join_date}</td>
                    </tr>
                    <c:set var="stt" value="${stt + 1}" />
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="./script/formatVND.js"></script>
    <script src="./script/thongke.js"></script>
</body>
</html>