package by.epam.note.service.impl;

import by.epam.note.bean.Note;
import by.epam.note.service.JsonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class JsonServiceImpl implements JsonService {
    public JsonServiceImpl() {}

    @Override
    public String objectToJsonObject(ArrayList<Note> notes) {
        GsonBuilder builder;
        Gson gson;

        builder = new GsonBuilder();

        gson = builder
                .setPrettyPrinting()
                .create();

        return gson.toJson(notes);
    }
}
