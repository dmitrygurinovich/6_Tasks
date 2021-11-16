package by.epam.library.server.controller.impl;

import by.epam.library.server.controller.ControllerProvider;
import by.epam.library.server.controller.ServerController;

public class MainController implements ServerController {
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
    public String action(String request) {

        String[] params = request.split("&");

        ServerController authorizationController;
        ServerController serviceController;

        authorizationController = ControllerProvider.getInstance().getAuthorizationController();
        serviceController = ControllerProvider.getInstance().getServiceController();

        switch (params[0]) {
            case "authorization":
                return authorizationController.action(request);
            case "service":
                return serviceController.action(request);
            default:
                return "invalid command";
        }

    }
}
