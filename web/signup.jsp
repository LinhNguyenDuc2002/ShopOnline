<%-- 
    Document   : signup
    Created on : Sep 6, 2023, 10:02:58 PM
    Author     : LinhNguyenDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/signup.css">
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="content">
        <div class="content-title">
            <p id="content-title">Create a new account</p>
        </div>
        <div class="signup-form">
            <form action="">
                <div class="signup-left">
                    <p>Name</p>
                    <input type="text">
                    <p>Username</p>
                    <input type="text">
                    <p>Password</p>
                    <input type="text">
                </div>
                
                <div class="signup-right">
                    <p>Birthday</p>
                    <input type="text">
                    <p>Phone number</p>
                    <input type="text">
                    <p>Email</p>
                    <input type="text">
                </div>
            </form>
            <div class="signup-button">
                <button class="create-account">Sign up</button>
                <button class="back">Back</button>
            </div>
        </div>
    </div>
</body>
</html>