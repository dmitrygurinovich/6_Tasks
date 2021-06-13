package by.epam.task.entity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note {
    private String theme;
    private String email;
    private String message;
    private GregorianCalendar date;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");

    public Note() {

    }

    /**
     * @param theme - theme
     * @param email - e-mail
     * @param message - message
     * @param date - date
     */
    public Note(String theme, String email, String message, GregorianCalendar date) {
        this.theme = theme;
        this.email = email;
        this.message = message;
        this.date = date;
    }

    @Override
    public String toString() {
        return  "Theme: " + theme + "\n" +
                "E-mail: " + email + "\n" +
                "Date: "
                + date.get(Calendar.DAY_OF_MONTH) + ":"
                + date.get(Calendar.MONTH) + ":"
                + date.get(Calendar.YEAR) + "\n" +
                "Message: " + message;
    }
}
