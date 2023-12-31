var bill_line = document.querySelectorAll(".product-list");
const containers = document.querySelectorAll('.sub-container');

bill_line.forEach((row, index) => {
    row.addEventListener('click', function() {
        if(containers[index].style.display == "contents") {
            containers[index].style.display = "none";
        }
        else {
            bill_line.forEach((row, index) => {
                containers[index].style.display = "none";
            });
    
            containers[index].style.display = "contents";
        }
    });
});

var product_line = document.querySelectorAll(".sub-product-list");

product_line.forEach(row => {
    row.addEventListener('click', function() {
        const url = this.getAttribute('link');
        window.location.href = "http://localhost:9999" + url;
    });
});

function updateStatus(clicked) {
    event.stopPropagation();
    var id = clicked.getAttribute("idBill");

    clicked.style.backgroundColor = 'darkred';
    clicked.style.color = 'white';

    var status = '.status'+id;
    document.querySelector(status).style.color = 'green';

    $.ajax ({
        url: "/shop/bill",
        method: "POST",
        data: {
            id: id,
            action: 'update'
        },
        success: function(response) {
            
        },
        error: function(error) {

        }
    });
}