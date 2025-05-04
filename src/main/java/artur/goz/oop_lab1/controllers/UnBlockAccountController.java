package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Component
public class UnBlockAccountController implements Controller {

    private final AccountService accountService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null || !user.getRole().contains("admin")) {
            log.warn("Unauthorized unblock attempt. Redirecting to login. User: {}",
                    user != null ? user.getName() : "null");
            resp.sendRedirect(req.getContextPath() + "/api/login");
            return;
        }

        String accountIdParam = req.getParameter("accountId");

        try {
            int accountId = Integer.parseInt(accountIdParam);
            log.info("Admin '{}' requested to unblock account ID {}", user.getName(), accountId);

            accountService.unblockAccount(accountId);
            log.info("Account successfully unblocked: ID {}", accountId);

            resp.sendRedirect(req.getContextPath() + "/api/user");

        } catch (NumberFormatException e) {
            log.warn("Invalid account ID format provided by admin '{}': '{}'",
                    user.getName(), accountIdParam);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account ID format.");
        } catch (Exception e) {
            log.error("Error while unblocking account ID {}: {}", accountIdParam, e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to unblock account.");
        }
    }
}
