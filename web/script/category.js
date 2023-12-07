var bar = document.querySelector(".category-bar");
var menu = document.querySelector(".category-content");

let show = true;

bar.addEventListener("click", function(event) {
    if(!show) {
        menu.style.display = 'none';
    }
    else {
        menu.style.display = 'grid';
    }
    
    show = !show;
})

document.addEventListener('DOMContentLoaded', function() {
    var categoryLinks = document.querySelectorAll('.category-items');
    var title = document.querySelector(".category-title");
    
    categoryLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            
            categoryLinks.forEach(function(item) {
                item.classList.remove('active');
            });
            
            this.classList.add('active');
            title.innerHTML = this.value;
        });
    });
});