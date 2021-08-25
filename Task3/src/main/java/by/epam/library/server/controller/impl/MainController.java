package by.epam.library.server.controller.impl;

import by.epam.library.server.controller.Controller;
import by.epam.library.server.controller.ControllerProvider;

public class MainController implements by.epam.library.server.controller.Controller {
    private static MainController instance;

    private MainController() {
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    @Override
    public void action(String request) {
        String[] params = request.split("\\s+");

        Controller authorizationController;
        Controller serviceController;

        authorizationController = ControllerProvider.getInstance().getAuthorizationController();
        serviceController = ControllerProvider.getInstance().getServiceController();

        switch (params[0]) {
            case "authorization":
                authorizationController.action(request);
                break;
            case "service":
                serviceController.action(request);
                break;
        }
    }
}
