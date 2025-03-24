package artur.goz.oop_lab1.Service;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.Service.interfaces.UserService;
import artur.goz.oop_lab1.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public void addUser(User user) {
        userDAO.createUser(user);
    }
}
