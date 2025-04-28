package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/make-payment")
@Slf4j
@RequiredArgsConstructor
public class MakePaymentServlet extends HttpServlet {

    private final PaymentService paymentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String accountIdToPayParam = req.getParameter("accountIdToPay");
        String accountIdToGetParam = req.getParameter("accountIdToGet");
        String amountParam = req.getParameter("amount");

        try {
            int accountIdToPay = Integer.parseInt(accountIdToPayParam);
            int accountIdToGet = Integer.parseInt(accountIdToGetParam);
            double amount = Double.parseDouble(amountParam);

            log.info("Payment request: from account {} to account {} amount {}", accountIdToPay, accountIdToGet, amount);

            paymentService.processPayment(accountIdToPay, accountIdToGet, amount);
            log.info("Payment successfully processed: {} -> {} amount {}", accountIdToPay, accountIdToGet, amount);

            resp.sendRedirect(req.getContextPath() + "/user");

        } catch (NumberFormatException e) {
            log.warn("Invalid input format: accountIdToPay='{}', accountIdToGet='{}', amount='{}'. Error: {}",
                    accountIdToPayParam, accountIdToGetParam, amountParam, e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account IDs or amount format.");
        } catch (Exception e) {
            log.error("Error during payment processing: {} -> {}, amount {}. Reason: {}",
                    accountIdToPayParam, accountIdToGetParam, amountParam, e.getMessage());
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Unable to process payment.");
        }
    }
}
