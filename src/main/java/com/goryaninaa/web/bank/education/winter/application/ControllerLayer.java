package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.web.bank.education.winter.controllers.AccountController;
import com.goryaninaa.winter.web.http.server.Controller;
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
    final Controller controller = new AccountController(serviceLayer.getAccountService(),
        new GenericSynchronizer<>());
    controllers.add(controller);
  }

  /* default */ List<Controller> getControllers() {
    return controllers;
  }
}
