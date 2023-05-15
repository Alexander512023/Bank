package com.goryaninaa.web.bank.education.db.h2;

import com.goryaninaa.winter.logger.mech.Logger;
import com.goryaninaa.winter.logger.mech.LoggingMech;
import com.goryaninaa.winter.logger.mech.StackTraceString;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This is class, that creates instance of inmemory H2 DB, that used for presentation and testing
 * purposes.
 */
public class DataBase {

  private static final Logger logger =
      LoggingMech.getLogger(H2InitilizationException.class.getCanonicalName());

  public DataBase() {
    // default constructor
  }

  public void initialize() {
    execute(readStatement("/schema.sql"));
    execute(readStatement("/data.sql"));
  }

  private void execute(final String sql) {
    try (Connection con = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1 ");
         PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.execute();
    } catch (SQLException e) {
      if (logger.isErrorEnabled()) {
        logger.error(StackTraceString.get(e));
      }
      throw new H2InitilizationException(e);
    }
  }

  private String readStatement(final String resource) {
    try (BufferedInputStream bis = new BufferedInputStream(
        Objects.requireNonNull(DataBase.class.getResourceAsStream(resource)))) {
      return new String(bis.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      if (logger.isErrorEnabled()) {
        logger.error(StackTraceString.get(e));
      }
      throw new H2InitilizationException(e);
    }
  }
}
