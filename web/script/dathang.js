var selectElement = document.getElementById("ship");
var selectedOption = selectElement.options[selectElement.selectedIndex];
var price = selectedOption.getAttribute("price");
var total = document.querySelector(".total").textContent.trim();
var tong = document.querySelector(".tong");
var displayElement = document.querySelector(".ship-fee");

totalPrice = parseFloat(total.replace(/[^\d]/g, ''));
displayElement.innerHTML = formatCurrency(parseFloat(price));
tong.innerHTML = formatCurrency(totalPrice + parseFloat(price));
tong.value = totalPrice + parseFloat(price);

selectElement.addEventListener("change", function() {
    selectedOption = selectElement.options[selectElement.selectedIndex];
    price = selectedOption.getAttribute("price");
    displayElement.innerHTML = formatCurrency(parseFloat(price));

    tong.innerHTML = formatCurrency(totalPrice + parseFloat(price));
    tong.value = totalPrice + parseFloat(price);
});