package services;

import daos.UserDao;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
public class UserService {
    private static UserService userService;
    private final UserDao userDao;

    /**
     * Instantiates a new User service.
     */
    public UserService() {
        this.userDao = new UserDao();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    /**
     * Create user.
     *
     * @param user the user
     */
    public void createUser(User user) {
        userDao.create(user);
    }

    /**
     * Get by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> getByEmail(String email){
        return userDao.getByEmail(email);
    }

    /**
     * Gets user by email and password.
     *
     * @param email    the email
     * @param password the password
     * @return the user by email and password
     */
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userDao.getByEmail(email)
                .filter(user -> BCrypt.checkpw(password, user.getPassword()));
    }

    /**
     * Read all users list.
     *
     * @return the list
     */
    public List<User> readAllUsers(){
        return  userDao.readAll();
    }

    /**
     * Remove user.
     *
     * @param id the id
     */
    public void removeUser(int id){
        userDao.remove(id);
    }

    /**
     * Check email availability boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean checkEmailAvailability(String email){
        return userDao.checkEmailAvailability(email);
    }
}
