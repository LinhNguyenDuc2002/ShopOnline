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
                    <form>
                        <label for="name">
                            <span class="login-label">Username:</span>
                            <span class="obligatory">*</span>
                        </label><br>
                        <input id="name" type="text" placeholder="Username"><br>
                        <label for="password">
                            <span class="login-label">Password:</span>
                            <span class="obligatory">*</span>
                        </label><br>
                        <input id="password" type="text" placeholder="Password">
                        <div class="login-button">
                            <button>Sign in</button>
                            <a href="">Quên mật khẩu ?</a>
                        </div>
                        <p>Don't have acount yet? Please sign up
                            <a style="color: #1191e1;" href="http://localhost:9999/shop/users?action=signup">here</a>
                        </p>
                    </form>
                </div>
                <script src="./script/login.js"></script>
            </body>
            <%@ include file="footer.jsp" %>

        </html>