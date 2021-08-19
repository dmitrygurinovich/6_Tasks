package by.epam.library.server.controller;

public interface Controller {
    // request format: authorization username password -> AuthorizationController
    //                 service command -> ServiceController
    void action(String request);
}
