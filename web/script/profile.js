const header1 = document.querySelector(".header1")
const header2 = document.querySelector(".header2")
const signup_form = document.querySelector(".content")

header1.classList.add("appear")
header2.classList.add("hidden")

function goBack() {
    window.history.back()
}