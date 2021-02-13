package daos;

import entities.Film;
import entities.Genre;
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
            "INSERT INTO films (film_title, film_title_uk, description, description_uk, duration, poster) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_ALL_FILMS = "SELECT * FROM films";
    private static final String GET_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM films WHERE id = ?";
    private static final String UPDATE_FILM =
            "UPDATE films SET film_title = ?, film_title_uk = ?, description = ?, description_uk = ?, duration = ? WHERE id = ?";
    private static final String SELECT_POSTER = "SELECT poster FROM films WHERE id = ?";
    private static final String UPDATE_POSTER = "UPDATE films SET poster = ? WHERE id = ?";
    private static final String INSERT_INTO_GENRE_FILM = "INSERT INTO genre_film (film_id, genre_id) VALUES (?, ?)";
    private final Connection connection;

    public FilmDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }


    public Film create(Film film) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FILM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, film.getFilmTitle());
            preparedStatement.setString(2, film.getFilmTitleUK());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setString(4, film.getDescriptionUK());
            preparedStatement.setTime(5, film.getDuration());
            preparedStatement.setBlob(6, film.getPoster());

            preparedStatement.executeUpdate();
//            setGenresForFilm(film, genre);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            film.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    public void setGenresForFilm(Film film, List <Genre> genres){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GENRE_FILM)) {
            preparedStatement.setInt(1, film.getId());
//            preparedStatement.setInt(2, genre.getId());
//            preparedStatement.execute();
            connection.setAutoCommit(false);

            for (Genre genre : genres) {
                preparedStatement.setInt(2, genre.getId());
                try {
                    preparedStatement.execute();
                } catch (SQLException e) {
                    //rollback if exception
                    connection.rollback();
                    System.err.print("SQLException");
                }
            }
            //commit
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            preparedStatement.setString(2, film.getFilmTitleUK());
            preparedStatement.setString(3, film.getDescription());
            preparedStatement.setString(4, film.getDescriptionUK());
            preparedStatement.setTime(5, film.getDuration());
            preparedStatement.setInt(6, film.getId());
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
