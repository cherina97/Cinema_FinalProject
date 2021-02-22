package filters;

import entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Admin filter.
 */
@WebFilter({"/allFilms/admin/*", "/allGenres/admin/*", "/allSession/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        if(user == null || user.getRoleId() == 1){
            request.getRequestDispatcher("/accessDenied.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
