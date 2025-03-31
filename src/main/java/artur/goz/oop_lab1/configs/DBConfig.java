package artur.goz.oop_lab1.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConfig {
    private static String url;
    private static String username;
    private static String password;

    @Value("${db.url}")
    public void setUrl(String url) {
        DBConfig.url = url;
    }

    @Value("${db.username}")
    public void setUsername(String username) {
        DBConfig.username = username;
    }

    @Value("${db.password}")
    public void setPassword(String password) {
        DBConfig.password = password;
    }

    public static Connection connect() throws SQLException {
        if (url == null || username == null || password == null) {
            throw new IllegalStateException("Database configuration not initialized. Check property values.");
        }
        return DriverManager.getConnection(url, username, password);
    }
}
