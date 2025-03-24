package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.CreditCardDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAOImpl implements CreditCardDAO {
    @Override
    public void addCreditCard(CreditCard card) {
        String sql = "INSERT INTO credit_cards (card_number, user_id, account_id, expiration_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, card.getCardNumber());
            stmt.setInt(2, card.getUserId());
            stmt.setInt(3, card.getAccountId());
            stmt.setDate(4, Date.valueOf(card.getExpirationDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CreditCard> getCardsByUserId(int clientId) {
        String sql = "SELECT * FROM credit_cards WHERE client_id = ?";
        List<CreditCard> cards = new ArrayList<>();

        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cards.add(mapCreditCard(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cards;
    }

    private CreditCard mapCreditCard(ResultSet rs) throws SQLException {
        CreditCard card = new CreditCard();
        card.setCardNumber(rs.getString("card_number"));
        card.setUserId(rs.getInt("user_id"));
        card.setAccountId(rs.getInt("account_id"));
        card.setExpirationDate(rs.getDate("expiration_date").toLocalDate());
        return card;
    }
}
