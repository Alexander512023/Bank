package com.goryaninaa.web.bank.education.dao.jdbc;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ClientDaoJdbc implements ClientDao {

  private final String url;
  private final String userName;
  private final String password;

  public ClientDaoJdbc(final Properties properties) {
    url = properties.getProperty("db.url");
    userName = properties.getProperty("db.username");
    password = properties.getProperty("db.password");
  }
  @Override
  public Optional<Client> findByPassport(String passport) {
    final String sql = "SELECT * FROM clients WHERE passport='" + passport + "'";
    Client client = null;
    try (Connection con = DriverManager.getConnection(url, userName, password);
        ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      if (resultSet.next()) {
        client = createClient(resultSet);
      }
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to find client with passport: " + passport +
          " in DB", e);
    }
    return Optional.ofNullable(client);
  }

  @Override
  public Optional<Client> findById(int clientId) {
    final String sql = "SELECT * FROM clients WHERE client_id=" + clientId;
    Client client = null;
    try (Connection con = DriverManager.getConnection(url, userName, password);
         ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      if (resultSet.next()) {
        client = createClient(resultSet);
      }
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to find client with client_id: " + clientId +
          " in DB", e);
    }
    return Optional.ofNullable(client);
  }

  private Client createClient(ResultSet resultSet) throws SQLException {
    Client client;
    client = new Client();
    client.setClientId(resultSet.getInt("client_id"));
    client.setPassport(resultSet.getString("passport"));
    client.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
    client.setFirstName(resultSet.getString("first_name"));
    client.setLastName(resultSet.getString("last_name"));
    return client;
  }

}
