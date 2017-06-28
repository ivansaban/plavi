package tvz.java.plavi.repository;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.dao.*;
import tvz.java.plavi.model.entity.*;

import java.sql.Date;

/**
 * Created by NIS on 2.6.2017..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectStakeholderRepository projectStakeholderRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void userRepositoryTest() {
        User user = userRepository.findByUsername("user");
        assertEquals("user", user.getUsername());
        assertEquals("Pero", user.getFirstname());
        assertEquals("Perić", user.getLastname());
        assertEquals("male", user.getGender());
        assertEquals("USER", user.getRole().getName());
    }

    @Test
    public void roleRepositoryTest() {
        Role role = roleRepository.findByName("USER");
        assertEquals("USER", role.getName());
    }

    @Test
    public void projectRepositoryTest() {
        Project project = projectRepository.findByName("Projekt");
        assertEquals("Projekt", project.getName());
    }

    @Test
    public void taskRepositoryTest() {
        Task task = taskRepository.findByName("Task");
        assertEquals("Task", task.getName());
        assertEquals("In progress", task.getStatus());
        assertEquals(5, task.getEstimated());
        assertEquals("user", task.getUser().getUsername());
        assertEquals("Projekt", task.getProject().getName());
    }

    @Test
    public void projectStakeholderRepositoryTest() {
        ProjectStakeholder projectStakeholder = projectStakeholderRepository.findOne(1L);
        assertEquals(1L, projectStakeholder.getId(), 0L);
        assertNotNull(projectStakeholder.getProject());
        assertNotNull(projectStakeholder.getUser());
    }
}
