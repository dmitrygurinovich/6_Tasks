package by.epam.task.logic;

import by.epam.task.entity.Note;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class NotesBaseLogic {
    public NotesBaseLogic() {

    }

    public void writeNotesToFile() {

    }

    public void readNotesFromFile(){

    }

    public void addNote() {

    }

    public JSONObject objectToJsonObject(Note note) {
        JSONArray date = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", String.valueOf(note.getId()));
        jsonObject.put("theme", note.getTheme());

        date.put("year: " + note.getDate().get(Calendar.YEAR));
        date.put("month: " + note.getDate().get(Calendar.MONTH));
        date.put("day: " + note.getDate().get(Calendar.DAY_OF_WEEK));

        jsonObject.put("date", date);

        jsonObject.put("email", note.getEmail());
        jsonObject.put("message", note.getMessage());

        return jsonObject;
    }
}
