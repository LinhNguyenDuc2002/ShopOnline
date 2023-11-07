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
        <link rel="stylesheet" href="./style/product-style.css">
    </head>

    <body>
        <%@ include file="header.jsp" %>
        <section class="san-pham">
            <div class="product-content">
                <div class="product-left">
                    <div><img class="anh1" src="./image/mt-2.webp" alt=""></div>
                    <div class="chi-tiet">
                        <div>
                            <img src="./image/mt-2.webp" alt="">
                        </div>
                        <div>
                            <img src="./image/mt-2.webp" alt="">
                        </div>
                        <div>
                            <img src="./image/mt-2.webp" alt="">
                        </div>
                        <div>
                            <img src="./image/mt-2.webp" alt="">
                        </div>
                    </div>

                </div>
                <div class="product-right">
                    <h1>Product name</h1>
                    <p class="text">Brand name: <span>WISDOM</span> | Status: <span>available</span></p>
                    <p class="price text">100.000</p>
                    <!--                <p>Size</p>
                                    <div id="radio">
                                        <label for="S">
                    
                                            <input type="radio" name="size" id="S">
                                            <span class="size">S</span>
                                        </label>
                                        <label for="M">
                                            <input type="radio" name="size" id="M">
                                            <span class="size">M</span>
                                        </label>
                                        <label for="L">
                                            <input type="radio" name="size" id="L">
                                            <span class="size">L</span>
                                        </label>
                                        <label for="XL">
                                            <input type="radio" name="size" id="XL">
                                            <span class="size">XL</span>
                                        </label>
                                    </div>-->
                    
                        <p>Quantity</p>
                        <div style="position: relative;">
                            <input type="number" value="0" min="0" max="100" />
                            <div id="inc-button" class="spinner-button">+</div>
                            <div id="dec-button" class="spinner-button">-</div>
                        </div>
                        
                        <button class="button1" style="margin-top: 100px; margin-left: 0">ADD TO CART</button>
                    
                </div>
            </div>
            <!-- MÔ TẢ -->
            <div></div>
            <!-- MÔ TẢ-->
            <div>
                <h2>Products of the same type</h2>
                <div id="wrapper">
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
                </div>
            </div>
        </section>
        <%@ include file="footer.jsp" %>
    </body>



</html>