package artur.goz.oop_lab1.DAO;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.configs.DBConfig;
import artur.goz.oop_lab1.models.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO user (name, login, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();

            // Retrieve the auto-generated user ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1)); // Set the generated ID to the user object
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM user WHERE login = ?";
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
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection conn = DBConfig.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
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
