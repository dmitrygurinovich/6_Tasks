package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    public EmailSender() {}

    /**
     * @param userEmail - user's e-mail
     * @param subject - e-mail's subject
     * @param text - message's text
     */
    public void notifyUsersAboutAddingBooksDescription(String userEmail, String subject, String text) {
        final String username = "dmitry.gurinovich1989@gmail.com";
        final String password = "qWe4531689925";

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

    /**
     * @param user - user, who is suggesting to add new book
     * @param book - book
     */
    public void suggestingToAddABookToTheLibrary(User user, Book book) {
        final String username = "dmitry.gurinovich1989@gmail.com";
        final String password = "qWe4531689925";

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

            msg.setFrom(new InternetAddress("dmitry.gurinovic1989@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("dmitry.gurinovich@hotmail.com",false));
            msg.setSubject("The user " + user.getLogin() + " suggested to add a book to the library.");
            msg.setText("The user " + user.getLogin() + " suggested to add a book to the library\n\n" +
                        "Name: " + book.getName() + "\n" +
                        "Author: " + book.getAuthor() + "\n" +
                        "Year: " + book.getYear() + "\n" +
                        "Book type: " + book.getType() + "\n" +
                        (book.getDescription() != null ? "Description: " + book.getDescription() : ""));
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
