package artur.goz.oop_lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
    private Map<String, HttpServlet> routes = new HashMap<>();

    @Autowired
    private LoginServlet loginServlet;

    @Autowired
    private PaymentServlet paymentServlet;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        routes.put("/login", loginServlet);
        routes.put("/payments/*", paymentServlet);
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getPathInfo();
        // Логіка вибору відповідного сервлета
    }
}
