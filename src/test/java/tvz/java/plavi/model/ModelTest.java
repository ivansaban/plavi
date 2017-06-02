package tvz.java.plavi.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.model.entity.Role;
import tvz.java.plavi.model.entity.User;

/**
 * Created by NIS on 2.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelTest {

    @Test
    public void UserTest() {
        User user = new User();
        user.setFirstname("Ante");
        user.setLastname("Antić");
        user.setUsername("test");
        user.setPassword("pass");
        assertEquals("Ante", user.getFirstname());
        assertEquals("Antić", user.getLastname());
        assertEquals("test", user.getUsername());
        assertEquals("pass", user.getPassword());
    }

    @Test
    public void RoleTest() {
        Role role = new Role();
        role.setName("test");
        assertEquals("test", role.getName());
    }
}
