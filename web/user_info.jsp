<%-- 
    Document   : editPersonal
    Created on : Sep 10, 2023, 9:02:27 AM
    Author     : LinhNguyenDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./style/edit.css">
    <title>JSP Page</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="edit-user">
        <div class="edit-title">
            <p id="edit-title">Personal information</p>
        </div>

        <div class="edit-form">
            <div class="general-info">
                <div class="image"><i class="fa-solid fa-user fa-xl"></i></div>

                <div class="button-edit"> 
                    <button>Change avatar</button>
                    <button>Delete account</button>
                </div>

                <div id="general-info">
                    <div class="nickname">
                        <p id="main">${requestScope.user.getFullname()}</p>
                        <p id="date">Joined: ${requestScope.user.getJoin_date()}</p>
                    </div>

                    <textarea readonly disabled class="bio">${requestScope.user.getNote()}</textarea>
                    
                </div>
            </div>

            <form action="http://localhost:9999/shop/users" method="POST">
                <input type="hidden" name="action" value="update">

                <div class="edit">
                    <div class="edit-left">
                        <p>Name</p>
                        <input type="text" name="fullname" value="${requestScope.user.getFullname()}">
                        <p>Username</p>
                        <input type="text" name="username" value="${requestScope.user.getUsername()}">
                        <p>Password</p>
                        <input type="text" name="password" value="${requestScope.user.getPassword()}">
                        <p>Birthday</p>
                        <input type="date" name="birthday" value="${requestScope.user.getBirthday()}">
                        <p>Phone number</p>
                        <input type="text" name="phone" value="${requestScope.user.getPhone()}">
                        <p>Email</p>
                        <input type="text" name="email" value="${requestScope.user.getEmail()}">
                    </div>
                    
                    <div class="edit-right">
                        <p>Gender</p>
                        <div class="radio">
                            <label for="">
                                <input type="radio" name="gender" ${requestScope.user.getSex() == "true" ? "checked" : ""}>Male
                            </label>
                            <label for="">
                                <input type="radio" name="gender" ${requestScope.user.getSex() == "false" ? "checked" : ""}>Female
                            </label>
                        </div>
                        <p>Detail address</p>
                        <input type="text" name="detail_address" value="${requestScope.user.getDetail_address()}">
                        <p>City</p>
                        <input type="text" name="city" value="${requestScope.user.getCity()}">
                        <p>Country</p>
                        <input type="text" name="country" value="${requestScope.user.getCountry()}">
                        <p>Note</p>
                        <textarea name="note">${requestScope.user.getNote()}</textarea>
                    </div>
                </div>

                 <div class="edit-button">
                    <button class="save" type="submit">Save</button>
                    <button class="back" onclick="goBack()"><i class="fa-solid fa-reply"></i>Back</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="footer.jsp" %>

    <script src="./script/edit_user.js"></script>
</body>
</html>
