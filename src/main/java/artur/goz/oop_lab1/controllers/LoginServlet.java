package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.AuthServiceImpl;
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
      //  this.authService = new AuthServiceImpl();
        log.info("Init login servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("In doGet login servlet");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = authService.login(login, password);
            log.info("User logged in " + user);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            log.info("In doPost login servlet");
            resp.sendRedirect(req.getContextPath() + "/user");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            req.setAttribute("error", "Invalid credentials");
            doGet(req, resp);
        }
    }
}
