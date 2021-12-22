package by.epam.archive.client.service;

import by.epam.archive.client.service.impl.ClientServiceImpl;
import by.epam.archive.client.service.impl.ConsoleDataServiceImpl;

public class ServiceProvider {
    private final ClientService clientService = new ClientServiceImpl();
    private final ConsoleDataService consoleDataService = new ConsoleDataServiceImpl();
    private static ServiceProvider instance;

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ConsoleDataService getConsoleDataService() {
        return consoleDataService;
    }
}
