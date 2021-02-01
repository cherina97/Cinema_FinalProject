package daos;

import entities.User;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements CRUD<User>{
    private final Connection connection;
    private static final String INSERT_USER =
            "INSERT INTO users (first_name, last_name, email, role, password) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public UserDao() {
        this.connection = ConnectionUtil.getConnection();
    }

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //todo
    @Override
    public List<User> readAll() {
        return null;
    }

    public Optional<User> getByEmail(String email){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Optional.of(User.of(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
