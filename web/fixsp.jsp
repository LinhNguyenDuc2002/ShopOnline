<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/home.css">
    <link rel="stylesheet" href="./style/addsp-style.css">
    <link rel="stylesheet" href="product-style.css">
</head>

<body>
    <%@ include file="header.jsp" %>
    
    <div class="fixsp">
        <p class="tieu-de">Edit product</p>
        <div style="position: relative">
            <form class="tong" action="/shop/products?action=edit&id=${requestScope.sanphamchitiet.id}" method="POST" enctype="multipart/form-data" >
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
                            <c:forEach items="${requestScope.list}" var="i">
                                <c:choose>
                                    <c:when test="${i.id == requestScope.sanphamchitiet.category.id}">
                                        <option value="${i.id}" selected>${i.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i.id}">${i.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                        
                <div class="right">
                    <div class="right-input">
                        <label for="">Description</label>
                        <textarea name="mo-ta" id="mo-ta" cols="60" rows="10" style="resize:none" >${requestScope.sanphamchitiet.description}</textarea>
                    </div>
                    
                    <div class="right-input">
                        <input type="file" name="file" id="file" value="12" class="inputfile" onchange="displayFileName()"/>
                        <label class="label" for="file" style="font-size: 20px;">Upload</label>

                    </div>
                    
                    <div class="nut">
                        <button class="button1" >Submit</button>
                    </div>
                </div>
            </form>
                    
            <a href="/shop/products?action=delete&id=${requestScope.sanphamchitiet.id}" style="position: absolute;bottom: 35px;right: 179px"><button class="button1 delete">Delete</button></a>      
        </div>
    </div>

    <%@ include file="footer.jsp" %>
    <script src="./script/addsp.js"></script>
</body>

</html>