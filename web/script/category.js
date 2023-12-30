var bar = document.querySelector(".category-bar");
var menu = document.querySelector(".category-content");
var value = "asc";

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
    var sort = document.getElementById('sort-price');
    var sortBy = document.getElementById('sort-by');

    sort.addEventListener('click', function() {
        if (sort.classList.contains('fa-arrow-up')) {
            sort.classList.remove('fa-arrow-up');
            sort.classList.add('fa-arrow-down');

            value = "desc";
        } else {
            sort.classList.remove('fa-arrow-down');
            sort.classList.add('fa-arrow-up');

            value = "asc";
        }

        loadPageFirst();
    });

    sortBy.addEventListener('change', function() {
        loadPageFirst();
    });
});

function send(id, price, seller) {
    window.location.href = "/shop/categories?id="+ id + "&price=" + price + "&seller=" + seller;
}

function loadPage(clicked) {
    var page = clicked.getAttribute('page');
    var btn = document.querySelectorAll('.index-page');
    var id = document.querySelector(".category-title").getAttribute("idCategory");
    var by = document.getElementById("sort-by").value;

    btn.forEach(btn => {
        btn.style.backgroundColor = 'lightgrey';
    });
    clicked.style.backgroundColor = 'black';

    $.ajax ({
        url: "/shop/categories",
        method: "GET",
        data: {
            id: id,
            page: page,
            by: by,
            sort: value
        },
        success: function(response) {
            var row = document.querySelector(".products");
            row.innerHTML = response;

            formatVND();
        },
        error: function(error) {

        }
    });
}

function loadPageFirst() {
    var id = document.querySelector(".category-title").getAttribute("idCategory");
    var by = document.getElementById("sort-by").value;

    var btn = document.querySelectorAll('.index-page');

    btn.forEach(btn => {
        btn.style.backgroundColor = 'lightgrey';
    });
    btn[0].style.backgroundColor = 'black';

    $.ajax ({
        url: "/shop/categories",
        method: "GET",
        data: {
            id: id,
            page: 0,
            by: by,
            sort: value
        },
        success: function(response) {
            var row = document.querySelector(".products");
            row.innerHTML = response;

            formatVND();
        },
        error: function(error) {

        }
    });
}
