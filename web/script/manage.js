var product_line = document.querySelectorAll(".product-list");

product_line.forEach(row => {
    row.addEventListener('click', function() {
        const url = this.getAttribute('link');
        window.location.href = "http://localhost:9999" + url;
    });
});

function sortData() {
    var filter = document.getElementById("filters").value;
    var sort = document.getElementById("sortBy").value;

    $.ajax ({
        url: "/shop/manage",
        method: "GET",
        data: {
            action: "products",
            filter: filter,
            sort: sort
        },
        success: function(response) {
            var row = document.querySelector(".product-body");
            row.innerHTML = response;

            formatVND();
        },
        error: function(error) {

        }
    });
}

