package by.epam.library.controller;

import by.epam.library.controller.impl.PresentationController;
import by.epam.library.controller.impl.ServiceController;

public class ControllerProvider {
    private static ControllerProvider instance;
    private final Controller PRESENTATION_CONTROLLER = new PresentationController();
    private final Controller SERVICE_CONTROLLER = new ServiceController();

    private ControllerProvider() {}

    public static ControllerProvider getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    public Controller getPresentationController() {
        return PRESENTATION_CONTROLLER;
    }

    public Controller getServiceController() {
        return SERVICE_CONTROLLER;
    }
}
