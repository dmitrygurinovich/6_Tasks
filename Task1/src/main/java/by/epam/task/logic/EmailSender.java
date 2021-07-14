package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.Library;
import by.epam.task.entity.User;
import by.epam.task.entity.UserRole;
import by.epam.task.view.View;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class EmailSender {
    private final String USERNAME = "gurinovich.notify@gmail.com";
    private final String PASSWORD = "4531689925qWe";
    private static EmailSender instance;

    private EmailSender() {

    }

    public static EmailSender getInstance() {
        if (instance == null) {
            instance = new EmailSender();
        }
        return instance;
    }

    public void notifyUsersAboutAddingBooksDescription(String subject, Book book) {
        View view;
        final String SSL_FACTORY;
        Properties props;
        Session session;
        Message message;

        view = new View();
        SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props = System.getProperties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

            message = new MimeMessage(session);

            message.setFrom(new InternetAddress("gurinovich.notify@gmail.com")); // field "from"
            message.setRecipients(
                    Message.RecipientType.TO, getUsersEmail(UserRole.USER));
            message.setSubject(subject);
            message.setText("Description has been added for book: \n" +
                    "№: " + book.getId() + "\n" +
                    "Author: " + book.getAuthor() + "\n" +
                    "Name: " + book.getName() + "\n" +
                    "Year: " + book.getYear() + "\n" +
                    "Description: " + book.getDescription());
            message.setSentDate(new Date());
            Transport.send(message);
            view.print("Message sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void suggestToAddABookToTheLibrary(User user, Book book) {
        final String SSL_FACTORY;
        View view;
        Properties props;
        Session session;
        Message message;

        SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        view = new View();
        props = System.getProperties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

            message = new MimeMessage(session);

            message.setFrom(new InternetAddress("dmitry.gurinovic1989@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("dmitry.gurinovich@hotmail.com", false));
            message.setSubject("The user " + user.getLogin() + " suggested to add a book to the library.");
            message.setText("The user " + user.getLogin() + " suggested to add a book to the library\n\n" +
                    "Name: " + book.getName() + "\n" +
                    "Author: " + book.getAuthor() + "\n" +
                    "Year: " + book.getYear() + "\n" +
                    "Book type: " + book.getType() + "\n" +
                    (book.getDescription() != null ? "Description: " + book.getDescription() : ""));
            message.setSentDate(new Date());
            Transport.send(message);
            view.print("Message sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Address[] getUsersEmail(UserRole role) {
        ArrayList<String> emails;
        Address[] addresses;

        emails = new ArrayList<>();

        for (User user : Library.getInstance().getUsers()) {
            if (user.getRole().equals(role)) {
                emails.add(user.getEmail());
            }
        }

        addresses = new Address[emails.size()];

        try {
            addresses = InternetAddress.parse(emails.toString().substring(1, emails.toString().length() - 1));
        } catch (AddressException exception) {
            exception.printStackTrace();
        }

        return addresses;
    }

}
