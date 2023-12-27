/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LinhNguyenDuc
 */
public class MailConfig {
    private String email;
    
    private String password;
    
    private Properties properties;
    
    public MailConfig() {
        Properties properties = new Properties();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app-config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String smtpHost = properties.getProperty("SMTP_HOST");
        String smtpPort = properties.getProperty("SMTP_PORT");
        String smtpAuth = properties.getProperty("SMTP_AUTH");
        String smtpStartTLSEnable = properties.getProperty("SMTP_START_TLS_ENABLE");
        
        this.email = properties.getProperty("OUTLOOK_EMAIL");
        this.password = properties.getProperty("OUTLOOK_PASSWORD");
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtpHost);
        prop.put("mail.smtp.port", smtpPort);
        prop.put("mail.smtp.auth", smtpAuth);
        prop.put("mail.smtp.starttls.enable", smtpStartTLSEnable); //TLS

        this.properties = prop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
