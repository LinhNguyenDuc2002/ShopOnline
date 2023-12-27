<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.tomcat.util.codec.binary.Base64" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="./css/all.min.css">
    <link rel="stylesheet" href="./style/otp.css">
</head>

<body >
    <%@ include file="header.jsp" %> 
    <div class="otp__cover">
        <div class="otp">
            <div class="otp__icon">
                <i class="fa-solid fa-circle-check"></i>
            </div>

            <h4>Enter OTP Code</h4>

            <form action="/shop/users" method="POST" class="otp__form">
                <input type="hidden" name="action" value="verify"/>

                <div class="otp__form--input">
                    <input type="number" name="digital1" min="0" max="9" onInput="inputNext(event)" required/>
                    <input type="number" name="digital2" min="0" max="9" onInput="inputNext(event)" disabled required/>
                    <input type="number" name="digital3" min="0" max="9" onInput="inputNext(event)" disabled required/>
                    <input type="number" name="digital4" min="0" max="9" onInput="inputNext(event)" disabled required/>
                    <input type="number" name="digital5" min="0" max="9" onInput="inputNext(event)" disabled required/>
                    <input type="number" name="digital6" min="0" max="9" onInput="inputNext(event)" disabled required/>
                </div>

                <c:choose>
                    <c:when test="${requestScope.error != null}">
                        <div class="message">
                            <p>${requestScope.error}</p>
                        </div>
                    </c:when>
                </c:choose>

                <div class="message">
                    <p value="${requestScope.error}"></p>
                </div>

                <div class="otp__form--btn">
                    <button type="submit">Verify OTP</button>
                    <button >Resend code</button>
                </div>
            </form>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
    <script src="./script/otp.js"></script>
</body>

</html>