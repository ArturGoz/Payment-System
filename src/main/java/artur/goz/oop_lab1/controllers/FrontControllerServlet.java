package artur.goz.oop_lab1.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
    private Map<String, HttpServlet> routes = new HashMap<>();

    @Override
    public void init() {
        routes.put("/login", new LoginServlet());
        routes.put("/payments/*", new PaymentServlet());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo();
        // Логіка вибору відповідного сервлета
    }
}
