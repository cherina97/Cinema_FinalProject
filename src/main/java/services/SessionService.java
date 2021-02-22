package services;

import daos.SessionDao;
import daos.TicketDao;
import entities.Session;

import java.util.List;

public class SessionService {

    private final SessionDao sessionDao;
    private final TicketDao ticketDao;
    private static SessionService sessionService;

    public SessionService(){
        this.sessionDao = new SessionDao();
        this.ticketDao = new TicketDao();
    }

    public static SessionService getInstance() {
        if (sessionService == null) {
            sessionService = new SessionService();
        }
        return sessionService;
    }

    public Session createSession(Session session){
        return sessionDao.create(session);
    }

    public void createTicketsForSession(Session createdSession) {
        int sessionId = createdSession.getId();
        ticketDao.createTicketsForSession(sessionId);
    }

    public List<Session> readAllSessions(){
        return sessionDao.readAll();
    }

    public List<Session> readAllFromNow(){
        return sessionDao.readAllFromNow();
    }

    public List<Session> readAllOrderByFilm(){
        return sessionDao.readAllOrderByFilm();
    }

    public List<Session> readAllOrderByFreeSeats(){
        return sessionDao.readAllOrderByFreeSeats();
    }

    public Session getSessionById(int sessionId){
        return sessionDao.getSessionById(sessionId);
    }

    public void removeSession(int id){
        sessionDao.remove(id);
    }

    public void updateSession(Session session){
        sessionDao.update(session);
    }
}
