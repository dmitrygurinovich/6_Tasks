package by.epam.archive.client.controller.impl;

import by.epam.archive.client.controller.ClientController;
import by.epam.archive.client.controller.ControllerProvider;

public class MainController implements ClientController {

    @Override
    public String action(String request) {
        String[] params;
        ClientController authorizationController;
        ClientController serviceController;
        ClientController menuController;
        ClientController errorController;

        params = request.split("&");
        authorizationController = ControllerProvider.getInstance().getAUTHORIZATION_CONTROLLER();
        serviceController = ControllerProvider.getInstance().getSERVICE_CONTROLLER();
        menuController = ControllerProvider.getInstance().getMENU_CONTROLLER();
        errorController = ControllerProvider.getInstance().getERROR_CONTROLLER();

        switch (params[0]) {
            case "authorization":
                return authorizationController.action(request);
            case "menu":
                return menuController.action(request);
            case "service":
                return serviceController.action(request);
            case "error":
                return errorController.action(request);
            default:
                return "Invalid command!";
        }
    }
}
