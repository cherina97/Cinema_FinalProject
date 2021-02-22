package servlets.session;

import services.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Delete session servlet.
 */
@WebServlet("/allSession/admin/delete")
public class DeleteSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sessionId = Integer.parseInt(req.getParameter("id"));
        sessionService.removeSession(sessionId);

        resp.sendRedirect("/cinema/allSession");
    }
}
