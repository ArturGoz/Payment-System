package artur.goz.oop_lab1.controllers.front;

import artur.goz.oop_lab1.Service.interfaces.AuthService;
import artur.goz.oop_lab1.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/login")
@Slf4j
@RequiredArgsConstructor
public class LoginServlet extends HttpServlet {
    private final AuthService authService;

    @Override
    public void init() {
        log.info("LoginServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("Handling GET request to /login");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");

        log.debug("Received POST request with username: {}", login);

        try {
            User user = authService.login(login, password);
            log.info("User successfully authenticated: {}", user.getName());

            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            log.debug("Session created. User role: {}", user.getRole());

            resp.sendRedirect(req.getContextPath() + "/user");
        } catch (RuntimeException e) {
            log.warn("Authentication failed for username: {}. Reason: {}", login, e.getMessage());
            req.setAttribute("error", "Invalid credentials");
            doGet(req, resp);
        }
    }
}
