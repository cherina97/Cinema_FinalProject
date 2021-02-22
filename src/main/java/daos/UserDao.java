package daos;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;
import utils.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 */
public class UserDao implements CRUD<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

    private static final String READ_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
    private final Connection connection;
    private static final String INSERT_USER =
            "INSERT INTO users (first_name, last_name, email, password, role_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    /**
     * Instantiates a new User dao.
     */
    public UserDao() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public User create(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());

            String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            preparedStatement.setString(4, hashPassword);

            preparedStatement.setInt(5, user.getRoleId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            LOG.error("SQLException in create method of UserDao class", e);
        }
        return user;
    }


    @Override
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in readAll method of UserDao class", e);
        }
        return users;
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQLException in remove method of UserDao class", e);
        }
    }

    @Override
    public User update(User user) {
        return null;
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     */
    public Optional<User> getByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(User.of(resultSet));
            }
        } catch (SQLException e) {
            LOG.error("SQLException in update method of UserDao class", e);
        }
        return Optional.empty();
    }

    /**
     * Check email availability boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkEmailAvailability(String email) {
        LOG.trace("Checking availability of email");

        if(email == null) {
            return false;
        }

        Optional<User> user = getByEmail(email);
        return user.get() == null;
    }

}
