package servlets.genre;

import entities.Genre;
import services.GenreService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The type All genres servlet.
 */
@WebServlet("/allGenres")
public class AllGenresServlet extends HttpServlet {
    private final GenreService genreService = GenreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Genre> genres = genreService.readAllGenres();
        req.setAttribute("genres", genres);

        req.getRequestDispatcher("allGenres.jsp").forward(req, resp);
    }
}
