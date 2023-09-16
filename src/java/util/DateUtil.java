/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LinhNguyenDuc
 */
public class DateUtil {
    public Date convertDateFromString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng của chuỗi ngày
        
        try {
            Date result = dateFormat.parse(date); // Chuyển chuỗi thành đối tượng ngày
            return result;
        } catch (ParseException e) {
            throw new ParseException("Date format is wrong", 0);
        }
    }
    
    public Date getDateNow() {
        return null;
    }
}
