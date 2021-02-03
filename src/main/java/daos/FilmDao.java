package daos;

import entities.Film;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao implements CRUD<Film> {
    private static final String CREATE_FILM = "INSERT INTO films (film_title, description, duration) VALUES (?, ?, ?)";
    private static final String READ_ALL_FILMS = "SELECT * FROM films";
    private static final String GET_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    private final Connection connection;

    public FilmDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public Film create(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FILM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, film.getFilmTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setTime(3, film.getDuration());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            film.setId(generatedKeys.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public List<Film> readAll() {
        List<Film> filmList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_FILMS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                filmList.add(Film.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmList;
    }

    public Film getById(int filmId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FILM_BY_ID)){
            preparedStatement.setInt(1, filmId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Film.of(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
