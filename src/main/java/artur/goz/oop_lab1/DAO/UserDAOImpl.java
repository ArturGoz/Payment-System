package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.User;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, login, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM clients WHERE login = ?";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
