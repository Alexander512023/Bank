package com.goryaninaa.web.bank.winter.application;

import com.goryaninaa.winter.web.http.server.Controller;
import java.util.List;
import java.util.Properties;

public class ApplicationAssembler {

  private final List<Controller> controllers;

  public ApplicationAssembler(Properties properties) {
    DaoLayer daoLayer = new DaoLayer();
    CacheLayer cacheLayer = new CacheLayer(daoLayer, properties);
    RepositoryLayer repositoryLayer = new RepositoryLayer(cacheLayer, daoLayer);
    ServiceLayer serviceLayer = new ServiceLayer(repositoryLayer);
    ControllerLayer controllerLayer = new ControllerLayer(serviceLayer);
    controllers = controllerLayer.getControllers();
  }

  public List<Controller> getControllers() {
    return controllers;
  }
}
