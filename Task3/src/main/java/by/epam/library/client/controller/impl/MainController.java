package by.epam.library.client.controller.impl;

import by.epam.library.client.controller.ClientController;
import by.epam.library.client.controller.ControllerProvider;

public class MainController implements ClientController {

    @Override
    public String action(String request) {
        String[] params;
        ClientController authorizationController;
        ClientController serviceController;

        params = request.split("\\s+");
        authorizationController = ControllerProvider.getInstance().getAUTHORIZATION_CONTROLLER();
        serviceController = ControllerProvider.instance.getSERVICE_CONTROLLER();

        switch (params[0]) {
            case "authorization":
                return authorizationController.action(request);
            case "service":
                return serviceController.action(request);
            default:
                return "Invalid command!";
        }
    }
}
