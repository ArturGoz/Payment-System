package artur.goz.oop_lab1;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.Service.UserServiceImpl;
import artur.goz.oop_lab1.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void addUser_success() {
        User user = new User();
        userService.addUser(user);
        verify(userDAO).createUser(user);
    }
}
