package by.epam.task.server.view;

import by.epam.task.server.entity.File;

public class View {
    public View() {

    }

    public void print(File file) {
        System.out.println(file);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
