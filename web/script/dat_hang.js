const header1 = document.querySelector(".header1")
const header2 = document.querySelector(".header2")

header2.classList.add("appear")
header1.classList.add("hidden")

function goBack() {
    window.history.back()
}
