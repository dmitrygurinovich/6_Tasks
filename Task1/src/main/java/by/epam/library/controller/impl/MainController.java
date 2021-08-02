package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.controller.ControllerProvider;

public class MainController implements Controller {
    private final ControllerProvider provider = ControllerProvider.getInstance();

    @Override
    public void doAction(String request) {
        String[] params;
        params = request.split("\\s+");

        switch (params[0]) {
            case "presentation":
                provider.getPresentationController().doAction(params[1]);
            case "service":
                provider.getServiceController().doAction(params[1]);
        }
    }
}
