package com.goryaninaa.web.bank;

import com.goryaninaa.web.bank.winter.application.ApplicationAssembler;
import com.goryaninaa.winter.logger.mech.LoggingMech;
import com.goryaninaa.winter.web.http.server.assembler.HttpServer;
import java.io.IOException;
import java.util.Properties;

/**
 * This is launch point of this application. Class with main method in it.
 */
public class Application { //NOPMD - suppressed UseUtilityClass - class with main method
  /**
   * Call this method to run the application.
   *
   * @param args - current version don't support args
   * @throws IOException - if there will be some problems with starting server functionality
   */
  public static void main(final String[] args) throws IOException {
    final Properties properties = new Properties();
    properties.load(Application.class.getResourceAsStream("/config.properties"));
    LoggingMech.getInstance().apply(properties);
    LoggingMech.getInstance().startLogging();
    final ApplicationAssembler appAssembler = new ApplicationAssembler(properties);
    final HttpServer httpServer = new HttpServer(properties, appAssembler.getControllers());
    httpServer.start();
  }
}
