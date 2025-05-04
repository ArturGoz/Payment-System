package artur.goz.oop_lab1.configs;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length());

        log.debug("Filtering request to path: {}", path);

        if (path.equals("/api/login") || path.startsWith("/templates/") || path.startsWith("/public/")) {
            log.debug("Public path accessed: {} - allowing through", path);
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null) {
            log.info("No session found. Redirecting to login.");
            resp.sendRedirect(req.getContextPath() + "/api/login");
        } else if (session.getAttribute("user") == null) {
            log.info("Session exists but no user found in session. Redirecting to login.");
            resp.sendRedirect(req.getContextPath() + "/api/login");
        } else {
            log.debug("User authenticated. Proceeding with request to {}", path);
            chain.doFilter(request, response);
        }
    }
}
