package artur.goz.oop_lab1.controllers;

import artur.goz.oop_lab1.controllers.front.LoginController;
import artur.goz.oop_lab1.controllers.front.UserController;
import artur.goz.oop_lab1.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/*")
@Component
@Slf4j
public class FrontController extends HttpServlet {
    private Map<String, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        controllerMap.put("/api/login", context.getBean(LoginController.class));
        controllerMap.put("/api/user", context.getBean(UserController.class));
        controllerMap.put("/api/block-account", context.getBean(BlockAccountController.class));
        controllerMap.put("/api/deposit-money", context.getBean(DepositMoneyController.class));
        controllerMap.put("/api/make-payment", context.getBean(MakePaymentController.class));
        controllerMap.put("/api/unblock-account", context.getBean(UnBlockAccountController.class));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestUri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = requestUri.substring(contextPath.length());
        log.info("Request URI: " + requestUri);
        log.info("Context Path: " + contextPath);
        log.info("Path: " + path);

        Controller controller = controllerMap.get(path);
        if (controller != null) {
            String method = req.getMethod();
            if ("GET".equalsIgnoreCase(method)) {
                controller.doGet(req, resp);
            } else if ("POST".equalsIgnoreCase(method)) {
                controller.doPost(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }
    }
}
