package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.interfaces.AccountService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {
    private AccountService accountService;

/*    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getPathInfo();
        if ("/block".equals(action)) {
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            accountService.blockAccount(accountId);
            resp.sendRedirect(req.getContextPath() + "/account");
        }
    }*/
}
