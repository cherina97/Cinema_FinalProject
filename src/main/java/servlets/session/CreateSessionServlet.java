package servlets.session;

import entities.Film;
import entities.Session;
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
import java.sql.Date;
import java.sql.Time;

/**
 * The type Create session servlet.
 */
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

        Film filmById = filmService.getById(Integer.parseInt(filmId));
        checkFreeTime(filmById.getDuration(), Time.valueOf(startAt + ":00"));

        String errorStartTime = "The Cinema open at 9:00";
        String errorEndTime = "The Cinema close at 22:00";

        if (Time.valueOf(startAt + ":00").before(Time.valueOf("09:00" + ":00"))) {
            req.setAttribute("error", errorStartTime);
            req.getRequestDispatcher("/createSession.jsp").forward(req, resp);
        } else if (Time.valueOf(startAt + ":00").after(Time.valueOf("22:00" + ":00"))) {
            req.setAttribute("error", errorEndTime);
            req.getRequestDispatcher("/createSession.jsp").forward(req, resp);
        } else if (ObjectUtils.allNotNull(filmId, startAt, date)) {
            Session createdSession = sessionService.createSession(
                    new Session.Builder()
                            .withFilm(filmService.getById(Integer.parseInt(filmId)))
                            .withTimeStartAt(Time.valueOf(startAt + ":00"))
                            .withDate(Date.valueOf(date))
                            .build());
            sessionService.createTicketsForSession(createdSession);
            resp.sendRedirect("/cinema/allSession");
            return;
        }

        req.getRequestDispatcher("/createSession.jsp").forward(req, resp);
    }

    private void checkFreeTime(Time filmDuration, Time startAt) {


    }

}
