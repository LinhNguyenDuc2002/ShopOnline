const header1 = document.querySelector(".header1")
const header2 = document.querySelector(".header2")
const signup_form = document.querySelector(".signup-form")
const success = document.querySelector(".success-form")

header1.classList.add("appear")
header2.classList.add("hidden")
success.classList.add("hidden")

var nickname = document.getElementById("fullname")
var username = document.getElementById("username")
var password = document.getElementById("password")
var birthday = document.getElementById("fullname")
var phone = document.getElementById("phone")
var email = document.getElementById("email")

const signupBtn = document.querySelector(".create-account")
const previous = document.querySelector(".back")

previous.addEventListener("click", function(){
    window.history.back();
})

signupBtn.addEventListener("click", signupUser)

function signupUser() {

    const data = {
        fullname: nickname.value,
        username: username.value,
        password: password.value,
        birthday: birthday.value,
        email: email.value,
        phone: phone.value
    }

}