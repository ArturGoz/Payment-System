package artur.goz.oop_lab1.Service.interfaces;

import artur.goz.oop_lab1.models.User;

public interface AuthService {
    User login(String username, String password);
}
