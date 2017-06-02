package tvz.java.plavi.repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.dao.RoleRepository;
import tvz.java.plavi.dao.UserRepository;
import tvz.java.plavi.model.entity.Role;
import tvz.java.plavi.model.entity.User;

/**
 * Created by NIS on 2.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void UserRepositoryTest() {
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
        User user = new User();
        user.setUsername("test");
        user.setPassword("pass");
        entityManager.persist(user);
        assertEquals(1, userRepository.count());
        assertNotNull(userRepository.findByUsername("test"));
    }

    @Test
    public void RoleRepositoryTest() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        assertEquals(0, roleRepository.count());
        Role role = new Role();
        role.setName("test");
        entityManager.persist(role);
        assertEquals(1, roleRepository.count());
        assertNotNull(roleRepository.findByName("test"));
    }
}
