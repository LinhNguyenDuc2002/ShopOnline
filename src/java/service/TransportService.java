/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.TransportDAO;
import java.util.List;
import model.Transport;

/**
 *
 * @author LinhNguyenDuc
 */
public class TransportService {
    private TransportDAO transportDAO;

    public TransportService() {
        this.transportDAO = new TransportDAO();
    }
    
    public List<Transport> getTransport() {
        return transportDAO.getTransport();
    }
    
}
