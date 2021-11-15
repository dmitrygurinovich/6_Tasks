package by.epam.library.server.controller;

import by.epam.library.server.controller.impl.AuthorizationController;
import by.epam.library.server.controller.impl.ServiceController;

public class ControllerProvider {
    private static ControllerProvider instance;
    private final ServerController authorizationController = new AuthorizationController();
    private final ServerController serviceController = new ServiceController();

    private ControllerProvider() {}

    public static ControllerProvider getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    public ServerController getAuthorizationController() {
        return authorizationController;
    }

    public ServerController getServiceController() {
        return serviceController;
    }
}
