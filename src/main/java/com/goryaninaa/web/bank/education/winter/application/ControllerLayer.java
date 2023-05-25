package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.education.winter.controllers.AccountController;
import com.goryaninaa.web.bank.education.winter.controllers.AuthenticationController;
import com.goryaninaa.winter.web.http.server.request.handler.manager.Controller;
import com.goryaninaa.winter.web.http.server.util.GenericSynchronizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller layer of the application. It provides a list of the controllers. Assembles together
 * controller and service layers.
 */
public class ControllerLayer {

  private final List<Controller> controllers = new ArrayList<>();

  /* default */ ControllerLayer(final ServiceLayer serviceLayer) {
    final Controller accController = new AccountController(serviceLayer.getAccountService(),
        new GenericSynchronizer<>(), serviceLayer.getAccAuthorizServ());
    final Controller authController = new AuthenticationController();
    controllers.add(accController);
    controllers.add(authController);
  }

  /* default */ List<Controller> getControllers() {
    return controllers;
  }
}
