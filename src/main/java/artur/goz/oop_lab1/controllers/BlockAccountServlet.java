package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/block-account")
@Slf4j
@RequiredArgsConstructor
public class BlockAccountServlet extends HttpServlet {

    private final AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String accountIdParam = req.getParameter("accountId");

        try {
            int accountId = Integer.parseInt(accountIdParam);
            log.info("Received request to block account with ID: {}", accountId);

            accountService.blockAccount(accountId);
            log.info("Account successfully blocked: {}", accountId);

            resp.sendRedirect(req.getContextPath() + "/user");

        } catch (NumberFormatException e) {
            log.warn("Invalid account ID format received: {}", accountIdParam);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account ID format.");
        } catch (Exception e) {
            log.error("Error while blocking account: {}", e.getMessage(), e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to block account.");
        }
    }
}
