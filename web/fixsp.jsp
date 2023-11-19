<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.io.*,java.util.*, model.* ,dao.*, java.sql.Date"%>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="./style/header.css">
        <link rel="stylesheet" href="./css/all.min.css">
        <link rel="stylesheet" href="./style/home.css">
        <link rel="stylesheet" href="./style/addsp-style.css">
        <link rel="stylesheet" href="product-style.css">
    </head>

    <%
        Product a = (Product)request.getAttribute("sanphamchitiet");
        Category b = a.getCategory();
        SanPhamDAO sp = new SanPhamDAO();
        List<Category> list= sp.TimCategory();
    
    %>
    <body>
        <div class="header">
            <p id="contact">Hotline: 1900.1001</p>

            <div class="direct-button">
                <button class="signup">Sign up</button>
                <button class="login">Log in</button>
                <button class="contact">Contact</button>
            </div>
        </div>
        <div class="body">
            <div class="header-body">
                <img src="" alt="Shop logo">
            </div>
            <div class="content-body">
                <div class="left-content">
                    <button id="home">HOME</button>
                    <button id="category">CATEGORY</button>
                    <button id="contact-button">CONTACT</button>
                </div>
                <div class="right-content">
                    <div class="search">
                        <input type="text" name="" id="search-text">
                        <i class="fa-solid fa-magnifying-glass fa-lg"></i>
                    </div>
                    <i class="fa-solid fa-cart-shopping fa-lg"></i>
                </div>
            </div>
        </div>
        <p class="tieu-de">Edit product</p>
        <div style="position: relative">
            <form class="tong" action="EditProductController" method="POST" enctype="multipart/form-data" >
                <div class="left">
                    <div class="left-input">
                        <label for="">Product name</label>
                        <input type="text" id="ten-san-pham" name="ten-san-pham" value="${requestScope.sanphamchitiet.getProductName()}">
                    </div>
                    <div class="left-input">
                        <label for="">Price</label>
                        <input type="text" id="nhap-gia" name="gia" value="${requestScope.sanphamchitiet.getPrice()}">
                    </div>
                    <div class="left-input">
                        <label for="">Number</label>
                        <input type="text" id="so-luong" name="so-luong" value="${requestScope.sanphamchitiet.getAvailable()}">
                    </div>
                    <div class="left-input">
                        <label for="">Category</label>
                        <select id="category" value="1" name="category">
                            <%for(Category i : list){
                    if(i.getName().equals(b.getName())){%>
                            <option value="<%= i.getId()%>" selected> <%= i.getName()%> </option>
                            <%} else { %>
                            <option value="<%= i.getId()%>"> <%= i.getName()%> </option>
                            <%} } %>
                        </select>
                    </div>
                </div>
                <div class="right">
                    <div class="right-input">
                        <label for="">Description</label>
                        <textarea name="mo-ta" id="mo-ta" cols="60" rows="10" style="resize:none">${requestScope.sanphamchitiet.getDescription()}</textarea>
                    </div>
                    <div class="right-input">
                        <input type="file" name="file" id="file" value="12" class="inputfile" />
                        <label class="label" for="file" style="font-size: 20px;">Upload</label>
                        
                    </div>
                    <div class="nut">
                        <button class="button1" >Submit</button>
                    </div>
                </div>
            </form>
            <a href="DeleteProduct" style="position: absolute;bottom: 35px;right: 179px"><button class="button1 delete">Delete</button></a>      
        </div>
    </body>
    <script src="./script/fixsp.js"></script>
</html>