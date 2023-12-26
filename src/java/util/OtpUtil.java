/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * @author LinhNguyenDuc
 */
public final class OtpUtil {
    public static String generateOTP() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000);
    }
}
