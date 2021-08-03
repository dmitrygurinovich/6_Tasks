package by.epam.library.controller;

import by.epam.library.controller.impl.PresentationController;
import by.epam.library.controller.impl.ServiceController;

public class ControllerProvider {
    private static ControllerProvider instance;
    private final Controller presentationController = new PresentationController();
    private final Controller serviceController = new ServiceController();

    private ControllerProvider() {}

    public static ControllerProvider getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    public Controller getPresentationController() {
        return presentationController;
    }

    public Controller getServiceController() {
        return serviceController;
    }
}
