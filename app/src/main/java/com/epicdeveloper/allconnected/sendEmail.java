package com.epicdeveloper.allconnected;

import android.os.StrictMode;

import java.util.Locale;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendEmail {
    //static String mailSession;
     //static String passwordSession;
     static Session session;
     static String selectedLang;
     static String support;

    public static void sendEmailMessage(String recipient, String subject, String messageSent){
        final String mailSession="allconnected@epicdevelopers.app";
        selectedLang= Locale.getDefault().getLanguage().toUpperCase();
        final String passwordSession= "Drcr1989@";
        Properties prop=new Properties();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true");

        try{
            session=Session.getInstance(prop, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSession, passwordSession);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getSupport(selectedLang)+"<Support@epicdevelopers.app>"));
            message.setSubject(subject);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setContent(messageSent,"text/html; charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String getSupport(String lang){
        if (lang.equals("ES")){
            support = "Soporte allConnected";
        }else{
            support = "allConnected Support";
        }
        return support;
    }

}