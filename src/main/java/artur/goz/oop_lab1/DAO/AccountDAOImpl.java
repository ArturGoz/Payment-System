package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.AccountDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.Account;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AccountDAOImpl implements AccountDAO {
    @Override
    public void blockAccount(int accountId, boolean block) {
        String sql = "UPDATE accounts SET blocked = ? WHERE id = ?";
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
    public void addFunds(int accountId, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
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
    public Account getAccount(int accountId) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
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
    public List<Account> getUserAccounts(int userId) {
        return List.of();
    }

    @Override
    public void updateBalance(int accountId, double amount) {

    }

    private Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setBalance(rs.getDouble("balance"));
        account.setBlocked(rs.getBoolean("blocked"));
        return account;
    }
}
