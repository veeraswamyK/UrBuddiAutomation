package utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

    public class emailsender {
        private static final Logger logger = LoggerFactory.getLogger(emailsender.class);

        public static void sendReport(String subject, String body, String recipient, String... attachments) {
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
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(body);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                for (String filePath : attachments) {
                    MimeBodyPart attachment = new MimeBodyPart();
                    attachment.attachFile(new File(filePath));
                    multipart.addBodyPart(attachment);
                }
                message.setContent(multipart);
                Transport.send(message);
                logger.info("email sent successfully");
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
        }
    }
