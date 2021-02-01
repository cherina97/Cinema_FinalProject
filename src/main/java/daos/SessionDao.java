package daos;

import entities.Session;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDao implements CRUD<Session> {
    private final Connection connection;
    private static final String INSERT_SESSION =
            "INSERT INTO sessions (film_title, description, start_at, duration) VALUES (?, ?, ?, ?)";
    private static final String READ_ALL_SESSIONS = "SELECT * FROM sessions";
    private static final String GET_BY_ID = "SELECT * FROM sessions WHERE id = ?";

    public SessionDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public Session create(Session session) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, session.getFilmTitle());
            preparedStatement.setString(2, session.getDescription());
            preparedStatement.setTime(3, session.getStartAt());
            preparedStatement.setTime(4, session.getDuration());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            session.setId(generatedKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public List<Session> readAll() {
        List<Session> sessionList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_SESSIONS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                sessionList.add(Session.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionList;
    }

    public Session getSessionById(int sessionId){
        Session session = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){
            preparedStatement.setInt(1, sessionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            session = Session.of(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }
}
