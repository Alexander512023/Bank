package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.winter.controllers.AccountController;
import com.goryaninaa.winter.web.http.server.Controller;
import com.goryaninaa.winter.web.http.server.util.GenericSynchronizer;
import java.util.ArrayList;
import java.util.List;

public class ControllerLayer {

  private final List<Controller> controllers = new ArrayList<>();

  /* default */ ControllerLayer(ServiceLayer serviceLayer) {
    Controller controller = new AccountController(serviceLayer.getAccountService(),
        new GenericSynchronizer<>());
    controllers.add(controller);
  }

  /* default */ List<Controller> getControllers() {
    return controllers;
  }
}
