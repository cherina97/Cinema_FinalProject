package daos;

import entities.Film;
import org.apache.commons.io.IOUtils;
import utils.ConnectionPool;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao implements CRUD<Film> {
    private static final String CREATE_FILM =
            "INSERT INTO films (film_title, description, duration, poster) VALUES (?, ?, ?, ?)";
    private static final String READ_ALL_FILMS = "SELECT * FROM films";
    private static final String GET_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM films WHERE id = ?";
    private static final String UPDATE_FILM =
            "UPDATE films SET film_title = ?, description = ?, duration = ? WHERE id = ?";
    private static final String SELECT_POSTER = "SELECT poster FROM films WHERE id = ?";
    private static final String UPDATE_POSTER = "UPDATE films SET poster = ? WHERE id = ?";
    private final Connection connection;

    public FilmDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Film create(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FILM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, film.getFilmTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setTime(3, film.getDuration());
            preparedStatement.setBlob(4, film.getPoster());
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

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Film film) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILM);
            preparedStatement.setString(1, film.getFilmTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setTime(3, film.getDuration());
            preparedStatement.setInt(4, film.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoster(Film film) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_POSTER);
            preparedStatement.setBlob(1, film.getPoster());
            preparedStatement.setInt(2, film.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void uploadPoster(int id, HttpServletResponse response){
        try {
            PreparedStatement stmt = connection.prepareStatement(SELECT_POSTER);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                response.getOutputStream().write(rs.getBytes("poster"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public SerialBlob getBlobFromPart(Part filePart){
        SerialBlob serialBlob = null;
        try {
            InputStream inputStream = filePart.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            serialBlob = new SerialBlob(bytes);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return serialBlob;
    }
}
