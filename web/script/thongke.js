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