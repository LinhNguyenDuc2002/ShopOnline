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
    public static Date convertDateFromString(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date result = dateFormat.parse(date);
            return result;
        } catch (ParseException e) {
            throw new ParseException("Date format is wrong", 0);
        }
    }
    
    public static Date getDateNow() {
        return new Date();
    }
}
