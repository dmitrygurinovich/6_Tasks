package by.epam.library.server.main;

import by.epam.library.server.controller.Controller;
import by.epam.library.server.controller.impl.MainController;

public class Main {
    public static void main(String[] args) {

        Controller mainController = MainController.getInstance();

        mainController.action("service show_files");

    }
}
