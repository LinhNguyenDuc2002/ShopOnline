/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author LinhNguyenDuc
 */
public class ProductService {
//    private boolean uploadImage() {
//        String uploadPath = "/path/to/your/desired/folder"; // Đường dẫn đến thư mục lưu trữ
//
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//
//        Part filePart = request.getPart("file");
//        String fileName = extractFileName(filePart);
//
//        // Lưu file vào thư mục lưu trữ
//        File file = new File(uploadPath + File.separator + fileName);
//        try (InputStream fileContent = filePart.getInputStream();
//             OutputStream os = new FileOutputStream(file)) {
//
//            int read;
//            final byte[] bytes = new byte[1024];
//            while ((read = fileContent.read(bytes)) != -1) {
//                os.write(bytes, 0, read);
//            }
//        }
//
//        // Xử lý sau khi upload thành công, ví dụ: thông báo, chuyển hướng, lưu đường dẫn vào cơ sở dữ liệu, v.v.
//    }
//
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] tokens = contentDisp.split(";");
//        for (String token : tokens) {
//            if (token.trim().startsWith("filename")) {
//                return token.substring(token.indexOf("=") + 2, token.length() - 1);
//            }
//        }
//        return "";
//    }
}
