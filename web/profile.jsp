<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/profile.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="content">
        <div class="content-title">
          <p id="content-title">Profile</p>
        </div>
        <div class="profile-form">
          <div class="profile-title">
            <img src="../web/image/profile.webp" alt="linh ăn cứt">
            <div class="profile-title-name">Nguyen Trung Anh
              <div class="profile-title-ID">ID: B20DCAT009</div>
            </div>
          </div>
          <div class="profile-main">
            <div class="category">PERSONAL INFO</div>
            <div class="row1">
              <label for="column">Username</label>
              <label for="column">Fullname</label>
              <div class="column">xxx2611</div>
              <div class="column">Nguyen Trung Anh</div>
            </div>
            <div class="row1">
              <label for="column">Birthday</label>
              <label for="column">Country</label>
              <div class="column">xx/xx/xxxx</div>
              <div class="column">xxxxxxxxxxxxxx</div>
            </div>
    
            <div class="category">CONTACT INFO</div>
            <div class="row1">
              <label for="column">Tel</label>
              <label for="column">Email</label>
              <div class="column">xxxxxxx893</div>
              <div class="column">xxxxx@xxxxx</div>
            </div>
            <label for="column">Address</label>
            <div class="row2">
              <div class="column">Country</div>
            </div>
          </div>
        </div>
      </div>
    <%@ include file="footer.jsp" %>
</body>
</html>