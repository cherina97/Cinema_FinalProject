package services;

import daos.TicketDao;
import entities.Ticket;

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

//    public void createTicketsForSession(int sessionId) {
//        ticketDao.createTicketsForSession(sessionId);
//    }
}
