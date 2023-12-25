/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bill;
import model.Product;
import model.User;
import model.detail_order;
import model.listData;
import service.BillService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "OrderDetailsProduct", urlPatterns = {"/OrderDetailsProduct"})
public class BillController extends HttpServlet {
    private ProductService productService;
    private BillService billDao = new BillService();
    private UserDAO userDAO = new UserDAO();
    
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
         super.init();
         productService = new ProductService();
         userService = new UserService();
    }
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
        String role = request.getParameter("role");
        
        User user = userService.getCurrentUser(request);
        request.setAttribute("user", user);
        List<listData> data = new ArrayList<>();
        List<Bill> listBill = billDao.FindAllOrder((int) user.getId());
        if(listBill != null){
            
            for(Bill bill : listBill){
                
                Bill bills = new Bill();
                
                List<Product> productList = new ArrayList<>();
                List<detail_order> listDetai = billDao.FindAllDetailsOrder((int) bill.getId());
                if(listDetai != null){
                    int sum = 0;
                    for(detail_order details : listDetai){
                        
                        Product prooduct = productService.getProduct((long)details.getProduct_id());
                        if(prooduct != null){
                            productList.add(prooduct);
                            sum += details.getQuantity() * prooduct.getPrice();
                        }
                    }
                }
                User findOne = userDAO.getUser(bill.getUser().getId());
                bills.setId(bill.getId());
                bills.setUser(findOne);
                bills.setOrderDate(bill.getOrderDate());
                bills.setStatus(bill.isStatus());
                bills.setDeliveryAddress(bill.getDeliveryAddress());
                listData listdata = new listData(bills, productList,0);
                data.add(listdata);
            }
            
        }
        
        request.setAttribute("sanpham", data);
        
        if(role != null && role.equals("ADMIN")) {
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("buyhistory.jsp").forward(request, response);
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
        String role = request.getParameter("role");
        
        User user = userService.getCurrentUser(request);
        request.setAttribute("user", user);
        List<listData> data = new ArrayList<>();
        List<Bill> listBill = billDao.FindAllOrder((int) user.getId());
        
        if(listBill != null){
            
            for(Bill bill : listBill){
                int sum = 0;
                Bill bills = new Bill();
                
                List<Product> productList = new ArrayList<>();
                List<detail_order> listDetai = billDao.FindAllDetailsOrder((int) bill.getId());
                if(listDetai != null){
                    for(detail_order details : listDetai){
                        Product prooduct = productService.getProduct((long)details.getProduct_id());
                        if(prooduct != null){
                            productList.add(prooduct);
                            sum += details.getQuantity() * prooduct.getPrice();
                        }
                    }
                }
                User findOne = userDAO.getUser(bill.getUser().getId());
                bills.setId(bill.getId());
                bills.setUser(findOne);
                bills.setOrderDate(bill.getOrderDate());
                bills.setStatus(bill.isStatus());
                bills.setDeliveryAddress(bill.getDeliveryAddress());
                listData listdata = new listData(bills, productList, sum);
                data.add(listdata);
                
            }
            
        }
        
        request.setAttribute("sanpham", data);
        if(role != null && role.equals("ADMIN")) {
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("buyhistory.jsp").forward(request, response);
        }
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
