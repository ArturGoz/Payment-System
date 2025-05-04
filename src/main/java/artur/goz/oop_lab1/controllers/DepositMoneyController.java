package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.PaymentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepositMoneyController implements Controller {

    private final PaymentService paymentService;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String accountIdParam = req.getParameter("accountId");
        String amountParam = req.getParameter("amount");

        try {
            int accountId = Integer.parseInt(accountIdParam);
            double amount = Double.parseDouble(amountParam);

            log.info("Deposit request received: accountId={}, amount={}", accountId, amount);

            paymentService.addFunds(accountId, amount);
            log.info("Funds successfully deposited to account {}: amount {}", accountId, amount);

            resp.sendRedirect(req.getContextPath() + "/api/user");

        } catch (NumberFormatException e) {
            log.warn("Invalid input format. accountId='{}', amount='{}'. Error: {}", accountIdParam, amountParam, e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account ID or amount format.");
        } catch (Exception e) {
            log.error("Error while processing deposit for accountId={}, amount={}. Reason: {}", accountIdParam, amountParam, e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to process deposit.");
        }
    }
}
