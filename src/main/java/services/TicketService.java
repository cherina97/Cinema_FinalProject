package services;

import daos.TicketDao;
import entities.Ticket;
import entities.User;

import java.util.List;

public class TicketService {
    private static TicketService ticketService;
    private final TicketDao ticketDao;

    public TicketService() {
        this.ticketDao = new TicketDao();
    }

    public static TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketDao.create(ticket);
    }

    public List<Ticket> readAllTickets(){
        return ticketDao.readAll();
    }

    public List<Ticket> readAllTicketsBySessionId(int sessionId) {
        return ticketDao.readAllTicketsBySessionId(sessionId);
    }

    public List<Ticket> getTicketsBySeats(List<Integer> checked, int sessionId) {
        return ticketDao.getTicketsBySeats(checked, sessionId);
    }

    public void setUserForTickets(List<Ticket> ticketsBySeats, User user) {
        ticketDao.setUserForTickets(ticketsBySeats, user);
    }

    public void removeTicket(int id){
        ticketDao.remove(id);
    }

    public List<Ticket> getTicketsByUser(User user) {
        return ticketDao.getTicketsByUser(user);
    }
}
