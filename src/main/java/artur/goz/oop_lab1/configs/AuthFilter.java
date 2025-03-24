package artur.goz.oop_lab1.configs;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                ((HttpServletResponse) response).sendRedirect("/login");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
