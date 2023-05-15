package com.goryaninaa.web.bank.education.dao.jdbc;

import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.education.winter.repository.client.ClientDao;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import com.goryaninaa.winter.connection.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * This is simple JDBC implementation of {@link ClientDao} interface.
 */
public class ClientDaoJdbc implements ClientDao {

  private final ConnectionPool connectionPool;

  /**
   * Default constructor for this DAO class.
   *
   * @param connectionPool - source of data base connections
   */
  public ClientDaoJdbc(final ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public Optional<Client> findByPassport(final String passport) {
    final String sql = "SELECT client_id, passport, date_of_birth, first_name, last_name "
        + "FROM clients WHERE passport='" + passport + "'";
    Client client = null;
    Connection con = connectionPool.getConnection();
    try (ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      if (resultSet.next()) {
        client = createClient(resultSet);
      }
      return Optional.ofNullable(client);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to find client with passport: " + passport
          + " in DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }


  @Override
  public Optional<Client> findById(final int clientId) {
    final String sql = "SELECT client_id, passport, date_of_birth, first_name, last_name "
        + "FROM clients WHERE client_id=" + clientId;
    Client client = null;
    Connection con = connectionPool.getConnection();
    try (ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      if (resultSet.next()) {
        client = createClient(resultSet);
      }
      return Optional.ofNullable(client);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to find client with client_id: " + clientId
          + " in DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  private Client createClient(final ResultSet resultSet) throws SQLException {
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
