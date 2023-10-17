const signup = document.querySelector(".signup")
const user = document.querySelector(".user")
const selectTable = document.querySelector(".selection-table")
const avt = document.querySelector(".avt")
const profile = document.querySelector(".profile")

selectTable.classList.add("hidden")

document.addEventListener("click", function(event) {
    if (!selectTable.contains(event.target) && event.target != avt && !selectTable.classList.contains("hidden")) {
        selectTable.classList.add("hidden")
    }
});

avt.addEventListener("click", showDropDown)

user.addEventListener("click", directUser)
profile.addEventListener("click", directUser)

signup.addEventListener("click", function(){
    window.location.href = "http://localhost:9999/shop/signup"
})

function showDropDown() {
    if(selectTable.classList.contains("hidden")) {
        selectTable.classList.remove("hidden")
    }
    else {
        selectTable.classList.add("hidden")
    }
}

function directUser() {
    window.location.href = "http://localhost:9999/shop/user"
}