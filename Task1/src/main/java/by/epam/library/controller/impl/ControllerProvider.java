package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;

public class ControllerProvider implements Controller {
    private static ControllerProvider instance;
    private final Controller presentationController = new PresentationController();
    private final Controller serviceController = new ServiceController();

    private ControllerProvider() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    @Override
    public void doAction(String request) {
        String[] params = request.split("\\s+");

        switch (params[0]) {
            case "presentation":
                presentationController.doAction(params[1]);
            case "service":
                serviceController.doAction(params[1]);
        }
    }
}
