/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.MailConfig;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LinhNguyenDuc
 */
public class OtpService {
    private MailConfig mailConfig;

    public OtpService() {
        this.mailConfig = new MailConfig();
    }
    
    private Session authenEmail() {
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getEmail(), mailConfig.getPassword());
            }
        };
        Session session = Session.getInstance(mailConfig.getProperties(), auth);
        return session;
    }

    public void sendOTPViaEmail(String otp, String customerEmail, String username) {
        try {
            Message message = new MimeMessage(authenEmail());
            message.setFrom(new InternetAddress(mailConfig.getEmail(), "Group1-4 - OTP authentication"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(customerEmail)
            );
            message.setSubject("Please verify your account");
            message.setText(formatMessage(
                    username,
                    otp,
                    "Group1-4 - OTP authentication",
                    "Posts and Telecommunications Institute of Technology",
                    "Km10, Nguyen Trai Street, Ha Dong, Ha Noi"));
            
            Transport.send(message);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OtpService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(OtpService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String formatMessage(
            String username,
            String otp,
            String team,
            String party,
            String address) {
        String message = "Dear " + username + ",\n" +
                "\n" +
                "Thank you for choosing our services. To complete your account verification, please enter the one-time password (OTP) provided below:\n" +
                "\n" +
                "OTP: " + otp + "\n" +
                "\n" +
                "This OTP is valid for a limited time, and it should be used within 1 minutes. If you did not request this OTP or need any assistance, please contact our support team at linhnd.b20at109@stu.ptit.edu.vn.\n" +
                "\n" +
                "Please ensure that you do not share this OTP with anyone for security reasons. It is used for the sole purpose of verifying your account.\n" +
                "\n" +
                "Thank you for choosing us. We look forward to serving you.\n" +
                "\n" +
                "Best regards,\n" +
                team + "\n" +
                party + "\n" +
                address + "\n";

        return message;
    }
}
