package by.epam.library.server.controller;

import by.epam.library.server.controller.impl.AuthorizationController;
import by.epam.library.server.controller.impl.ServiceController;

public class ControllerProvider {
    private static ControllerProvider instance;
    private final Controller authorizationController = new AuthorizationController();
    private final Controller serviceController = new ServiceController();

    private ControllerProvider() {}

    public static ControllerProvider getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    public Controller getAuthorizationController() {
        return authorizationController;
    }

    public Controller getServiceController() {
        return serviceController;
    }
}
