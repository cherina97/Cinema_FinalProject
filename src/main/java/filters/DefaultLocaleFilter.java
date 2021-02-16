package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "DefaultLocaleFilter", urlPatterns = {"/*"})
public class DefaultLocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getSession().getAttribute("language") == null) {
            req.getSession().setAttribute("language", "uk_UA");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
