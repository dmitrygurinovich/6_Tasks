package by.epam.note.bean;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Note {
    private int id;
    private String theme;
    private String email;
    private String message;
    private GregorianCalendar date;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

    public Note() {

    }

    public Note(int id, String theme, String email, String message, GregorianCalendar date) {
        this.id = id;
        this.theme = theme;
        this.email = email;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(theme, note.theme)
                && Objects.equals(email, note.email)
                && Objects.equals(message, note.message)
                && Objects.equals(date, note.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, theme, email, message, date);
    }

    @Override
    public String toString() {
        return  "№: " + id + "\n" +
                "Theme: " + theme + "\n" +
                "Date: " + dateFormat.format(date.getTime()) + "\n" +
                "E-mail: " + email + "\n" +
                "Message: \n" + message + "\n";
    }
}
