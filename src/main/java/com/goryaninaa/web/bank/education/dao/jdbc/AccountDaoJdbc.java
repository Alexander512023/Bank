package com.goryaninaa.web.bank.education.dao.jdbc;

import com.goryaninaa.web.bank.domain.model.account.Account;
import com.goryaninaa.web.bank.domain.model.account.AccountType;
import com.goryaninaa.web.bank.domain.model.account.State;
import com.goryaninaa.web.bank.domain.model.client.Client;
import com.goryaninaa.web.bank.education.winter.repository.account.AccountDao;
import com.goryaninaa.web.bank.exception.BankRuntimeException;
import com.goryaninaa.winter.connection.pool.ConnectionPool;
import com.goryaninaa.winter.logger.mech.Logger;
import com.goryaninaa.winter.logger.mech.LoggingMech;
import com.goryaninaa.winter.logger.mech.StackTraceString;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * This is simple JDBC implementation of {@link AccountDao} interface.
 */
public class AccountDaoJdbc implements AccountDao {

  private final Logger log
      = LoggingMech.getLogger(this.getClass().getCanonicalName());
  private final ConnectionPool connectionPool;

  /**
   * Default constructor for this DAO class.
   *
   * @param connectionPool - source of data base connections
   */
  public AccountDaoJdbc(final ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  @Override
  public void save(final Account acc) {
    final String sql = "INSERT INTO Accounts(balance, number, state, opened_at, closed_at,"
        + "client_id, account_type, term, prolongation_date)\n"
        + "VALUES (?, ?, ?, ?, NULL, ?, ?, ?, ?)";
    Connection con = connectionPool.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      assignParametersToSaveSql(acc, pstmt);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      if (log.isErrorEnabled()) {
        log.error(StackTraceString.get(e));
      }
      throw new BankRuntimeException("Failed to save account to DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  @Override
  public void update(final Account acc) {
    final String sql = "UPDATE Accounts SET balance=?, number=?, state=?, opened_at=?,"
        + "closed_at=?, client_id=?, account_type=?, term=?, prolongation_date=?\n"
        + "WHERE account_id=?";
    Connection con = connectionPool.getConnection();
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      assignParametersToUpdateSql(acc, pstmt);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      if (log.isErrorEnabled()) {
        log.error(StackTraceString.get(e));
      }
      throw new BankRuntimeException("Failed to update account in DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  @Override
  public Optional<Account> getOneByNumber(final int number) {
    final String sql = "SELECT * FROM Accounts WHERE number=" + number;
    Account acc = null;
    Connection con = connectionPool.getConnection();
    try (ResultSet resultSet = con.prepareStatement(sql).executeQuery()) {
      if (resultSet.next()) {
        acc = createAccount(resultSet);
      }
      return Optional.ofNullable(acc);
    } catch (SQLException e) {
      if (log.isErrorEnabled()) {
        log.error(StackTraceString.get(e));
      }
      throw new BankRuntimeException("Failed to find account with number: " + number + " in DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  @Override
  public Optional<Account> findById(final int accountId) {
    final String sql = "SELECT * FROM Accounts WHERE account_id=" + accountId;
    Account acc = null;
    Connection con = connectionPool.getConnection();
    try (ResultSet resultSet = con.prepareStatement(sql).executeQuery()) {
      if (resultSet.next()) {
        acc = createAccount(resultSet);
      }
      return Optional.ofNullable(acc);
    } catch (SQLException e) {
      if (log.isErrorEnabled()) {
        log.error(StackTraceString.get(e));
      }
      throw new BankRuntimeException("Failed to find account with ID: " + accountId + " in DB", e);
    } finally {
      connectionPool.releaseConnection(con);
    }
  }

  private Account createAccount(final ResultSet resultSet) throws SQLException {
    final Account acc = new Account();
    acc.setAccountId(resultSet.getInt("account_id"));
    acc.setNumber(resultSet.getInt("number"));
    acc.setBalance(resultSet.getInt("balance"));
    acc.setOpenedAt(resultSet.getTimestamp("opened_at").toLocalDateTime());
    acc.setClosedAt(resultSet.getTimestamp("closed_at") == null ? null :
        resultSet.getTimestamp("closed_at").toLocalDateTime());
    acc.setProlongationDate(resultSet.getDate("prolongation_date").toLocalDate());
    acc.setTerm(resultSet.getInt("term"));
    acc.setType(AccountType.valueOf(resultSet.getString("account_type")));
    acc.setState(State.valueOf(resultSet.getString("state")));
    final Client owner = new Client();
    owner.setClientId(resultSet.getInt("client_id"));
    acc.setOwner(owner);
    return acc;
  }

  private void assignParametersToSaveSql(
      final Account acc, final PreparedStatement pstmt) throws SQLException {
    pstmt.setInt(1, acc.getBalance());
    pstmt.setInt(2, acc.getNumber());
    pstmt.setString(3, acc.getState().toString());
    pstmt.setTimestamp(4, Timestamp.valueOf(acc.getOpenedAt()));
    pstmt.setInt(5, acc.getOwner().getClientId());
    pstmt.setString(6, acc.getType().toString());
    pstmt.setInt(7, acc.getTerm());
    pstmt.setDate(8, Date.valueOf(acc.getProlongationDate()));
  }

  private void assignParametersToUpdateSql(
      final Account acc, final PreparedStatement pstmt) throws SQLException {
    pstmt.setInt(1, acc.getBalance());
    pstmt.setInt(2, acc.getNumber());
    pstmt.setString(3, acc.getState().toString());
    pstmt.setTimestamp(4, Timestamp.valueOf(acc.getOpenedAt()));
    pstmt.setTimestamp(5, acc.getClosedAt() == null
        ? null : Timestamp.valueOf(acc.getClosedAt()));
    pstmt.setInt(6, acc.getOwner().getClientId());
    pstmt.setString(7, acc.getType().toString());
    pstmt.setInt(8, acc.getTerm());
    pstmt.setDate(9, Date.valueOf(acc.getProlongationDate()));
    pstmt.setInt(10, acc.getAccountId());
  }
}
