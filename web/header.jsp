<%-- 
    Document   : header
    Created on : Sep 6, 2023, 10:02:37 PM
    Author     : LinhNguyenDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Header</title>
    <link rel="stylesheet" href="./style/header.css">
    <link rel="stylesheet" href="./css/all.min.css">
</head>
<body>

    <c:choose>
        <c:when test="${empty requestScope.user}">
            <div class="header1">
                <p id="contact">Hotline: 1900.1001</p>
                
                <div class="avatar1">
                    <a class="signup" href="/shop/users?action=signup">Sign up</a>
                    <a class="login" href="/shop/users?action=login">Log in</a>
                    <a class="contact">Contact</a>
                </div>
            </div>
        </c:when>

        <c:otherwise>
            <div class="header2">
                <p id="contact">Hotline: 1900.1001</p>
                
                <div class="avatar2">
                    <p class="user">Hello, ${requestScope.user.getFullname()}</p>
                    <div class="avt">
                        <i class="fa-solid fa-user fa-xl"></i>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="body">
        <div class="header-body">
            <img src="./image/logo-vpp-021.png" alt="Shop logo">
        </div>

        <div class="content-body">
            <div class="left-content">
                <c:choose>
                    <c:when test="${not empty requestScope.user and requestScope.user.role eq 'ADMIN'}">
                        <a href="/shop/home?role=admin">HOME</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/shop/home">HOME</a>
                    </c:otherwise>
                </c:choose>
                <a href="#">CATEGORY</a>
                <a href="#">CONTACT</a>
            </div>

            <div class="right-content">
                <div class="search">
                    <input type="text" name="" id="search-text">
                    <i class="fa-solid fa-magnifying-glass fa-lg"></i>
                </div>
                <i class="fa-solid fa-cart-shopping fa-lg"></i>
            </div>
        </div>

        <div class="selection-table">
            <a href="/shop/users" class="profile">
                <p>See all profile</p>
            </a>

            <a class="selection" href="/shop/home">
                <i class="fa-solid fa-house"></i>
                <p>Home</p>
            </a>

            <a class="selection" href="#">
                <i class="fa-solid fa-cart-shopping"></i>
                <p>Cart</p>
            </a>

            <a class="selection" href="#">
                <i class="fa-solid fa-truck"></i>
                <p>Purchased orders</p>
            </a>

            <a class="selection" href="#">
                <i class="fa-solid fa-gear"></i>
                <p>Setting & privacy</p>
            </a>

            <a class="selection" href="#">
                <i class="fa-solid fa-question"></i>
                <p>Help & support</p>
            </a>

            <a class="selection" href="/shop/users?action=logout">
                <i class="fa-solid fa-right-from-bracket"></i>
                <p>Log out</p>
            </a>
        </div>
    </div>

    <script src="./script/header.js"></script>
</body>
</html>

