package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.Service.PaymentService;
import lombok.RequiredArgsConstructor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/payments/*")
public class PaymentServlet extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init() {
        this.paymentService = new PaymentServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getPathInfo();

        if ("/create".equals(action)) {
            // Обробка створення платежу
        }
    }
}
