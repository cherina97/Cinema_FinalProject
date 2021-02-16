package services;

import daos.UserDao;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
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
                .filter(user -> BCrypt.checkpw(password, user.getPassword()));
    }

    public List<User> readAllUsers(){
        return  userDao.readAll();
    }

    public void removeUser(int id){
        userDao.remove(id);
    }
}
