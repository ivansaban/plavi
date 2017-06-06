package tvz.java.plavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tvz.java.plavi.dao.*;
import tvz.java.plavi.model.dto.UserTasksCounter;
import tvz.java.plavi.model.entity.Project;
import tvz.java.plavi.model.entity.ProjectStakeholder;
import tvz.java.plavi.model.entity.Task;
import tvz.java.plavi.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NIS on 27.5.2017..
 */
@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectStakeholderRepository projectStakeholderRepository;

    @GetMapping("/getProjects/{userId}")
    public List<Project> getProjects(@PathVariable Long userId) {
        User user  = userRepository.findById(userId);
        List<ProjectStakeholder> projectStakeholders = projectStakeholderRepository.findByUser(user);
        List<Project> projects = new ArrayList<>();
        for (ProjectStakeholder projectStakeholder : projectStakeholders) {
            projects.add(projectStakeholder.getProject());
        }
        return projects;
    }

    @GetMapping("/getTasks/{userId}/{projectId}")
    public List<Task> getTasks(@PathVariable Long userId, @PathVariable Long projectId) {
        User user  = userRepository.findById(userId);
        Project project = projectRepository.findById(projectId);
        return taskRepository.findByUserAndProject(user, project);
    }

    @GetMapping("/getProjectTasks/{projectId}/{status}")
    public List<Task> getProjectTasks(@PathVariable Long projectId, @PathVariable String status) {
        Project project = projectRepository.findById(projectId);
        return taskRepository.findByProjectAndStatus(project, status);
    }

    @GetMapping("/getProjectStakeholders/{projectId}")
    public List<UserTasksCounter> getProjectStakeholders(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId);
        List<ProjectStakeholder> projectStakeholders = projectStakeholderRepository.findByProject(project);
        List<User> users = new ArrayList<>();
        for (ProjectStakeholder projectStakeholder : projectStakeholders) {
            users.add(projectStakeholder.getUser());
        }
        List<UserTasksCounter> userTasksCounters = new ArrayList<>();
        for (User user : users) {
            userTasksCounters.add(new UserTasksCounter(user.getUsername(), user.getTasks().size()));
        }
        return userTasksCounters;
    }
}