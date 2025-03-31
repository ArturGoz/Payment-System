package artur.goz.oop_lab1;

import artur.goz.oop_lab1.DAO.interfaces.UserDAO;
import artur.goz.oop_lab1.Service.AuthServiceImpl;
import artur.goz.oop_lab1.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private UserDAO userDAO;
    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void login_success() {
        String username = "test";
        String password = "pass";
        User user = new User();
        user.setPassword(password);

        when(userDAO.getUserByLogin(username)).thenReturn(user);

        User result = authService.login(username, password);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userDAO).getUserByLogin(username);
    }

    @Test
    void login_wrongPassword_returnsNull() {
        String username = "test";
        String password = "pass";
        User user = new User();
        user.setPassword("wrong");

        when(userDAO.getUserByLogin(username)).thenReturn(user);

        User result = authService.login(username, password);

        assertNull(result);
        verify(userDAO).getUserByLogin(username);
    }
}

