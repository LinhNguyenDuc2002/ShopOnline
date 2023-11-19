/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var file = document.getElementById("file");
var upload = document.getElementById("upload-anh");


file.addEventListener("change",function(e){
    console.log(e)
    upload.innerHTML = e.target.value;
});
