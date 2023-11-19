<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="style/addsp-style.css">
</head>

<body>
    <%@ include file="header.jsp" %>

    <div class="content">
        <p class="tieu-de">Add new product</p>

        <form action="/shop/products" method="POST" enctype="multipart/form-data" class="tong">
            <input type="hidden" name="action" value="add" />
            <div class="left">
                <div class="left-input">
                    <label for="">Product name</label>
                    <input type="text" id="ten-san-pham" name="productName" required>
                </div>

                <div class="left-input">
                    <label for="">Price</label>
                    <input type="text" id="nhap-gia" name="price" required>
                </div>

                <div class="left-input">
                    <label for="">Number</label>
                    <input type="text" id="so-luong" name="available" required>
                </div>

                <div class="left-input">
                    <label for="">Category</label>
                    <select id="cars" name="category" required>
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
                    <input type="file" name="image" id="file" class="inputfile" required>
                    <label class="label" for="file">Upload</label>
                </div>

                <input type="submit" class="button1" value="Add">
            </div>
        </form>
    </div>

    <%@ include file="footer.jsp" %>
</body>

</html>