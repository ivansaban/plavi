package tvz.java.plavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;
import tvz.java.plavi.mapper.UserMapper;
import tvz.java.plavi.model.dto.LoggedUser;
import tvz.java.plavi.model.dto.UserLoginRequest;
import tvz.java.plavi.model.entity.User;
import tvz.java.plavi.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by NIS on 27.5.2017..
 */
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final LogoutHandler logoutHandler;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthenticationController(LogoutHandler logoutHandler, AuthenticationManager authenticationManager, UserService userService) {
        this.logoutHandler = logoutHandler;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoggedUser login(@RequestBody UserLoginRequest user) {
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            if (authenticate.isAuthenticated()) {
                return UserMapper.mapUser(userService.getUserByUsername(authenticate.getName()));
            }
            return new LoggedUser();
        } catch(Exception e) {
            return new LoggedUser();
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logoutHandler.logout(request, response, auth);
        }
    }

    @GetMapping("/getLoggedUser")
    public LoggedUser getClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            return UserMapper.mapUser(userService.getUserByUsername(authentication.getName()));
        } else {
            return new LoggedUser();
        }
    }
}