package servlets.tickets;

import entities.Ticket;
import entities.User;
import services.SessionService;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/allSession/tickets")
public class TicketsBySessionServlet extends HttpServlet {
    private final SessionService sessionService = SessionService.getInstance();
    private final TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sessionId = Integer.parseInt(req.getParameter("id"));
        req.getSession().setAttribute("sessionId", sessionId);

        List<Ticket> ticketList = ticketService.readAllTicketsBySessionId(sessionId);
        List<Ticket> range1 = ticketList.stream().limit(12).collect(Collectors.toList());
        req.setAttribute("range1", range1);

        List<Ticket> range2 = ticketList.stream().skip(12).limit(12).collect(Collectors.toList());
        req.setAttribute("range2", range2);

        String path = "/chooseTicket.jsp?id=" + sessionId;
        getServletContext().getRequestDispatcher(path).include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] checked = req.getParameterValues("checkedSeats");
        List<Integer> checkedList = Arrays.stream(checked)
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        int sessionId = (int) req.getSession().getAttribute("sessionId");

        List<Ticket> ticketsBySeats = ticketService.getTicketsBySeats(checkedList, sessionId);
        User user = (User) req.getSession().getAttribute("user");

        ticketService.setUserForTickets(ticketsBySeats, user);
        resp.sendRedirect("/cinema/tickets");
    }
}
