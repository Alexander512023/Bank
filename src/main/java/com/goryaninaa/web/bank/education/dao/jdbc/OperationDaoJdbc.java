package com.goryaninaa.web.bank.education.dao.jdbc;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OperationDaoJdbc implements OperationDao {

  private final String url;
  private final String userName;
  private final String password;

  public OperationDaoJdbc(final Properties properties) {
    url = properties.getProperty("db.url");
    userName = properties.getProperty("db.username");
    password = properties.getProperty("db.password");
  }
  @Override
  public void save(final Operation oper) {
    final String sql = "INSERT INTO operations (amount, balance_before, balance_after, " +
        "performed_at, account_id, account_from_id, account_recipient_id, client_id, service, " +
        "operation_type, number_in_history)" +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection con = DriverManager.getConnection(url, userName, password);
         PreparedStatement pstmt = con.prepareStatement(sql)) {
      executeInsert(oper, pstmt);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to save operation: " + oper + " in DB.", e);
    }
  }

  @Override
  public List<Operation> findOperationsOfAccount(final int accountId) {
    final String sql = "SELECT operation_id, amount, balance_before, balance_after, " +
        "performed_at, account_id, account_from_id, account_recipient_id, client_id, service, " +
        "operation_type, number_in_history FROM operations WHERE account_id=" + accountId;
    List<Operation> operations = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(url, userName, password);
         ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      fillOperationsList(operations, resultSet);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to load list of operations for account with id: "
          + accountId + " from DB", e);
    }
    return operations;
  }

  private void fillOperationsList(List<Operation> operations, ResultSet resultSet) throws SQLException {
    while (resultSet.next()) {
      Operation oper = new Operation();
      oper.setOperationId(resultSet.getInt("operation_id"));
      oper.setAmount(resultSet.getInt("amount"));
      oper.setBalanceBefore(resultSet.getInt("balance_before"));
      oper.setBalanceAfter(resultSet.getInt("balance_after"));
      oper.setPerformedAt(resultSet.getTimestamp("performed_at").toLocalDateTime());
      Account acc = new Account();
      acc.setAccountId(resultSet.getInt("account_id"));
      oper.setAccount(acc);
      int accFromId = resultSet.getInt("account_from_id");
      if (!resultSet.wasNull()) {
        Account accFrom = new Account();
        accFrom.setAccountId(accFromId);
        oper.setAccountFrom(accFrom);
      }
      int accRecId = resultSet.getInt("account_recipient_id");
      if (!resultSet.wasNull()) {
        Account accRec = new Account();
        accRec.setAccountId(accRecId);
        oper.setAccountRecipient(accRec);
      }
      Client client = new Client();
      client.setClientId(resultSet.getInt("client_id"));
      oper.setClient(client);
      oper.setService(ServiceInitiator.valueOf(resultSet.getString("service")));
      oper.setOperationType(
          OperationType.valueOf(resultSet.getString("operation_type")));
      oper.setHistoryNumber(resultSet.getInt("number_in_history"));
      operations.add(oper);
    }
  }

  private void executeInsert(Operation oper, PreparedStatement pstmt) throws SQLException {
    pstmt.setInt(1, oper.getAmount());
    pstmt.setInt(2, oper.getBalanceBefore());
    pstmt.setInt(3, oper.getBalanceAfter());
    pstmt.setTimestamp(4, Timestamp.valueOf(oper.getPerformedAt()));
    pstmt.setInt(5, oper.getAccount().getAccountId());
    if (oper.getAccountFrom() == null) {
      pstmt.setNull(6, Types.INTEGER);
    } else {
      pstmt.setInt(6, oper.getAccountFrom().getAccountId());
    }
    if (oper.getAccountRecipient() == null) {
      pstmt.setNull(7, Types.INTEGER);
    } else {
      pstmt.setInt(7, oper.getAccountRecipient().getAccountId());
    }
    pstmt.setInt(8, oper.getClient().getClientId());
    pstmt.setString(9, oper.getService().toString());
    pstmt.setString(10, oper.getOperationType().toString());
    pstmt.setInt(11, oper.getHistoryNumber());
    pstmt.executeUpdate();
  }
}
