var product_line = document.querySelectorAll(".product-list");
            
product_line.forEach(row => {
    row.addEventListener('click', function() {
        var start = document.getElementById("startAt").value;
        var end = document.getElementById("endAt").value;

        if (isValidDate(start) && isValidDate(end)) {
            const url = this.getAttribute('link');
            window.location.href = "http://localhost:9999" + url + "&start=" + start + "&end=" + end;
        }
    });
});

function find() {
    var start = document.getElementById("startAt").value;
    var end = document.getElementById("endAt").value;

    if(isValidDate(start) && isValidDate(end)) {
        $.ajax ({
            url: "/shop/customers",
            method: "GET",
            data: {
                start: start,
                end: end
            },
            success: function(response) {
                var row = document.querySelector(".customer");
                row.innerHTML = response;
    
                formatVND();

                var product_line = document.querySelectorAll(".product-list");
            
                product_line.forEach(row => {
                    row.addEventListener('click', function() {
                        var start = document.getElementById("startAt").value;
                        var end = document.getElementById("endAt").value;

                        if (isValidDate(start) && isValidDate(end)) {
                            const url = this.getAttribute('link');
                            window.location.href = "http://localhost:9999" + url + "&start=" + start + "&end=" + end;
                        }
                    });
                });
            },
            error: function(error) {
    
            }
        });
    }
}

function isValidDate(dateString) {
    const regex = /^\d{4}-\d{2}-\d{2}$/;
    return regex.test(dateString);
}