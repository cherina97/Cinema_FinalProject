package servlets.genre;

import entities.Genre;
import org.apache.commons.lang3.ObjectUtils;
import services.GenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allGenres/admin/addGenre")
public class CreateGenreServlet extends HttpServlet {
    private final GenreService genreService = GenreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/createGenre.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genreName = req.getParameter("genreName");
        String genreNameUK = req.getParameter("genreNameUK");

        if (ObjectUtils.allNotNull(genreName, genreNameUK)) {
            genreService.createGenre(
                    new Genre(genreName, genreNameUK));
            resp.sendRedirect("/cinema/allGenres");
            return;
        }

        req.getRequestDispatcher("/createGenre.jsp").forward(req, resp);
    }

}
