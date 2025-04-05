package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.PaymentDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.Payment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public void createPayment(Payment payment) {
        String sql = "INSERT INTO payment (accountId, amount, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payment.getAccountId());
            stmt.setDouble(2, payment.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(payment.getTimestamp()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Payment> getPaymentsByAccount(int accountId) {
        String sql = "SELECT * FROM payment WHERE accountId = ? ORDER BY timestamp DESC";
        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    payments.add(mapPayment(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    private Payment mapPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setAccountId(rs.getInt("accountId"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        return payment;
    }
}
