package daos;

import entities.Genre;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenreDao implements CRUD<Genre>{
    private static final String INSERT_GENRE = "INSERT INTO genres (genre_name, genre_name_uk) VALUES (?, ?);";
    private static final String READ_ALL_GENRES = "SELECT * FROM genres";
    private static final String GET_BY_IDS = "SELECT * FROM genres WHERE id IN (?)";
    private final Connection connection;

    public GenreDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Genre create(Genre genre) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENRE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, genre.getGenreName());
            preparedStatement.setString(2, genre.getGenreNameUK());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            genre.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public List<Genre> readAll() {
        List<Genre> genres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_GENRES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genres.add(Genre.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

//    public Genre getByName(String genre){
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from genres where genre_name = ?");
//            preparedStatement.setString(1, genre);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()){
//                return Genre.of(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return  null;
//    }

    public List<Genre> getGenresByIds(List<Integer> genresIds){
        List <Genre> genres = new ArrayList<>();
        try {
            String sqlIN = genresIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(",", "(", ")"));
            String sql = GET_BY_IDS.replace("(?)", sqlIN);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genres.add(Genre.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Genre genre) {

    }
}
