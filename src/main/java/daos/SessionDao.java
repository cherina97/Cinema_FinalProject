package daos;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.Session;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDao implements CRUD<Session> {
    private static final Logger LOG = LoggerFactory.getLogger(SessionDao.class);

    private static final String DELETE_BY_ID = "DELETE FROM sessions WHERE id = ?";
    private static final String UPDATE_SESSION =
            "UPDATE sessions SET film_id = ?, start_at = ?, date = ? WHERE id = ?";

    private final Connection connection;
    private static final String INSERT_SESSION =
            "INSERT INTO sessions (film_id, start_at, date) VALUES (?, ?, ?)";
    private static final String READ_ALL_SESSIONS =
            "select sessions.id, sessions.film_id, sessions.start_at, sessions.date, count(*) as user_id from tickets \n" +
                    "inner join sessions \n" +
                    "on tickets.session_id = sessions.id \n" +
                    "where user_id is null group by session_id order by date,start_at";
    private static final String READ_ALL_ORDER_BY_DATE =
            "select sessions.id, sessions.film_id, sessions.start_at, sessions.date, count(*) as user_id from tickets\n" +
                    "inner join sessions \n" +
                    "on tickets.session_id = sessions.id \n" +
                    "where user_id is null and date >= CURDATE() group by session_id order by date,start_at";
    private static final String READ_ALL_ORDER_BY_FILM =
            "select sessions.id, sessions.film_id, sessions.start_at, sessions.date, count(*) as user_id from tickets \n" +
                    "inner join sessions on tickets.session_id = sessions.id \n" +
                    "inner join films on sessions.film_id = films.id \n" +
                    "where user_id is null and date >= CURDATE() group by session_id order by film_title";
    private static final String READ_ALL_ORDER_BY_FREE_SEATS =
            "select sessions.id, sessions.film_id, sessions.start_at, sessions.date, count(*) as user_id from tickets \n" +
                    "inner join sessions \n" +
                    "on tickets.session_id = sessions.id \n" +
                    "where user_id is null and date >= CURDATE() group by session_id order by user_id desc";
    private static final String GET_BY_ID = "SELECT * FROM sessions WHERE id = ?";
    private int noOfRecords;

    public SessionDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Session create(Session session) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, session.getFilm().getId());
            preparedStatement.setTime(2, session.getStartAt());
            preparedStatement.setObject(3, session.getDate());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            session.setId(generatedKeys.getInt(1));

        } catch (SQLException e) {
            LOG.error("SQLException in create method of SessionDao class", e);
        }
        return session;
    }


    @Override
    public List<Session> readAll() {
        return getSessionList(READ_ALL_SESSIONS);
    }

    public List<Session> readAllFromNow() {
        return getSessionList(READ_ALL_ORDER_BY_DATE);
    }

    public List<Session> readAllOrderByFilm() {
        return getSessionList(READ_ALL_ORDER_BY_FILM);
    }

    public List<Session> readAllOrderByFreeSeats() {
        return getSessionList(READ_ALL_ORDER_BY_FREE_SEATS);
    }

    //pagination
    public List<Session> readAll(int offset, int noOfRecords) {
        String query = "select SQL_CALC_FOUND_ROWS * from sessions limit "
                + offset + ", " + noOfRecords;

        List<Session> sessionList = new ArrayList<>();
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                sessionList.add(of(rs));
            }
            rs.close();

            rs = stmt.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            LOG.error("SQLException in readAll method of SessionDao class", e);
        }
        return sessionList;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    private List<Session> getSessionList(String readAllSessionsFromNowOrderBy) {
        List<Session> sessionList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(readAllSessionsFromNowOrderBy)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sessionList.add(ofWithUserId(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in getSessionList method of SessionDao class", e);
        }
        return sessionList;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException in remove method of SessionDao class", e);
        }
    }

    @Override
    public Session update(Session session) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SESSION);
            preparedStatement.setInt(1, session.getFilm().getId());
            preparedStatement.setTime(2, session.getStartAt());
            preparedStatement.setDate(3, session.getDate());
            preparedStatement.setInt(4, session.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("SQLException in update method of SessionDao class", e);
        }
        //todo
        return null;
    }

    public static Session ofWithUserId(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int filmId = resultSet.getInt("film_id");
            Time startAt = resultSet.getTime("start_at");
            Date date = Date.valueOf(resultSet.getString("date"));
            int freeSeats = Integer.parseInt(resultSet.getString("user_id"));

            return new Session.Builder()
                    .withId(id)
                    .withFilm(new FilmDao().getById(filmId))
                    .withTimeStartAt(startAt)
                    .withDate(date)
                    .withFreeSeats(freeSeats)
                    .build();

        } catch (SQLException e) {
            LOG.error("SQLException in ofWithUserId method of SessionDao class", e);
        }
        //todo
        return null;
    }

    public static Session of(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int filmId = resultSet.getInt("film_id");
            Time startAt = resultSet.getTime("start_at");
            Date date = Date.valueOf(resultSet.getString("date"));

            return new Session.Builder()
                    .withId(id)
                    .withFilm(new FilmDao().getById(filmId))
                    .withTimeStartAt(startAt)
                    .withDate(date)
                    .build();

        } catch (SQLException e) {
            LOG.error("SQLException in of method of SessionDao class", e);
        }
        /// TODO: 19.02.2021
        return null;
    }

    public Session getSessionById(int sessionId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, sessionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return of(resultSet);
            }
//            resultSet.next();
        } catch (SQLException e) {
            LOG.error("SQLException in getSessionById method of SessionDao class", e);
        }
        return null;
    }
}
