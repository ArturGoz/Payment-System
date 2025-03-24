package artur.goz.oop_lab1.DAO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
@Component
public class AccountDAOImpl implements AccountDAO{

    private final Connection connection;
    @Override
    public void blockAccount(int accountId, boolean block) {
        String sql = "UPDATE accounts SET blocked = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, block);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addFunds(int accountId, double amount) {

    }
}
