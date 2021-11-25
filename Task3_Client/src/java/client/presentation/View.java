package client.presentation;

import client.bean.File;

public interface View {
    void print(File file);
    void print(String text);
}
