<%-- 
    Document   : header
    Created on : Sep 6, 2023, 10:02:37 PM
    Author     : LinhNguyenDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
    <div class="header1">
        <p id="contact">Hotline: 1900.1001</p>
        
        <div class="avatar1">
            <button class="signup">Sign up</button>
            <button class="login">Log in</button>
            <button class="contact">Contact</button>
        </div>
    </div>

    <div class="header2">
        <p id="contact">Hotline: 1900.1001</p>
        
        <div class="avatar2">
            <p class="user">Hello, ${requestScope.user.getFullname()}</p>
            <div class="avt">
                <i class="fa-solid fa-user fa-xl"></i>
            </div>
        </div>
    </div>

    <div class="body">
        <div class="header-body">
            <img src="" alt="Shop logo">
        </div>

        <div class="content-body">
            <div class="left-content">
                <button id="home">HOME</button>
                <button id="category">CATEGORY</button>
                <button id="contact-button">CONTACT</button>
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
            <div class="profile">
                <p>See all profile</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-house"></i>
                <p>Home</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-cart-shopping"></i>
                <p>Cart</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-truck"></i>
                <p>Purchased orders</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-gear"></i>
                <p>Setting & privacy</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-question"></i>
                <p>Help & support</p>
            </div>
            <div class="selection">
                <i class="fa-solid fa-right-from-bracket"></i>
                <p>Log out</p>
            </div>
        </div>
    </div>

    <script src="./script/header.js"></script>
</body>
</html>

