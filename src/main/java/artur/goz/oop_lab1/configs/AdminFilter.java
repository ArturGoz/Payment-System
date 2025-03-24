package artur.goz.oop_lab1.configs;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// AdminFilter.java
@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
        try {
            HttpSession session = ((HttpServletRequest) req).getSession(false);
            if (session != null && "ADMIN".equals(session.getAttribute("role"))) {
                chain.doFilter(req, res);
            } else {
                ((HttpServletResponse) res).sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
