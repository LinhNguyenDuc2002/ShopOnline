<%-- Document : login.jsp Created on : Sep 12, 2023, 9:52:49 AM Author : TrungAnhNguyen --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/login.css">
    
</head>
<%@ include file="header.jsp" %>

    <body>

        <div class="content">
            <div class="content-title">
                <p id="content-title">Login</p>
            </div>
            
            <form method="POST" action="/shop/users">
                <input type="hidden" name="action" value="login">
                
                <label for="name">
                    <span class="login-label">Username:</span>
                    <span class="obligatory">*</span>
                </label>
                <input class="input" name="username" type="text" placeholder="Username">
                
                <label for="password">
                    <span class="login-label">Password:</span>
                    <span class="obligatory">*</span>
                </label>
                <input class="input" name="password" type="password" placeholder="Password">
                
                <p class="error">${requestScope.error}</p>
                
                <div class="login-button">
                    <input type="submit" value="Sign in">
                    <a href="">Quên mật khẩu ?</a>
                </div>
                <p id="signup">Don't have acount yet? Please sign up
                    <a style="color: #1191e1;" href="http://localhost:9999/shop/users?action=signup">here</a>
                </p>
            </form>
        </div>
    </body>
    <%@ include file="footer.jsp" %>

</html>