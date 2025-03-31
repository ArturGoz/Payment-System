package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet("/block-account")
@RequiredArgsConstructor
public class BlockAccountServlet extends HttpServlet {
    private final AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        accountService.blockAccount(accountId);
        resp.sendRedirect(req.getContextPath() + "/user");
    }
}
