<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/header.css">
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/home.css">
    <link rel="stylesheet" href="style/addsp-style.css">
    <link rel="stylesheet" href="product-style.css">
</head>

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
    <p class="tieu-de">Add new product</p>
    <div class="tong">
        <div class="left">
            <div class="left-input">
                <label for="">Product name</label>
                <input type="text" id="ten-san-pham" name="ten-san-pham">
            </div>
            <div class="left-input">
                <label for="">Price</label>
                <input type="text" id="nhap-gia" name="nhap-gia">
            </div>
            <div class="left-input">
                <label for="">Number</label>
                <input type="text" id="so-luong" name="so-luong">
            </div>
            <div class="left-input">
                <label for="">Category</label>
                <select id="cars">
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                  </select>
            </div>
            <div class="left-input">
                <label for="">Unit</label>
                <select id="cars">
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                  </select>
            </div>
        </div>
        <div class="right">
            <!-- <div class="right-input">
                <label for="">Chá»n size</label>
                <label class="container">S
                    <input type="checkbox">
                    <span class="checkmark"></span>
                </label>
                <label class="container">M
                    <input type="checkbox">
                    <span class="checkmark"></span>
                </label>
                <label class="container">L
                    <input type="checkbox">
                    <span class="checkmark"></span>
                </label>
                <label class="container">XL
                    <input type="checkbox">
                    <span class="checkmark"></span>
                </label>
            </div> -->
            <div class="right-input">
                <label for="">Description</label>
                <textarea name="mo-ta" id="mo-ta" cols="60" rows="10"></textarea>
            </div>
            <div class="right-input">
                <input type="file" name="file" id="file" class="inputfile" />
                <label class="label" for="file" style="font-size: 20px;">Upload</label>
            </div>
            <button class="button1">Submit</button>
        </div>
    </div>

</body>

</html>