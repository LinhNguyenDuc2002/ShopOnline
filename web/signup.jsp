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
            <form action="/shop/users" method="POST">
                <input type="hidden" name="action" value="signup">

                <div class="form">
                    <div class="signup-left">
                        <div class="inputBox">
                            <input type="text" pattern="^[A-Za-z0-9]+$" name="fullname" id="fullname" value="${requestScope.input.get("fullname")}" required
                            title="Please enter a username without special characters"><i>Name</i>
                        </div>
                        
                        <div class="inputBox">
                            <input type="text" name="username" id="username" value="${requestScope.input.get("username")}" required><i>Username</i>
                        </div>
                        
                        <div class="inputBox">
                            <input type="text" minlength="6" name="password" id="password" value="${requestScope.input.get("password")}" required><i>Password</i>
                        </div>
                        
                    </div>
                    
                    <div class="signup-right">
                        <div class="inputBox">
                            <input type="date" name="birthday" id="birthday" value="${requestScope.input.get("birthday")}"><i>Birthday</i>
                        </div>
                        
                        <div class="inputBox">
                            <input type="tel" pattern="[0-9]{10,11}" name="phone" id="phone" value="${requestScope.input.get("phone")}" required><i>Phone number</i>
                        </div>
                        
                        <div class="inputBox">
                            <input type="email" name="email" id="email" value="${requestScope.input.get("email")}" required><i>Email</i>
                        </div>
                        
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