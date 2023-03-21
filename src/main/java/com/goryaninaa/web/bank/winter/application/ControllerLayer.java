package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.web.bank.model.account.Account;
import com.goryaninaa.web.bank.winter.controllers.AccountController;
import com.goryaninaa.winter.web.http.server.Controller;
import com.goryaninaa.winter.web.http.server.util.GenericSynchronizer;
import com.goryaninaa.winter.web.http.server.util.Synchronizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControllerLayer {

    private final List<Controller> controllers = new ArrayList<>();

    /* default */ ControllerLayer(ServiceLayer serviceLayer, Properties properties) {
        Controller controller = new AccountController(serviceLayer.getAccountService(),
                new GenericSynchronizer<Integer>());
        controllers.add(controller);
    }

    /* default */ List<Controller> getControllers() {
        return controllers;
    }
}
