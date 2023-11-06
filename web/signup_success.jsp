<%-- 
    Document   : signupSuccess
    Created on : Sep 17, 2023, 1:03:07 PM
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

        <div class="success-form">
            <div class="success-content">
                <p><i class="fa-regular fa-circle-check"></i>Account created successfully!</p>
                <p>Login to start shopping</p>
            </div>
            <button class="login-account">Log in</button>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/signup_success.js"></script>
</body>
</html>
