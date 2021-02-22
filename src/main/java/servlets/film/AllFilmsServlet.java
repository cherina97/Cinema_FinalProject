package servlets.film;

import dto.FilmDto;
import entities.Film;
import services.FilmService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type All films servlet.
 */
@WebServlet("/allFilms")
public class AllFilmsServlet extends HttpServlet {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //pagination
        int page = 1;
        int recordsPerPage = 2;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Film> filmList = filmService.readAll((page - 1) * recordsPerPage, recordsPerPage);
        int noOfRecords = filmService.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);


        List<FilmDto> filmDtos = new ArrayList<>();
        for(Film f : filmList){
            int id = f.getId();
            FilmDto filmWithGenres = filmService.getFilmWithGenres(id);
            filmDtos.add(filmWithGenres);
        }

        req.setAttribute("filmListPagination", filmDtos);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("allFilms.jsp").forward(req, resp);
    }

}
