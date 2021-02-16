package servlets.session;

import entities.Session;
import services.FilmService;
import services.SessionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

@WebServlet("/allSession/update")
public class UpdateSessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();
    private final FilmService filmService = FilmService.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        getServletContext().setAttribute("weekDays", WeekDay.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sessionId = Integer.parseInt(req.getParameter("id"));
        Session sessionById = sessionService.getSessionById(sessionId);
        req.setAttribute("sessionById", sessionById);
        req.setAttribute("allFilms", filmService.readAllFilms());
        req.getRequestDispatcher("/updateSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String filmId = req.getParameter("filmId");
        LocalTime startAt = LocalTime.parse(req.getParameter("startAt"));
        String date = req.getParameter("date");

        sessionService.updateSession(
                new Session.Builder()
                        .withId(id)
                        .withFilm(filmService.getById(Integer.parseInt(filmId)))
                        .withTimeStartAt(Time.valueOf(startAt))
                        .withDate(Date.valueOf(date))
                        .build());

        resp.sendRedirect("/cinema/allSession");
    }
}
