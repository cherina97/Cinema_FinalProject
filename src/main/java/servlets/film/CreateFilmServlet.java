package servlets.film;

import entities.Film;
import org.apache.commons.lang3.ObjectUtils;
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
import java.sql.Time;

@WebServlet("/addFilm")
@MultipartConfig(maxFileSize = 16177215)
public class CreateFilmServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("createFilm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmTitle = req.getParameter("filmTitle");
        String description = req.getParameter("description");
        String duration = req.getParameter("duration");
        Part filePart = req.getPart("poster");

        SerialBlob serialBlob = filmService.getBlobFromPart(filePart);

        if (ObjectUtils.allNotNull(filmTitle, description, duration)) {
            filmService.createFilm(
                    new Film.Builder()
                            .withFilmTitle(filmTitle)
                            .withDescription(description)
                            .withDuration(Time.valueOf(duration + ":00"))
                            .withPoster(serialBlob)
                            .build());
            resp.sendRedirect("/cinema/allFilms");
            return;
        }

        req.getRequestDispatcher("createFilm.jsp").forward(req, resp);
    }
}
