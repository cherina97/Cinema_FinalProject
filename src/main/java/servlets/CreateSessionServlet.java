package servlets;

import entities.Session;
import org.apache.commons.lang3.ObjectUtils;
import services.SessionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;

@WebServlet("/createSession")
public class CreateSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("createSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmTitle = req.getParameter("filmTitle");
        String description = req.getParameter("description");
        String startAt = req.getParameter("startAt");
        String duration = req.getParameter("duration");
        String tickets = req.getParameter("tickets");

        if (ObjectUtils.allNotNull(filmTitle, description, startAt, duration, tickets)) {
            sessionService.createSession(
                    new Session.Builder()
                    .withFilmTitle(filmTitle)
                    .withDescription(description)
                    .withTimeStartAt(Time.valueOf(startAt + ":00"))
                    .withDuration(Time.valueOf(duration + ":00"))
                    .withTickets(Integer.parseInt(tickets))
                    .build());
            resp.sendRedirect("/cinema/allSession");
            return;
        }

        req.getRequestDispatcher("createSession.jsp").forward(req, resp);
    }
}
