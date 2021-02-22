package servlets.film;

import services.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Poster servlet.
 */
@WebServlet("/posterServlet")
public class PosterServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        filmService.uploadPoster(id, response);

    }
}
