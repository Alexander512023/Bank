package com.goryaninaa.web.bank.education.dao.jdbc;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.domain.model.operation.Operation;
import com.goryaninaa.web.bank.domain.model.operation.OperationType;
import com.goryaninaa.web.bank.domain.model.operation.ServiceInitiator;
import com.goryaninaa.web.bank.education.winter.repository.operation.OperationDao;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import com.goryaninaa.winter.connection.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * This is simple JDBC implementation of {@link OperationDao} interface.
 */
public class OperationDaoJdbc implements OperationDao {

  private final ConnectionPool connectionPool;

  /**
   * Default constructor for this DAO class.
   *
   * @param connectionPool - source of data base connections
   */
  public OperationDaoJdbc(final ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void save(final Operation oper) {
    final String sql = "INSERT INTO operations (amount, balance_before, balance_after, "
        + "performed_at, account_id, account_from_id, account_recipient_id, client_id, service, "
        + "operation_type, number_in_history)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection con = connectionPool.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      executeInsert(oper, pstmt);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to save operation: " + oper + " in DB.", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  @Override
  public List<Operation> findOperationsOfAccount(final int accountId) {
    final String sql = "SELECT operation_id, amount, balance_before, balance_after, "
        + "performed_at, account_id, account_from_id, account_recipient_id, client_id, service, "
        + "operation_type, number_in_history FROM operations WHERE account_id=" + accountId;
    Connection con = connectionPool.getConnection();
    try (ResultSet resultSet = con.createStatement().executeQuery(sql)) {
      return prepareOperationsList(resultSet);
    } catch (SQLException e) {
      throw new BankRuntimeException("Failed to load list of operations for account with id: "
          + accountId + " from DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  private List<Operation> prepareOperationsList(final ResultSet resultSet)
      throws SQLException {
    final List<Operation> operations = new ArrayList<>();
    while (resultSet.next()) {
      final Operation oper = new Operation(); //NOPMD - suppressed AvoidInstantiatingObjectsInLoops
      setFlatRequisites(resultSet, oper);
      setAccount(resultSet, oper);
      setAccountFrom(resultSet, oper);
      setAccountRecipient(resultSet, oper);
      setClient(resultSet, oper);
      operations.add(oper);
    }
    return operations;
  }

  private void setClient(ResultSet resultSet, Operation oper) throws SQLException {
    final Client client = new Client(); //NOPMD - suppressed AvoidInstantiatingObjectsInLoops
    client.setClientId(resultSet.getInt("client_id"));
    oper.setClient(client);
  }

  private void setAccount(ResultSet resultSet, Operation oper) throws SQLException {
    final Account acc = new Account(); //NOPMD - suppressed AvoidInstantiatingObjectsInLoops
    acc.setAccountId(resultSet.getInt("account_id"));
    oper.setAccount(acc);
  }

  private void setFlatRequisites(ResultSet resultSet, Operation oper) throws SQLException {
    oper.setOperationId(resultSet.getInt("operation_id"));
    oper.setAmount(resultSet.getInt("amount"));
    oper.setBalanceBefore(resultSet.getInt("balance_before"));
    oper.setBalanceAfter(resultSet.getInt("balance_after"));
    oper.setPerformedAt(resultSet.getTimestamp("performed_at").toLocalDateTime());
    oper.setService(ServiceInitiator.valueOf(resultSet.getString("service")));
    oper.setOperationType(
        OperationType.valueOf(resultSet.getString("operation_type")));
    oper.setHistoryNumber(resultSet.getInt("number_in_history"));
  }

  private void setAccountRecipient(ResultSet resultSet, Operation oper) throws SQLException {
    final int accRecId = resultSet.getInt("account_recipient_id");
    if (!resultSet.wasNull()) {
      final Account accRec = new Account(); //NOPMD - suppressed AvoidInstantiatingObjectsInLoops
      accRec.setAccountId(accRecId);
      oper.setAccountRecipient(accRec);
    }
  }

  private void setAccountFrom(ResultSet resultSet, Operation oper) throws SQLException {
    final int accFromId = resultSet.getInt("account_from_id");
    if (!resultSet.wasNull()) {
      final Account accFrom = new Account(); //NOPMD - suppressed AvoidInstantiatingObjectsInLoops
      accFrom.setAccountId(accFromId);
      oper.setAccountFrom(accFrom);
    }
  }

  private void executeInsert(final Operation oper, final PreparedStatement pstmt)
      throws SQLException {
    pstmt.setInt(1, oper.getAmount());
    pstmt.setInt(2, oper.getBalanceBefore());
    pstmt.setInt(3, oper.getBalanceAfter());
    pstmt.setTimestamp(4, Timestamp.valueOf(oper.getPerformedAt()));
    pstmt.setInt(5, oper.getAccount().getAccountId());
    setAccountToStatement(oper.getAccountFrom(), pstmt, 6);
    setAccountToStatement(oper.getAccountRecipient(), pstmt, 7);
    pstmt.setInt(8, oper.getClient().getClientId());
    pstmt.setString(9, oper.getService().toString());
    pstmt.setString(10, oper.getOperationType().toString());
    pstmt.setInt(11, oper.getHistoryNumber());
    pstmt.executeUpdate();
  }

  private void setAccountToStatement(Account acc, PreparedStatement pstmt, int parameterIndex)
      throws SQLException {
    if (acc == null) {
      pstmt.setNull(parameterIndex, Types.INTEGER);
    } else {
      pstmt.setInt(parameterIndex, acc.getAccountId());
    }
  }
}
