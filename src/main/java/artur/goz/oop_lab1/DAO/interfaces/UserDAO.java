package artur.goz.oop_lab1.DAO.interfaces;

import artur.goz.oop_lab1.models.User;

public interface UserDAO {
    User createUser(User user);
    User getUserByLogin(String login);
    User getUserById(int id);
}
