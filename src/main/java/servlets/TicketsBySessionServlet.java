package servlets;

import entities.Ticket;
import services.SessionService;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allSession/tickets")
public class TicketsBySessionServlet extends HttpServlet {
    private SessionService sessionService = SessionService.getInstance();
    private TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sessionId = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("sessionId", sessionId);

        List<Ticket> ticketList = ticketService.readAllTicketsBySessionId(sessionId);
        req.setAttribute("ticketList", ticketList);

        String path = "/allTickets.jsp?id=" + sessionId;
        getServletContext().getRequestDispatcher(path).include(req, resp);

    }


}
