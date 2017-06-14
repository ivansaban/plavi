package tvz.java.plavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tvz.java.plavi.dao.*;
import tvz.java.plavi.model.dto.TotalUsers;
import tvz.java.plavi.model.dto.UserAddRequest;
import tvz.java.plavi.model.dto.UserEditRequest;
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
public class ManagmentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectStakeholderRepository projectStakeholderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userRepository.findByUsername(userAddRequest.getUsername()) != null) {
            return new ResponseEntity<String>("Username is already taken!", HttpStatus.NOT_ACCEPTABLE);
        }
        User user = new User();
        user.setUsername(userAddRequest.getUsername());
        user.setFirstname(userAddRequest.getFirstname());
        user.setLastname(userAddRequest.getLastname());
        user.setGender(userAddRequest.getGender());
        user.setPassword(passwordEncoder.encode(userAddRequest.getPassword()));
        user.setRole(roleRepository.findById(userAddRequest.getRoleId()));
        userRepository.save(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody UserEditRequest userEditRequest) {
        User user = userRepository.findByUsername(userEditRequest.getUsername());
        user.setFirstname(userEditRequest.getFirstname());
        user.setLastname(userEditRequest.getLastname());
        user.setGender(userEditRequest.getGender());
        userRepository.save(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}