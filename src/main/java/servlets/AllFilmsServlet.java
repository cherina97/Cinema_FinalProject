package servlets;

import entities.Film;
import services.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allFilms")
public class AllFilmsServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> filmList = filmService.readAllFilms();
        req.setAttribute("filmList", filmList);

        req.getRequestDispatcher("allFilms.jsp").forward(req, resp);
    }
}
