function find() {
    var start = document.getElementById("startAt").value;
    var end = document.getElementById("endAt").value;

    console.log(end)
    if(isValidDate(start) && isValidDate(end)) {
        window.location.href = "/shop/customers?start="+ start + "&end=" + end;
    }
}

function isValidDate(dateString) {
    const regex = /^\d{4}-\d{2}-\d{2}$/;
    return regex.test(dateString);
}