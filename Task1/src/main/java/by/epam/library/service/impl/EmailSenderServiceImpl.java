package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.service.EmailSenderService;
import by.epam.library.view.impl.ViewImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public final class EmailSenderServiceImpl implements EmailSenderService {
    private final String USERNAME = "gurinovich.notify@gmail.com";
    private final String PASSWORD = "4531689925qWe";
    private static EmailSenderServiceImpl instance;

    private EmailSenderServiceImpl() {

    }

    public static EmailSenderServiceImpl getInstance() {
        if (instance == null) {
            instance = new EmailSenderServiceImpl();
        }
        return instance;
    }

    @Override
    public void notifyUsersAboutAddingBookDescription(String subject, Book book) {
        ViewImpl view;
        final String SSL_FACTORY;
        Properties props;
        Session session;
        Message message;
        UserBaseServiceIml userBaseServiceIml;

        view = ViewImpl.getInstance();
        SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props = System.getProperties();
        userBaseServiceIml = UserBaseServiceIml.getInstance();

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
                    Message.RecipientType.TO, userBaseServiceIml.getUsersEmail(UserRole.USER));
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

    @Override
    public void suggestToAddABookToTheLibrary(User user, Book book) {
        final String SSL_FACTORY;
        ViewImpl view;
        Properties props;
        Session session;
        Message message;

        SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        view = ViewImpl.getInstance();
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
}
