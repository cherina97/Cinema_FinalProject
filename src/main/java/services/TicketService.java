package services;

import daos.SessionDao;
import daos.TicketDao;
import dto.TicketDto;
import entities.Ticket;
import entities.User;

import java.util.List;

public class TicketService {
    private static TicketService ticketService;
    private final TicketDao ticketDao;
    private final SessionDao sessionDao;

    public TicketService() {
        this.ticketDao = new TicketDao();
        this.sessionDao = new SessionDao();
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

    public TicketDto getTicketWithSession(int ticketId) {
        Ticket ticket = ticketDao.getById(ticketId);
        return new TicketDto(ticket.getId(),
                ticket.getSeatNumber(),
                ticket.getPrice(),
                ticket.getUserId(),
                sessionDao.getSessionById(ticket.getSessionId()));
    }

}
