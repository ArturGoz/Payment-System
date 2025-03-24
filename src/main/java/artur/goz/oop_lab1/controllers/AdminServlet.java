package artur.goz.oop_lab1.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
/*    private BlockRequestService blockRequestService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getPathInfo();
        if ("/dashboard".equals(action)) {
            List<BlockRequest> requests = blockRequestService.getPendingRequests();
            req.setAttribute("requests", requests);
            req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
        }
    }*/
}
