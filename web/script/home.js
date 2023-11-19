var prices = document.getElementsByClassName('price');

for (var i = 0; i < prices.length; i++) {
    var priceElement = prices[i];
    var priceValue = parseFloat(priceElement.textContent);

    priceElement.innerHTML = formatCurrency(priceValue);
}

function formatCurrency(amount) {
    return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
}


