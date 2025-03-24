package artur.goz.oop_lab1.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    @Value("${db.url}")
    private static String url;
    @Value("${db.username}")
    private static String username;
    @Value("${db.password}")
    private static String password;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
