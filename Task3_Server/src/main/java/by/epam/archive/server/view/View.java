package by.epam.archive.server.view;

import by.epam.archive.server.bean.File;

public interface View {
    void print(String text);
    void print(File file);
}
