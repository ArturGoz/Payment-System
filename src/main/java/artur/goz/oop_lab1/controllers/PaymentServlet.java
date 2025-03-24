package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.PaymentServiceImpl;
import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payments/*")
@RequiredArgsConstructor
public class PaymentServlet extends HttpServlet {
    private final PaymentService paymentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            double amount = Double.parseDouble(req.getParameter("amount"));

            paymentService.processPayment(accountId, amount);
            resp.sendRedirect("/success");
        } catch (RuntimeException | IOException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
}
