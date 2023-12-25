var product_line = document.querySelectorAll(".product-list");
var selectedFilter = localStorage.getItem("filter");
var selectedSort = localStorage.getItem("sort");
if (selectedFilter) {
    document.getElementById("filters").value = selectedFilter;
}
if (selectedSort) {
    document.getElementById("sortBy").value = selectedSort;
}

product_line.forEach(row => {
    row.addEventListener('click', function() {
        const url = this.getAttribute('link');
        window.location.href = "http://localhost:9999" + url;
    });
});

function sortData() {
    var filter = document.getElementById("filters").value;
    var sort = document.getElementById("sortBy").value;

    localStorage.setItem("filter", filter);
    localStorage.setItem("sort", sort);

    window.location.href = "/shop/manage?action=products&filter="+ filter + "&sort=" + sort;
}

