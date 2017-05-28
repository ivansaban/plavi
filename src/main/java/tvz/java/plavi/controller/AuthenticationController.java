package tvz.java.plavi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;
import tvz.java.plavi.model.dto.UserLoginRequest;

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

    @Autowired
    public AuthenticationController(LogoutHandler logoutHandler, AuthenticationManager authenticationManager) {
        this.logoutHandler = logoutHandler;
        this.authenticationManager = authenticationManager;
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequest user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        if (authenticate.isAuthenticated()) {
            return "Successfull";
        }
        return "Unsuccessfull";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logoutHandler.logout(request, response, auth);
        }
    }
}
