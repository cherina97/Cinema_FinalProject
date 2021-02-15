package servlets.film;

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
        int page = 1;
        int recordsPerPage = 3;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Film> filmList = filmService.readAll((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = filmService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("filmListPagination", filmList);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("allFilms.jsp").forward(req, resp);
    }
}
