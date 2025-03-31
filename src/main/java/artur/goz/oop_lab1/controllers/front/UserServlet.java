package artur.goz.oop_lab1.controllers.front;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
import artur.goz.oop_lab1.Service.interfaces.CreditCardService;
import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import artur.goz.oop_lab1.models.Account;
import artur.goz.oop_lab1.models.CreditCard;
import artur.goz.oop_lab1.models.Payment;
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
import java.util.List;


@WebServlet("/user")
@Slf4j
@RequiredArgsConstructor
public class UserServlet extends HttpServlet {
    private final CreditCardService cardService;
    private final AccountService accountService;
    private final PaymentService paymentService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Отримання сесії та перевірка авторизації
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Отримання даних користувача
        List<CreditCard> cards = cardService.getCreditCardsByUserId(user.getId());
        List<Account> accounts = accountService.getAccountsByUserId(user.getId());
        List<Payment> payments = paymentService.getPaymentsByUserId(user.getId());

        // Передача даних у запит
        req.setAttribute("user", user);
        req.setAttribute("cards", cards);
        req.setAttribute("accounts", accounts);
        req.setAttribute("payments", payments);

        // Перенаправлення на JSP-сторінку
        req.getRequestDispatcher("/WEB-INF/views/user.jsp").forward(req, resp);
    }

}
