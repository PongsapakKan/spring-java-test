package com.userinfo.controllers;

import com.userinfo.converters.UserConverter;
import com.userinfo.exceptions.FieldValidationException;
import com.userinfo.models.api.requests.UserCredentials;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.LoginResponse;
import com.userinfo.models.entities.User;
import com.userinfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @PostMapping("/api/users")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistration registration, Errors errors) {
        if (errors.hasErrors()) {
            throw new FieldValidationException(errors.getFieldErrors());
        }
        User u = userConverter.convertRegistrationToEntity(registration);
        return new ResponseEntity<>(userConverter.convertUserToUserResponse(userService.createUser(u)), HttpStatus.CREATED);
    }

    @GetMapping("/api/users/me")
    public ResponseEntity<?> whoami(HttpServletRequest req) {
        return new ResponseEntity<>(userConverter.convertUserToUserResponse(userService.getUserByToken(req)), HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials UserCredentials) {
        String token = userService.login(UserCredentials.getUsername(), UserCredentials.getPassword());
        LoginResponse lr = new LoginResponse();
        lr.setToken(token);
        return new ResponseEntity<>(lr, HttpStatus.OK);
    }
}
