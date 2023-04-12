package com.goryaninaa.web.bank;

import com.goryaninaa.web.bank.winter.application.ApplicationAssembler;
import com.goryaninaa.winter.logger.mech.LoggingMech;
import com.goryaninaa.winter.web.http.server.assembler.HttpServer;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(App.class.getResourceAsStream("/config.properties"));
        LoggingMech.getInstance().apply(properties);
        LoggingMech.getInstance().startLogging();
        ApplicationAssembler applicationAssembler = new ApplicationAssembler(properties);
        HttpServer httpServer = new HttpServer(properties, applicationAssembler.getControllers());
        httpServer.start();
    }
}
