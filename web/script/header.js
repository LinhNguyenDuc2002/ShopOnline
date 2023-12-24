const signup = document.querySelector(".signup");
const login = document.querySelector(".login");
const user = document.querySelector(".user");
const selectTable = document.querySelector(".selection-table");
const avt = document.querySelector(".avt");
const profile = document.querySelector(".profile");

selectTable.classList.add("hidden");

document.addEventListener("click", function (event) {
    if (!selectTable.contains(event.target) && event.target != avt && !selectTable.classList.contains("hidden")) {
        selectTable.classList.add("hidden");
        document.querySelector('.avt i').style.color = 'black';
    }
});

avt.addEventListener("click", showDropDown);

user.addEventListener("click", directUser);
profile.addEventListener("click", directUser);

signup.addEventListener("click", function () {
    window.location.href = "http://localhost:9999/shop/users?action=signup";
});

login.addEventListener("click", function () {
    window.location.href = "http://localhost:9999/shop/users?action=login";
});

function showDropDown() {
    if (selectTable.classList.contains("hidden")) {
        selectTable.classList.remove("hidden");
        document.querySelector('.avt i').style.color = 'blue';
    } else {
        selectTable.classList.add("hidden");
        document.querySelector('.avt i').style.color = 'black';
    }
}

function directUser() {
    window.location.href = "http://localhost:9999/shop/users";
}

let i = 0;
function hienthi() {
    i++;
    const show = document.querySelector(".show");
    const profile = document.querySelector(".profile");

    if (i % 2 != 0) {
        show.style.display = "block";
        show.style.transform = "translateX(0)";
        displays.style.transition = "1.5s ease-in-out";
    } else if (i % 2 == 0) {
        show.style.display = "none";
        show.style.transform = "translateX(-100%)";
        displays.style.transition = "1.5s ease-in-out";
    }
}