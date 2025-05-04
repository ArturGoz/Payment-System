package artur.goz.oop_lab1.controllers.front;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.Service.interfaces.CreditCardService;
import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import artur.goz.oop_lab1.controllers.Controller;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.CreditCard;
import artur.goz.oop_lab1.models.Payment;
import artur.goz.oop_lab1.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class UserController implements Controller {

    private final CreditCardService cardService;
    private final AccountService accountService;
    private final PaymentService paymentService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            log.warn("Unauthorized access attempt to /user. Redirecting to login.");
            resp.sendRedirect(req.getContextPath() + "/api/login");
            return;
        }

        log.info("Authenticated user accessing /user: {}", user.getName());

        try {
            log.debug("Fetching credit cards for user ID {}", user.getId());
            List<CreditCard> cards = cardService.getCreditCardsByUserId(user.getId());

            log.debug("Fetching accounts for user ID {}", user.getId());
            List<Account> accounts = accountService.getAccountsByUserId(user.getId());

            log.debug("Fetching payments for user ID {}", user.getId());
            List<Payment> payments = paymentService.getPaymentsByUserId(user.getId());

            req.setAttribute("user", user);
            req.setAttribute("cards", cards);
            req.setAttribute("accounts", accounts);
            req.setAttribute("payments", payments);

            log.info("Forwarding data to user.jsp for user: {}", user.getName());
            req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error("Error while loading user data for user ID {}: {}", user.getId(), e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load user data.");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
