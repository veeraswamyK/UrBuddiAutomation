package excelReader;

import POM.dashboardPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class emailSender {
    private static final Logger logger = LoggerFactory.getLogger(emailSender.class);
    public static void sendReport(String subject, String body, String recipient) {
        final String from = "veeraswamy.kalluri@optimworks.com";
        final String password = "rxfk yodz zvln jiww";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            logger.info("email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
