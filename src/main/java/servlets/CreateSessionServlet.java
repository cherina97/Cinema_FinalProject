package servlets;

import entities.Session;
import entities.Ticket;
import org.apache.commons.lang3.ObjectUtils;
import services.SessionService;
import services.TicketService;

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
    private final TicketService ticketService = TicketService.getInstance();


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

        if (ObjectUtils.allNotNull(filmTitle, description, startAt, duration)) {
            Session createdSession = sessionService.createSession(
                    new Session.Builder()
                            .withFilmTitle(filmTitle)
                            .withDescription(description)
                            .withTimeStartAt(Time.valueOf(startAt + ":00"))
                            .withDuration(Time.valueOf(duration + ":00"))
                            .build());
            createTicketsForSession(createdSession);
            resp.sendRedirect("/cinema/allSession");
            return;
        }

        req.getRequestDispatcher("createSession.jsp").forward(req, resp);
    }

    private void createTicketsForSession(Session createdSession) {
        int sessionId = createdSession.getId();

        for (int i = 1; i <= 5; i++) {
            ticketService.createTicket(new Ticket.Builder()
                    .withSeatNumber(i)
                    .withSessionId(sessionId)
                    .withPrice(50)
                    .build());
        }
    }
}
