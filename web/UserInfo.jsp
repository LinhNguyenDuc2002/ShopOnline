<%-- Document : editPersonal Created on : Sep 10, 2023, 9:02:27 AM Author : LinhNguyenDuc --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import = "java.util.List" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./style/user_info.css">
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
                            <p>Fullname</p>
                            <input type="text" pattern="^[A-Za-z0-9 ]+$" name="fullname"
                                value="${requestScope.user.getFullname()}"
                                title="Please enter a username without special characters" required>

                            <p>Username</p>
                            <input type="text" name="username" value="${requestScope.user.getUsername()}" required>

                            <p>Birthday</p>
                            <input type="date" name="birthday" value="${requestScope.user.getBirthday()}">

                            <p>Phone number</p>
                            <input type="text" pattern="[0-9]{10,11}" name="phone" value="${requestScope.user.getPhone()}" required>

                            <p>Email</p>
                            <input type="text" name="email" value="${requestScope.user.getEmail()}" required>
                        </div>

                        <div class="edit-right">
                            <p>Gender</p>
                            <div class="radio">
                                <label for="">
                                    <input type="radio" name="gender" ${requestScope.user.getSex()=="true"
                                        ? "checked" : "" } required>Male
                                </label>
                                <label for="">
                                    <input type="radio" name="gender" ${requestScope.user.getSex()=="false"
                                        ? "checked" : "" } required>Female
                                </label>
                            </div>

                            <p>Detail address</p>
                            <input type="text" name="detail_address"
                                value="${requestScope.user.getDetail_address()}" required>

                            <p>City</p>
                            <input type="text" name="city" value="${requestScope.user.getCity()}" required>

                            <p>Country</p>
                            <input type="text" name="country" value="${requestScope.user.getCountry()}" required>

                            <p>Note</p>
                            <textarea name="note">${requestScope.user.getNote()}</textarea>
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

                    <div class="edit-button">
                        <input class="save" type="submit" value="Save"></input>
                        <input class="back" type="button" onclick="goBack()" value="Back"></input>
                    </div>
                </form>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
</body>

</html>