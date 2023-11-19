function displayFileName() {
    var fileInput = document.getElementById('file');
    var fileNameDisplay = document.getElementById('fileNameDisplay');

    if (fileInput.files.length > 0) {
        fileNameDisplay.textContent = fileInput.files[0].name;
    } else {
        fileNameDisplay.textContent = 'Choose a image file';
    }
}