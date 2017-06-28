package tvz.java.plavi.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tvz.java.plavi.model.dto.TotalUsers;
import tvz.java.plavi.model.dto.UserTasksCounter;
import tvz.java.plavi.model.entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


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
        user.setTasks(new ArrayList<>());
        user.setProjectStakeholders(new ArrayList<>());
        assertEquals("Ante", user.getFirstname());
        assertEquals("Antić", user.getLastname());
        assertEquals("test", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertEquals("male", user.getGender());
        assertNotNull(user.getTasks());
        assertNotNull(user.getProjectStakeholders());

        User user2 = new User();
        assertFalse(user.equals(user2));
        assertFalse(user.toString().equals(user2.toString()));
    }

    @Test
    public void RoleTest() {
        Role role = new Role();
        role.setName("test");
        role.setUsers(new ArrayList<>());
        assertEquals("test", role.getName());
        assertNotNull(role.getUsers());

        Role role2 = new Role();
        assertFalse(role.equals(role2));
        assertFalse(role.toString().equals(role2.toString()));
    }

    @Test
    public void ProjectTest() {
        Project project = new Project();
        project.setName("test");
        project.setTasks(new ArrayList<>());
        project.setProjectStakeholders(new ArrayList<>());
        assertEquals("test", project.getName());
        assertNotNull(project.getTasks());
        assertNotNull(project.getProjectStakeholders());

        Project project2 = new Project();
        assertFalse(project.equals(project2));
        assertFalse(project.toString().equals(project2.toString()));
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

    @Test
    public void ProjectStakeholderTest() {
        ProjectStakeholder projectStakeholder = new ProjectStakeholder();
        projectStakeholder.setUser(new User());
        projectStakeholder.setProject(new Project());
        assertNotNull(projectStakeholder.getUser());
        assertNotNull(projectStakeholder.getProject());
    }

    @Test
    public void TotalUsersTest() {
        TotalUsers totalUsers = new TotalUsers(2L, 1L, 1L);
        totalUsers.setFemaleUsers(2L);
        totalUsers.setAllUsers(3L);
        assertEquals(3L, totalUsers.getAllUsers());
        assertEquals(2L, totalUsers.getFemaleUsers());
        assertEquals(1L, totalUsers.getMaleUsers());
    }

    @Test
    public void UserTasksCounterTest() {
        UserTasksCounter userTasksCounter = new UserTasksCounter("test", 1L, 100.0f);
        userTasksCounter.setTaskCount(3L);
        assertEquals("test", userTasksCounter.getUsername());
        assertEquals(3L, userTasksCounter.getTaskCount());
    }
}
