package servlets.film;

import entities.Film;
import entities.Genre;
import org.apache.commons.lang3.ObjectUtils;
import services.FilmService;
import services.GenreService;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Create film servlet.
 */
@WebServlet("/allFilms/admin/addFilm")
@MultipartConfig(maxFileSize = 16177215)
public class CreateFilmServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();
    private final GenreService genreService = GenreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genre> genres = genreService.readAllGenres();
        req.setAttribute("genres", genres);
        req.getRequestDispatcher("/createFilm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filmTitle = req.getParameter("filmTitle");
        String filmTitleUK = req.getParameter("filmTitleUK");
        String description = req.getParameter("description");
        String descriptionUK = req.getParameter("descriptionUK");
        String duration = req.getParameter("duration");
        Part filePart = req.getPart("poster");

        Optional<Film> filmByTitle = filmService.getFilmByTitle(filmTitle);
        String filmPresent = "Film with such title is already present";

        if (filmByTitle.isPresent()) {
            req.setAttribute("error", filmPresent);
            req.getRequestDispatcher("/createFilm.jsp").forward(req, resp);
        }

        String[] genres = req.getParameterValues("genres");
        List<Integer> genresIds = Arrays.stream(genres)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        List<Genre> genresByIds = genreService.getGenresByIds(genresIds);
        SerialBlob serialBlob = filmService.getBlobFromPart(filePart);

        if (ObjectUtils.allNotNull(filmTitle, description, duration) && !filmByTitle.isPresent()) {
            Film film = filmService.createFilm(
                    new Film.Builder()
                            .withFilmTitle(filmTitle)
                            .withFilmTitleUK(filmTitleUK)
                            .withDescription(description)
                            .withDescriptionUK(descriptionUK)
                            .withDuration(Time.valueOf(duration + ":00"))
                            .withPoster(serialBlob)
                            .build());
            filmService.setGenresForFilm(film, genresByIds);
            resp.sendRedirect("/cinema/allFilms");
            return;
        }

        req.getRequestDispatcher("/createFilm.jsp").forward(req, resp);
    }
}
