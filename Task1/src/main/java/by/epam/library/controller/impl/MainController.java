package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.controller.ControllerProvider;

public class MainController implements Controller {
    @Override
    public void doAction(String request) {
        ControllerProvider controllerProvider = ControllerProvider.getInstance();
        Controller presentationController = controllerProvider.getPresentationController();
        Controller serviceController = controllerProvider.getServiceController();
        String[] params = request.split("\\s+");

        switch (params[0]) {
            case "presentation":
                presentationController.doAction(params[1]);
                break;
            case "service":
                serviceController.doAction(params[1]);
                break;
        }
    }
}