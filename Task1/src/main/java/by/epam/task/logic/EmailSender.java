package by.epam.task.logic;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    public EmailSender() {}

    /**
     * @param userEmail
     * @param subject
     * @param text
     */
    public void emailSender(String userEmail, String subject, String text) throws MessagingException {
        final String username = "dmitry.gurinovich1989@gmail.com"; //gmail login
        final String password = "qWe4531689925";                   //gmail password

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("dmitry.gurinovic1989@gmail.com")); // field "from"
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail,false));
            msg.setSubject(subject);
            msg.setText(text);
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
