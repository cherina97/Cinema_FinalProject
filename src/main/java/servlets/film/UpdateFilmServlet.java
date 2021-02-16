package servlets.film;

import entities.Film;
import services.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;

@WebServlet("/allFilms/update")
public class UpdateFilmServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int filmId = Integer.parseInt(req.getParameter("id"));
        Film filmById = filmService.getById(filmId);
        req.setAttribute("filmById", filmById);
        req.getRequestDispatcher("/updateFilm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String filmTitle = req.getParameter("filmTitle");
        String filmTitleUK = req.getParameter("filmTitleUK");
        String description = req.getParameter("description");
        String descriptionUK = req.getParameter("descriptionUK");
        LocalTime localTime = LocalTime.parse(req.getParameter("duration"));

        filmService.updateFilm(
                new Film.Builder()
                        .withId(id)
                        .withFilmTitle(filmTitle)
                        .withFilmTitleUK(filmTitleUK)
                        .withDescription(description)
                        .withDescriptionUK(descriptionUK)
                        .withDuration(Time.valueOf(localTime))
                        .build());

        resp.sendRedirect("/cinema/allFilms");
    }
}
