<<<<<<< HEAD
var prices = document.querySelectorAll('.price');

for (var a = 0; a < prices.length; a++) {
    var priceElement = prices[a];
    var priceValue = parseFloat(priceElement.textContent);
=======
formatVND();

function formatVND() {
    var prices = document.querySelectorAll('.price');
>>>>>>> dev

    for (var i = 0; i < prices.length; i++) {
        var priceElement = prices[i];
        var priceValue = parseFloat(priceElement.textContent);

        priceElement.innerHTML = formatCurrency(priceValue);
    }
}

function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}
