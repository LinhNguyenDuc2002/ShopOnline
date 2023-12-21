var product_line = document.querySelectorAll(".product-list");

product_line.forEach(row => {
    row.addEventListener('click', function() {
        const url = this.getAttribute('link');
        window.location.href = "http://localhost:9999" + url;
    });
  });

