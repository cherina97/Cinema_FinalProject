package utils;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendingEmail {
    private String userEmail;
    private String myHash;

    public SendingEmail(String userEmail, String myHash) {
        this.userEmail = userEmail;
        this.myHash = myHash;
    }

    public void sendMail() {
        String email = "cinemapostservice@gmail.com";
        String password = "Chervinskaya1997";

        Properties theProperties = new Properties();
        theProperties.put("mail.smtp.auth", "true");
        theProperties.put("mail.smtp.starttls.enable", "true");
        theProperties.put("mail.smtp.host", "smtp.gmail.com");
        theProperties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(theProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Email Verification Link");
            message.setText("Welcome to CinemaClub! \n" +
                    "Please, click link below to confirm email and finish registration:"
                    + "\n\nVerification Link: " + "http://localhost:8080/cinema/ActivateAccount?key1=" + userEmail + "&key2=" + myHash);

            Transport.send(message);

        } catch (Exception e) {
            //todo log
        }

    }
}
