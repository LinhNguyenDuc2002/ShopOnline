<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
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

        <table>
            <tr class="field">
                <td>STT</td>
                <td>Full name</td>
                <td>Birthday</td>
                <td>Total amount</td>
                <td>Joined date</td>
            </tr>
            
            <c:set var="stt" value="1" />
            <c:forEach items="${requestScope.users}" var="i">
                <tr class="product-list">
                    <td>${stt}</td>
                    <td>${i.fullname}</td>
                    <td>${i.birthday}</td>
                    <td class="price">${i.totalAmount}</td>
                    <td>${i.join_date}</td>
                </tr>
                <c:set var="stt" value="${stt + 1}" />
            </c:forEach>
        </table>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/home.js"></script>
    <script src="./script/formatVND.js"></script>
</body>

</html>