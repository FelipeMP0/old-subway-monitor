package com.subwaymonitor.controllers;

import com.subwaymonitor.models.User;
import com.subwaymonitor.presenters.JwtResponse;
import com.subwaymonitor.presenters.UserPresenter;
import com.subwaymonitor.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("account")
public class AccountController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AccountController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User createdUser = this.userService.save(user);

        return new ResponseEntity<>(new UserPresenter(createdUser), HttpStatus.CREATED);
    }

    @GetMapping(value = "authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@Valid @RequestBody User user) throws Exception {
        this.authenticate(user.getUsername(), user.getPassword());
        String token = this.userService.generateToken(user.getUsername());

        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
