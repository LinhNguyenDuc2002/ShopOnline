function inputNext(event) {
    // Kiểm tra xem người dùng đã nhập giá trị hay chưa
    if (event.target.value.length > 0) {
        // Tìm ô tiếp theo
        var nextInput = event.target.nextElementSibling;

        // Kiểm tra xem ô tiếp theo có phải là input type="number" hay không
        if (nextInput && (nextInput.type === "number" || nextInput.type === "hidden")) {
            // Mở ô tiếp theo
            nextInput.removeAttribute("disabled");

            // Tự động đặt trỏ vào ô vừa mở
            nextInput.focus();
        }
    }
}

