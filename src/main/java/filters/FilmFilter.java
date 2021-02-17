package filters;

import entities.Film;
import services.FilmService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter({"/allFilms/admin/update", "/allFilms/admin/delete", "/allFilms/admin/updatePoster"})
public class FilmFilter implements Filter {
    private final FilmService filmService = FilmService.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        int filmId = Integer.parseInt(request.getParameter("id"));

        List<Film> filmList = filmService.readAllFilms();
        List<Integer> presentIds = filmList.stream().map(Film::getId).collect(Collectors.toList());

        if (presentIds.contains(filmId)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("/wrongParam.jsp").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
