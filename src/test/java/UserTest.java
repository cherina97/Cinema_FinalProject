import entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyInt;

public class UserTest {
    @Mock
    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void setId() {
        user = new User();
        int id = anyInt();
        user.setId(id);
        Assert.assertEquals(user.getId(), id);
    }

    @Test
    public void getId() {
        user = new User();
        int id = user.getId();
        Assert.assertEquals(user.getId(), id);
    }
    @Test
    public void setFirstName() {
        user = new User();
        String test = "test";
        user.setFirstName(test);
        Assert.assertEquals(user.getFirstName(), test);
    }

    @Test
    public void getFirstName() {
        user = new User();
        String firstName = user.getFirstName();
        Assert.assertEquals(user.getFirstName(), firstName);
    }

    @Test
    public void setLastName() {
        user = new User();
        String test = "test";
        user.setLastName(test);
        Assert.assertEquals(user.getLastName(), test);
    }

    @Test
    public void getLastName() {
        user = new User();
        String last = user.getLastName();
        Assert.assertEquals(user.getLastName(), last);
    }

    @Test
    public void setEmail() {
        user = new User();
        String test = "test";
        user.setEmail(test);
        Assert.assertEquals(user.getEmail(), test);
    }

    @Test
    public void getEmail() {
        user = new User();
        String email = user.getEmail();
        Assert.assertEquals(user.getEmail(), email);
    }

    @Test
    public void setPassword() {
        user = new User();
        String test = "test";
        user.setPassword(test);
        Assert.assertEquals(user.getPassword(), test);
    }

    @Test
    public void getPassword() {
        user = new User();
        String psw = user.getPassword();
        Assert.assertEquals(user.getPassword(), psw);
    }

}
