package by.epam.library.server.view;

import by.epam.library.server.bean.File;

public interface View {
    void print(String text);
    void print(File file);
}
