package tvz.java.plavi.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.model.entity.Project;
import tvz.java.plavi.model.entity.Role;
import tvz.java.plavi.model.entity.Task;
import tvz.java.plavi.model.entity.User;

import java.sql.Date;
import java.time.LocalDate;


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
        user.setGender("male");
        assertEquals("Ante", user.getFirstname());
        assertEquals("Antić", user.getLastname());
        assertEquals("test", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertEquals("male", user.getGender());
    }

    @Test
    public void RoleTest() {
        Role role = new Role();
        role.setName("test");
        assertEquals("test", role.getName());
    }

    @Test
    public void ProjectTest() {
        Project project = new Project();
        project.setName("test");
        assertEquals("test", project.getName());
    }

    @Test
    public void TaskTest() {
        Task task = new Task();
        task.setName("test");
        task.setStatus("finished");
        task.setEstimated(1);
        task.setCreated(Date.valueOf(LocalDate.now()));
        assertEquals("test", task.getName());
        assertEquals("finished", task.getStatus());
        assertEquals(1, task.getEstimated());
        assertEquals(Date.valueOf(LocalDate.now()), task.getCreated());
    }
}
