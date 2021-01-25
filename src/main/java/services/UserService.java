package services;

import daos.UserDao;
import entities.User;

public class UserService {
    private static UserService userService;
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User createUser(User user) {
        return userDao.create(user);
    }
}
