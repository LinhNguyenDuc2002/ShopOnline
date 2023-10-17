/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author LinhNguyenDuc
 */
public class DateUtil {
    public static Date convertStringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Date result = new Date(dateFormat.parse(date).getTime());
        return result;
    }
    
    public static Date getDateNow() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }
}
