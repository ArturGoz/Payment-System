package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.Account;
import org.springframework.stereotype.Component;

import java.sql.*;


@Component
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void blockAccount(int accountId, boolean block) {
        String sql = "UPDATE account SET blocked = ? WHERE id = ?";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, block);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account getAccount(int accountId) {
        String sql = "SELECT * FROM account WHERE id = ?";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapAccount(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public void updateBalance(int accountId, double amount) {
        String sql = "UPDATE account SET balance = balance + ? WHERE id = ?";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account createAccount(Account account) {
        String sql = "INSERT INTO account (balance, blocked) VALUES (?, ?)";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setBoolean(2, account.isBlocked());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }

            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setBalance(rs.getDouble("balance"));
        account.setBlocked(rs.getBoolean("blocked"));
        return account;
    }
}
