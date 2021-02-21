package servlets.session;

import entities.Session;
import entities.Ticket;
import org.apache.commons.lang3.ObjectUtils;
import services.FilmService;
import services.SessionService;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/allSession/admin/createSession")
public class CreateSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();
    private final TicketService ticketService = TicketService.getInstance();
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allFilms", filmService.readAllFilms());
        req.getRequestDispatcher("/createSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmId = req.getParameter("filmId");
        String startAt = req.getParameter("startAt");
        String date = req.getParameter("date");

        if (ObjectUtils.allNotNull(filmId, startAt, date)) {
            Session createdSession = sessionService.createSession(
                    new Session.Builder()
                            .withFilm(filmService.getById(Integer.parseInt(filmId)))
                            .withTimeStartAt(Time.valueOf(startAt + ":00"))
                            .withDate(Date.valueOf(date))
                            .build());
            createTicketsForSession(createdSession);
            resp.sendRedirect("/cinema/allSession");
            return;
        }

        req.getRequestDispatcher("/createSession.jsp").forward(req, resp);
    }

    private void createTicketsForSession(Session createdSession) {
        int sessionId = createdSession.getId();

        for (int i = 1; i <= 72; i++) {
            ticketService.createTicket(new Ticket.Builder()
                    .withSeatNumber(i)
                    .withSessionId(sessionId)
                    .withPrice(BigDecimal.valueOf(50))
                    .build());
        }
    }
}
