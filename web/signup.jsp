<%-- 
    Document   : signup
    Created on : Sep 6, 2023, 10:02:58 PM
    Author     : LinhNguyenDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.List" %>
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
            <form action="http://localhost:9999/shop/signup" method="POST">
                <div class="form">
                    <div class="signup-left">
                        <p>Name</p>
                        <input type="text" name="fullname" id="fullname" value="${requestScope.fullname}">
                        <p>Username</p>
                        <input type="text" name="username" id="username" value="${requestScope.username}">
                        <p>Password</p>
                        <input type="text" name="password" id="password" value="${requestScope.password}">
                    </div>
                    
                    <div class="signup-right">
                        <p>Birthday</p>
                        <input type="date" name="birthday" id="birthday" value="${requestScope.birthday}">
                        <p>Phone number</p>
                        <input type="text" name="phone" id="phone" value="${requestScope.phone}">
                        <p>Email</p>
                        <input type="text" name="email" id="email" value="${requestScope.email}">
                    </div>
                </div>

                <div class="error">
                    <% 
                        List<String> errors = (List<String>) request.getAttribute("error");
                        if(errors != null) {
                            for(String error : errors) { 
                    %>
                                <p><%= error %></p>
                    <%      
                            }
                        }
                    %>
                </div>
                
                <div class="policy">
                    <p>By clicking Sign Up, you agree to our Terms, Privacy Policy and Cookies Policy.</p>
                    <p>You may receive SMS notifications from us and can opt out at any time.</p>
                </div>

                <div class="signup-button">
                    <button class="create-account">Sign up</button>
                    <button class="back" onclick="goBack()"><i class="fa-solid fa-reply"></i>Back</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/signup.js"></script>
</body>
</html>