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
                </div>
                <div id="general-info">
                    <p>Nguyen Duc Linh</p>
                    <p>Joined: 15/08/2023</p>
                </div>
            </div>

            <form action="">
                <div class="edit-left">
                    <p>Name</p>
                    <input type="text">
                    <p>Username</p>
                    <input type="text">
                    <p>Password</p>
                    <input type="text">
                    <p>Birthday</p>
                    <input type="date">
                    <p>Phone number</p>
                    <input type="text">
                    <p>Email</p>
                    <input type="text">
                </div>
                
                <div class="edit-right">
                    <p>Gender</p>
                    <div class="radio">
                        <label for="">
                                <input type="radio" name="gender" value="male">Male
                        </label>
                        <label for=""><input type="radio" name="gender" value="female">Female</label>
                    </div>
                    <p>Detail address</p>
                    <input type="text">
                    <p>City</p>
                    <input type="text">
                    <p>Country</p>
                    <input type="text">
                    <p>Note</p>
                    <textarea ></textarea>
                </div>
            </form>

            <div class="edit-button">
                <button class="save">Save</button>
                <button class="back"><i class="fa-solid fa-reply"></i>Back</button>
            </div>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>
</html>
