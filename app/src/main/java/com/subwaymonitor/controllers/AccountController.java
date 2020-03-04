package com.subwaymonitor.controllers;

import com.subwaymonitor.models.UserModel;
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

  @PostMapping(
      value = "register",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@Valid @RequestBody UserModel userModel) {
    UserModel createdUserModel = this.userService.save(userModel);

    return new ResponseEntity<>(new UserPresenter(createdUserModel), HttpStatus.CREATED);
  }

  @GetMapping(
      value = "authenticate",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> authenticate(@Valid @RequestBody UserModel userModel) throws Exception {
    this.authenticate(userModel.getUsername(), userModel.getPassword());
    String token = this.userService.generateToken(userModel.getUsername());

    return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
  }

  private void authenticate(String username, String password) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }
}
