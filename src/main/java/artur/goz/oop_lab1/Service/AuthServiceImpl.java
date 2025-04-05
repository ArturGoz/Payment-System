package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.Service.interfaces.AuthService;
import artur.goz.oop_lab1.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDAO userDAO;

    @Override
    public User login(String username, String password) {
        User user = userDAO.getUserByLogin(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
