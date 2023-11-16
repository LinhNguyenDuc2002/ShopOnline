<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
    <%@ include file="header.jsp" %>
    
    <p class="tieu-de">Add new product</p>
    <form action="" class="tong">
        <div class="left">
            <div class="left-input">
                <label for="">Product name</label>
                <input type="text" id="ten-san-pham" name="productName">
            </div>

            <div class="left-input">
                <label for="">Price</label>
                <input type="text" id="nhap-gia" name="price">
            </div>

            <div class="left-input">
                <label for="">Number</label>
                <input type="text" id="so-luong" name="available">
            </div>

            <div class="left-input">
                <label for="">Category</label>
                <select id="cars">
                    <c:forEach items="${requestScope.categories}" var="i">
                        <option value=${i.name}>${i.name}</option>
                    </c:forEach>
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
            <div class="right-input">
                <label for="">Description</label>
                <textarea name="mo-ta" id="mo-ta" cols="60" rows="10" name="description"></textarea>
            </div>

            <div class="right-input">
                <input type="file" name="image" id="file" class="inputfile" />
                <label class="label" for="file" style="font-size: 20px;">Upload</label>
            </div>

            <button class="button1">Submit</button>
        </div>
    </form>

    <%@ include file="footer.jsp" %>
</body>

</html>