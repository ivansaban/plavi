package tvz.java.plavi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.model.dto.LoggedUser;
import tvz.java.plavi.model.entity.Role;
import tvz.java.plavi.model.entity.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by NIS on 3.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Test
    public void UserMapperTest() {
        Role role = new Role();
        role.setName("TEST");

        User user = new User();
        user.setFirstname("Ante");
        user.setLastname("Antić");
        user.setUsername("test");
        user.setPassword("pass");
        user.setRole(role);

        LoggedUser loggedUser = UserMapper.mapUser(user);

        assertEquals("Ante", loggedUser.getFirstname());
        assertEquals("Antić", loggedUser.getLastname());
        assertEquals("test", loggedUser.getUsername());
        assertEquals("TEST", loggedUser.getRole());
    }
}
