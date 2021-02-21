package filters;

import entities.Session;
import services.SessionService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter({"/allSession/tickets", "/allSession/admin/update", "/allSession/admin/delete"})
public class SessionFilter implements Filter {
    private final SessionService sessionService = SessionService.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        int sessionId = Integer.parseInt(request.getParameter("id"));

        List<Session> sessionList = sessionService.readAllSessions();
        List<Integer> presentIds = sessionList
                .stream()
                .map(Session::getId)
                .collect(Collectors.toList());

        if (presentIds.contains(sessionId)) {
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
