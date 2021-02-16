package servlets.tickets;

import entities.Ticket;
import entities.User;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tickets")
public class BuyTicketServlet extends HttpServlet {
    private TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Ticket> ticketsByUser = ticketService.getTicketsByUser(user);
        req.setAttribute("ticketsByUser", ticketsByUser);

        req.getRequestDispatcher("boughtTickets.jsp").forward(req, resp);
    }
}
