const header1 = document.querySelector(".header1")
const header2 = document.querySelector(".header2")
const signup_form = document.querySelector(".signup-form")

header1.classList.add("appear")
header2.classList.add("hidden")

var nickname = document.getElementById("fullname")
var username = document.getElementById("username")
var password = document.getElementById("password")
var birthday = document.getElementById("fullname")
var phone = document.getElementById("phone")
var email = document.getElementById("email")

function goBack() {
    window.history.back()
}