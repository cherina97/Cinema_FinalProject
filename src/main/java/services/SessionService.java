package services;

import daos.SessionDao;
import entities.Session;

import java.util.List;

public class SessionService {

    private final SessionDao sessionDao;
    private static SessionService sessionService;

    public SessionService(){
        this.sessionDao = new SessionDao();
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

    public List<Session> readAllSessions(){
        return sessionDao.readAll();
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
