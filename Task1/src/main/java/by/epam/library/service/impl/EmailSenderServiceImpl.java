package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;
import by.epam.library.service.EmailSenderService;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public final class EmailSenderServiceImpl implements EmailSenderService {
    private final String USERNAME = "gurinovich.notify@gmail.com";
    private final String PASSWORD = "4531689925qWe";

    public EmailSenderServiceImpl() {

    }

    @Override
    public void notifyUsersAboutAddingBookDescription(String subject, Book book) {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();
        Properties props = System.getProperties();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserBaseService userBaseService = serviceProvider.getUserBaseService();
        Address[] usersEmails = userBaseService.getUsersEmails(UserRole.USER);

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("gurinovich.notify@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO, usersEmails);
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
    public void suggestToAddABookToTheLibrary(Book book) {
        Library library = Library.getInstance();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();
        User user = library.getAuthorizedUser();
        Properties props = System.getProperties();

        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try {
            Session session = Session.getDefaultInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(USERNAME, PASSWORD);
                        }
                    });

            Message message = new MimeMessage(session);

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
