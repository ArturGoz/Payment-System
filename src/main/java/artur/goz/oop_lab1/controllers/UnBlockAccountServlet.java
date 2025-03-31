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

import java.io.IOException;

@WebServlet("/unblock-account")
@RequiredArgsConstructor
public class UnBlockAccountServlet extends HttpServlet {
    private final AccountService accountService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().contains("admin")) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int accountId = Integer.parseInt(req.getParameter("accountId"));
        accountService.blockAccount(accountId);
        resp.sendRedirect(req.getContextPath() + "/user");
    }
}
