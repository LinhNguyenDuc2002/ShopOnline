var bar = document.querySelector(".category-bar");
var menu = document.querySelector(".category-content");

let show = false;

bar.addEventListener("click", function(event) {
    if(!show) {
        menu.style.display = 'none';
    }
    else {
        menu.style.display = 'grid';
    }
    
    show = !show;
});
