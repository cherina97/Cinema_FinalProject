package services;

import daos.UserDao;
import entities.User;

import java.util.Optional;

public class UserService {
    private static UserService userService;
    private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void createUser(User user) {
        userDao.create(user);
    }

    public Optional<User> getByEmail(String email){
        return userDao.getByEmail(email);
    }

    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        return userDao.getByEmail(email)
                .filter(user -> user.getPassword().equals(password));
    }
}
