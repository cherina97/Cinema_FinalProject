package servlets.film;

import entities.Film;
import services.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;

/**
 * The type Update film poster.
 */
@WebServlet("/allFilms/admin/updatePoster")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateFilmPoster extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int filmId = Integer.parseInt(req.getParameter("id"));
        Film filmById = filmService.getById(filmId);
        req.setAttribute("filmById", filmById);
        req.getRequestDispatcher("/updateFilmPoster.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Part filePart = req.getPart("poster");

        SerialBlob serialBlob = filmService.getBlobFromPart(filePart);

        filmService.updatePoster(
                new Film.Builder()
                        .withId(id)
                        .withPoster(serialBlob)
                        .build());

        resp.sendRedirect("/cinema/allFilms");
    }
}
