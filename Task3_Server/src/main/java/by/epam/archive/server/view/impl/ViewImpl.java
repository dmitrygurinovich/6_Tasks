package by.epam.archive.server.view.impl;

import by.epam.archive.server.bean.File;
import by.epam.archive.server.view.View;

public class ViewImpl implements View {
    private static ViewImpl instance;

    private ViewImpl() {}

    public static ViewImpl getInstance() {
        if (instance == null) {
            instance = new ViewImpl();
        }
        return instance;
    }

    public void print(File file) {
        System.out.println(file);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
