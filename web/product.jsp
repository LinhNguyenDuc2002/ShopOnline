<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import="java.io.*, model.* ,DAO.*, java.sql.Date"%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="./style/header.css">
        <link rel="stylesheet" href="./css/all.min.css">
        <link rel="stylesheet" href="./style/home.css">
        <link rel="stylesheet" href="./style/product-style.css">
    </head>
    <% 
        Product a = (Product)request.getAttribute("sanphamchitiet"); 
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
        <section class="san-pham">
            <div class="product-content">
                <div class="product-left">
                    <div><img class="anh1" src="data:image/jpg;base64,${requestScope.sanphamchitiet.getImage()}"alt=""></div>
                   

                </div>
                <div class="product-right">
                    <h1>${requestScope.sanphamchitiet.getProductName()}</h1>
                    <p class="text">Category: <span>${requestScope.sanphamchitiet.getCategory().getName()}</span> | Status: 
                        <% if (a.getAvailable() > 0 ){ %>
                        <span>available</span>
                        <% } else { %>
                        <span>Sold out</span>
                        <% } %>
                    </p>
                    <p class="price text">${requestScope.sanphamchitiet.getPrice()}</p>                    
                        <p>Quantity</p>
                        <div style="position: relative;">
                            <input type="number" value="1" min="1" max="100" />
                            <div id="inc-button" class="spinner-button">+</div>
                            <div id="dec-button" class="spinner-button">-</div>
                        </div>
                        <button class="button1" style="margin-top: 100px; margin-left: 0">ADD TO CART</button>
                </div>
            </div>
            <!-- MÔ TẢ -->
            <div class="mo-ta">
                     <h2 >MÔ TẢ</h2>
                     <div class="khung-mo-ta">
                         <p>${requestScope.sanphamchitiet.getDescription()}<p>
                     </div>
            </div>
            <!-- MÔ TẢ-->
            <div>
                <h2>Products of the same type</h2>
                <div class="wrapper">
                    <ul class="products">
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="" class="hien-thi">
                                        <img src="./image/mt-2.webp" alt="">
                                    </a>
                                    <a href="" class="buy-now">BUY NOW</a>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-name">Product name</a>
                                    <div class="product-price">100.000</div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="" class="hien-thi">
                                        <img src="./image/mt-2.webp" alt="">
                                    </a>
                                    <a href="" class="buy-now">BUY NOW</a>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-name">Product name</a>
                                    <div class="product-price">100.000</div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="" class="hien-thi">
                                        <img src="./image/mt-2.webp" alt="">
                                    </a>
                                    <a href="" class="buy-now">BUY NOW</a>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-name">Product name</a>
                                    <div class="product-price">100.000</div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="product-item">
                                <div class="product-top">
                                    <a href="" class="hien-thi">
                                        <img src="./image/mt-2.webp" alt="">
                                    </a>
                                    <a href="" class="buy-now">BUY NOW</a>
                                </div>
                                <div class="product-info">
                                    <a href="" class="product-name">Product name</a>
                                    <div class="product-price">100.000</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
        <%@ include file="footer.jsp" %>
    </body>
</html>