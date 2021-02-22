package servlets.session;

import entities.Session;
import services.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type All session servlet.
 */
@WebServlet("/allSession")
public class AllSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmTitle = "filmTitle";
        String freePlace = "freePlace";
        String date = "date";
        String allSession = "allSession";

        if (req.getParameter("sortBy") == null || date.equals(req.getParameter("sortBy"))) {
            req.setAttribute("sortBy", date);
            List<Session> sessionsFromNow = sessionService.readAllFromNow();
            req.setAttribute("allSession", sessionsFromNow);

        } else if ("filmTitle".equals(req.getParameter("sortBy"))) {
            req.setAttribute("sortBy", filmTitle);
            List<Session> sessionOrderByFilm = sessionService.readAllOrderByFilm();
            req.setAttribute("allSession", sessionOrderByFilm);

        } else if ("freePlace".equals(req.getParameter("sortBy"))) {
            req.setAttribute("sortBy", freePlace);
            List<Session> sessionOrderBySeats = sessionService.readAllOrderByFreeSeats();
            req.setAttribute("allSession", sessionOrderBySeats);

        } else if ("allSession".equals(req.getParameter("sortBy"))) {
            req.setAttribute("sortBy", allSession);
            List<Session> allSessions = sessionService.readAllSessions();
            req.setAttribute("allSession", allSessions);
        }

      req.getRequestDispatcher("allSession.jsp").forward(req, resp);
    }
}
