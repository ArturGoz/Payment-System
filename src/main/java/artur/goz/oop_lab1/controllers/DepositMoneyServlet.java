package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet("/deposit-money")
@RequiredArgsConstructor
public class DepositMoneyServlet extends HttpServlet {
    private final PaymentService paymentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        double amount = Double.parseDouble(req.getParameter("amount"));
        paymentService.addFunds(accountId, amount);
        resp.sendRedirect(req.getContextPath() + "/user");
    }
}
