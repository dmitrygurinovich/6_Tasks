package by.epam.library.client.controller;

import by.epam.library.client.controller.impl.AuthorizationController;
import by.epam.library.client.controller.impl.MenuController;
import by.epam.library.client.controller.impl.ServiceController;

public class ControllerProvider {
    private static ControllerProvider instance;
    private final ClientController AUTHORIZATION_CONTROLLER = new AuthorizationController();
    private final ClientController SERVICE_CONTROLLER = new ServiceController();
    private final ClientController MENU_CONTROLLER = new MenuController();

    private ControllerProvider() {}

    public static ControllerProvider getInstance() {
        if (instance == null) {
            instance = new ControllerProvider();
        }
        return instance;
    }

    public ClientController getAUTHORIZATION_CONTROLLER() {
        return AUTHORIZATION_CONTROLLER;
    }

    public ClientController getSERVICE_CONTROLLER() {
        return SERVICE_CONTROLLER;
    }

    public ClientController getMENU_CONTROLLER() {
        return MENU_CONTROLLER;
    }
}
