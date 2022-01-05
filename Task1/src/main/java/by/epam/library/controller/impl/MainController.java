package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.controller.ControllerProvider;

public class MainController implements Controller {
    private final ControllerProvider CONTROLLER_PROVIDER = ControllerProvider.getInstance();
    private final Controller PRESENTATION_CONTROLLER = CONTROLLER_PROVIDER.getPresentationController();
    private final Controller SERVICE_CONTROLLER = CONTROLLER_PROVIDER.getServiceController();

    @Override
    public void doAction(String request) {
        String[] params = request.split("\\s+");

        switch (params[0]) {
            case "presentation":
                PRESENTATION_CONTROLLER.doAction(params[1]);
                break;
            case "service":
                SERVICE_CONTROLLER.doAction(params[1]);
                break;
        }
    }
}