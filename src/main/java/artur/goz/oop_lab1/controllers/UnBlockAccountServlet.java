package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
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

@WebServlet("/unblock-account")
@Slf4j
@RequiredArgsConstructor
public class UnBlockAccountServlet extends HttpServlet {

    private final AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null || !user.getRole().contains("admin")) {
            log.warn("Unauthorized unblock attempt. Redirecting to login. User: {}",
                    user != null ? user.getName() : "null");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String accountIdParam = req.getParameter("accountId");

        try {
            int accountId = Integer.parseInt(accountIdParam);
            log.info("Admin '{}' requested to unblock account ID {}", user.getName(), accountId);

            accountService.unblockAccount(accountId);
            log.info("Account successfully unblocked: ID {}", accountId);

            resp.sendRedirect(req.getContextPath() + "/user");

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
