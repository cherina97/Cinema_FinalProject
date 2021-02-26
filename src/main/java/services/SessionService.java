package services;

import daos.SessionDao;
import daos.TicketDao;
import entities.Session;

import java.util.List;

/**
 * The type Session service.
 */
public class SessionService {

    private final SessionDao sessionDao;
    private final TicketDao ticketDao;
    private static SessionService sessionService;

    /**
     * Instantiates a new Session service.
     */
    public SessionService(){
        this.sessionDao = new SessionDao();
        this.ticketDao = new TicketDao();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SessionService getInstance() {
        if (sessionService == null) {
            sessionService = new SessionService();
        }
        return sessionService;
    }

    /**
     * Create session session.
     *
     * @param session the session
     * @return the session
     */
    public Session createSession(Session session){
        return sessionDao.create(session);
    }

    /**
     * Create tickets for session.
     *
     * @param createdSession the created session
     */
    public void createTicketsForSession(Session createdSession) {
        int sessionId = createdSession.getId();
        ticketDao.createTicketsForSession(sessionId);
    }

    /**
     * Read all sessions list.
     *
     * @return the list
     */
    public List<Session> readAllSessions(){
        return sessionDao.readAll();
    }

    /**
     * Read all from now list.
     *
     * @return the list
     */
    public List<Session> readAllFromNow(){
        return sessionDao.readAllFromNow();
    }

    /**
     * Read all order by film list.
     *
     * @return the list
     */
    public List<Session> readAllOrderByFilm(){
        return sessionDao.readAllOrderByFilm();
    }

    /**
     * Read all order by free seats list.
     *
     * @return the list
     */
    public List<Session> readAllOrderByFreeSeats(){
        return sessionDao.readAllOrderByFreeSeats();
    }

    /**
     * Get session by id session.
     *
     * @param sessionId the session id
     * @return the session
     */
    public Session getSessionById(int sessionId){
        return sessionDao.getSessionById(sessionId);
    }

    /**
     * Remove session.
     *
     * @param id the id
     */
    public void removeSession(int id){
        sessionDao.remove(id);
    }

    /**
     * Update session.
     *
     * @param session the session
     */
    public void updateSession(Session session){
        sessionDao.update(session);
    }
}
