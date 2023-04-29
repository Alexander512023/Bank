package com.goryaninaa.web.bank.education.winter.application;

import com.goryaninaa.winter.web.http.server.Controller;
import java.util.List;
import java.util.Properties;

/**
 * This is top level of dependencies assembling class. Instance of this class could be created in
 * main methods in order to assemble the whole application.
 */
public class ApplicationAssembler {

  private final List<Controller> controllers;

  /**
   * Constructor that fully assembles all layers together. Receives properties that will be passed
   * further to its consumers.
   *
   * @param properties - properties that need to be passed at the entrance of application
   */
  public ApplicationAssembler(final Properties properties) {
    final DataAccessLayer dataAccessLayer = new DataAccessLayer(properties);
    final CacheLayer cacheLayer = new CacheLayer(dataAccessLayer, properties);
    final RepositoryLayer repositoryLayer = new RepositoryLayer(cacheLayer, dataAccessLayer);
    final ServiceLayer serviceLayer = new ServiceLayer(repositoryLayer);
    final ControllerLayer controllerLayer = new ControllerLayer(serviceLayer);
    controllers = controllerLayer.getControllers();
  }

  public List<Controller> getControllers() {
    return controllers;
  }
}
