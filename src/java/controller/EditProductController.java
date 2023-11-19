/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.mysql.cj.jdbc.Blob;
import dao.SanPhamDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.InputStream;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author Hue
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/EditProductController"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class EditProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idstring = request.getParameter("id");
        Long id = Long.parseLong(idstring);
        SanPhamDAO sp = new SanPhamDAO();
        Product a = sp.TimSP(id);
        List<Category> arr = sp.TimCategory();
        request.setAttribute("sanphamchitiet", a);
         HttpSession session = request.getSession();
        session.setAttribute("spct", a);
        request.getRequestDispatcher("fixsp.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String product_name = request.getParameter("ten-san-pham");
        String price = request.getParameter("gia");
        String available = request.getParameter("so-luong");
        String category = request.getParameter("category");
        Category a = new Category();
        a.setId(Long.parseLong(category));
        String description = request.getParameter("mo-ta");
        InputStream inputStream =  request.getPart("file").getInputStream();
        HttpSession session = request.getSession();
        Product old = (Product) session.getAttribute("spct");
        if (inputStream.available() != 0){
            old.setImage(inputStream.readAllBytes());
        }
        old.setProductName(product_name);
        old.setDescription(description);
        old.setAvailable(Long.parseLong(available));
        old.setPrice(Double.parseDouble(price));
        old.setCategory(a);
        SanPhamDAO spdao = new SanPhamDAO();
        spdao.Update(old);
        
        response.sendRedirect("home");
//        Product a = new Product(product_name,a.getId(),Double.parseDouble(price),Long.parseLong(available),,description);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
