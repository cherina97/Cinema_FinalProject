import daos.UserDao;
import entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.UserService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserDaoTest {

    @Mock
    private Connection connection;
    @Mock
    private User user;
    @Mock
    private UserDao userDao;
    @Mock
    private UserService userService;
    @Mock
    private List<User> users;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void createUser() {
        UserDao userDao = mock(UserDao.class);
        String email = "email";
        when(userDao.getByEmail(email)).thenReturn(Optional.of(user));
        Assert.assertNotNull(user);
    }

    @Test
    public void getAllUsers() {
        UserDao userDao = mock(UserDao.class);
        when(userDao.readAll()).thenReturn(users);
        Assert.assertNotNull(user);
    }

    @Test
    public void emailNull() {
        boolean test = userService.checkEmailAvailability(null);
        verify(userDao, never()).getByEmail(anyString());
        Assert.assertFalse(test);
    }

    @Test
    public void createdUser() {
        User user = mock(User.class);
        User registered = mock(User.class);
        registered.setId(1);
        when(userDao.create(user)).thenReturn(registered);
        User user1 = userDao.create(user);
        verify(userDao, only()).create(user);
        Assert.assertNotNull(user1);
    }

    @Test
    public void getByEmailAndPsw() {
        User user = mock(User.class);
        when(userService.getUserByEmailAndPassword("email", "password")).thenReturn(Optional.ofNullable(user));
        Optional<User> userByEmailAndPassword = userService.getUserByEmailAndPassword("email", "password");
        verify(userService, only()).getUserByEmailAndPassword(anyString(), anyString());
        Assert.assertNotNull(userByEmailAndPassword);
    }

    @Test
    public void getByEmailAndPswReturnNull() {
        Optional<User> test = userService.getUserByEmailAndPassword("email", null);
        verify(userService, only()).getUserByEmailAndPassword(anyString(), anyString());
        Assert.assertNull(test);
    }

}
