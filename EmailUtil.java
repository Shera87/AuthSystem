package com.auth;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class EmailUtil {
    public static void sendOTP(String toEmail, String otp) throws Exception {
        final String fromEmail = "shreyavaradaraj78@gmail.com";
        final String password = "sqkvvftzniwvfgme";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(toEmail));
        message.setSubject("Your OTP Verification Code");
        message.setText("Your OTP is: " + otp +
            "\n\nThis OTP is valid for 5 minutes.");
        Transport.send(message);
    }
}
