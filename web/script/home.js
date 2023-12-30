function loadPage(clicked) {
    var page = clicked.getAttribute('page');
    var btn = document.querySelectorAll('.index-page');
    btn.forEach(btn => {
        btn.style.backgroundColor = 'lightgrey';
    });
    clicked.style.backgroundColor = 'black';

    $.ajax ({
        url: "/shop/home",
        method: "GET",
        data: {
            page: page
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


