<%-- Document : login.jsp Created on : Sep 12, 2023, 9:52:49 AM Author : TrungAnhNguyen --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/changePwd.css">
    
</head>
    <%@ include file="header.jsp" %>

    <body>
        <div class="content">
            <div class="content-title">
                <p id="content-title">Change password</p>
            </div>
            
            <form method="POST" action="/shop/users" class="change">
                <input type="hidden" name="action" value="change-pwd">
                
                <label for="name">
                    <span class="login-label">Old password:</span>
                    <span class="obligatory">*</span>
                </label>
                <input class="input" name="old" type="password" required >
                
                <label for="password">
                    <span class="login-label">New password:</span>
                    <span class="obligatory">*</span>
                </label>
                <input class="input" minlength="6" name="new" type="password" required >

                <label for="password">
                    <span class="login-label">Enter new password again:</span>
                    <span class="obligatory">*</span>
                </label>
                <input class="input" minlength="6" name="again" type="password" required >
                
                <c:choose>
                    <c:when test="${requestScope.message == 'success'}">
                        <p class="error" style="color: green;">Change password successfully!</p>
                    </c:when>
                    <c:otherwise>
                        <p class="error" style="color: red;">${requestScope.message}</p>
                    </c:otherwise>
                </c:choose>
                
                <div class="login-button">
                    <input type="submit" value="Change password">
                </div>
            </form>
        </div>
    </body>

    <%@ include file="footer.jsp" %>
</html>