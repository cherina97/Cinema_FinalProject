package filters;

import entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = {"/*"})
public class NotAuthFilter implements Filter {
    private final List<String> START_PAGES = Arrays.asList("/index.jsp", "/login", "/allSession", "/allSession/tickets");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getServletPath();
        if (uri.matches(".*(css|jpg|png|gif|js)")) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            if (!START_PAGES.contains(uri)) {
                request.getRequestDispatcher("/accessDenied.jsp").forward(request, response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
