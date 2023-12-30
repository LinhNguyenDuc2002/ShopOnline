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

document.addEventListener('DOMContentLoaded', function() {
    var categoryElement = document.querySelector('.category-title');
    var idCategory = 0;
    if (categoryElement) {
        idCategory = categoryElement.getAttribute('idCategory');
    }

    var price = document.getElementById('sort-price');
    var seller = document.getElementById('sort-selling');

    price.addEventListener('click', function() {
        if (price.classList.contains('fa-arrow-up')) {
            price.classList.remove('fa-arrow-up');
            price.classList.add('fa-arrow-down');

            
        } else {
            price.classList.remove('fa-arrow-down');
            price.classList.add('fa-arrow-up');
        }
    });

    seller.addEventListener('click', function() {
        if (seller.classList.contains('fa-arrow-up')) {
            seller.classList.remove('fa-arrow-up');
            seller.classList.add('fa-arrow-down');
        } else {
            seller.classList.remove('fa-arrow-down');
            seller.classList.add('fa-arrow-up');
        }
    });
});

function send(id, price, seller) {
    window.location.href = "/shop/categories?id="+ id + "&price=" + price + "&seller=" + seller;
}
