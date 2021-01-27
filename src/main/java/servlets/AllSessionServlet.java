package servlets;

import entities.Session;
import services.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allSession")
public class AllSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Session> sessionList = sessionService.readAllSessions();
        req.setAttribute("sessionList", sessionList);

        req.getRequestDispatcher("allSession.jsp").forward(req, resp);
    }
}
