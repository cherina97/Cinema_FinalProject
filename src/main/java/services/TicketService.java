package services;

import daos.SessionDao;
import daos.TicketDao;
import dto.TicketDto;
import entities.Ticket;
import entities.User;

import java.util.List;

/**
 * The type Ticket service.
 */
public class TicketService {
    private static TicketService ticketService;
    private final TicketDao ticketDao;
    private final SessionDao sessionDao;

    /**
     * Instantiates a new Ticket service.
     */
    public TicketService() {
        this.ticketDao = new TicketDao();
        this.sessionDao = new SessionDao();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static TicketService getInstance() {
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    /**
     * Create ticket ticket.
     *
     * @param ticket the ticket
     * @return the ticket
     */
    public Ticket createTicket(Ticket ticket) {
        return ticketDao.create(ticket);
    }

    /**
     * Read all tickets list.
     *
     * @return the list
     */
    public List<Ticket> readAllTickets(){
        return ticketDao.readAll();
    }

    /**
     * Read all tickets by session id list.
     *
     * @param sessionId the session id
     * @return the list
     */
    public List<Ticket> readAllTicketsBySessionId(int sessionId) {
        return ticketDao.readAllTicketsBySessionId(sessionId);
    }

    /**
     * Gets tickets by seats.
     *
     * @param checked   the checked
     * @param sessionId the session id
     * @return the tickets by seats
     */
    public List<Ticket> getTicketsBySeats(List<Integer> checked, int sessionId) {
        return ticketDao.getTicketsBySeats(checked, sessionId);
    }

    /**
     * Sets user for tickets.
     *
     * @param ticketsBySeats the tickets by seats
     * @param user           the user
     */
    public void setUserForTickets(List<Ticket> ticketsBySeats, User user) {
        ticketDao.setUserForTickets(ticketsBySeats, user);
    }

    /**
     * Remove ticket.
     *
     * @param id the id
     */
    public void removeTicket(int id){
        ticketDao.remove(id);
    }

    /**
     * Gets tickets by user.
     *
     * @param user the user
     * @return the tickets by user
     */
    public List<Ticket> getTicketsByUser(User user) {
        return ticketDao.getTicketsByUser(user);
    }

    /**
     * Gets ticket with session.
     *
     * @param ticketId the ticket id
     * @return the ticket with session
     */
    public TicketDto getTicketWithSession(int ticketId) {
        Ticket ticket = ticketDao.getById(ticketId);
        return new TicketDto(ticket.getId(),
                ticket.getSeatNumber(),
                ticket.getPrice(),
                ticket.getUserId(),
                sessionDao.getSessionById(ticket.getSessionId()));
    }

}
