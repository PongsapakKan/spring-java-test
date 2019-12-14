package com.userinfo.controllers;

import com.userinfo.exceptions.FieldValidationException;
import com.userinfo.models.api.requests.UserCredentials;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.LoginResponse;
import com.userinfo.models.api.response.UserResponse;
import com.userinfo.models.entities.MemberClassification;
import com.userinfo.models.entities.Role;
import com.userinfo.models.entities.User;
import com.userinfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistration registration, Errors errors) {
        if (errors.hasErrors()) {
            throw new FieldValidationException(errors.getFieldErrors());
        }
        User u = convertToEntity(registration);
        return new ResponseEntity<>(convertToResponse(userService.createUser(u)), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(convertToResponse(userService.getUser(UUID.fromString(id))), HttpStatus.OK);
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> whoami(HttpServletRequest req) {
        return new ResponseEntity<>(convertToResponse(userService.getUserByToken(req)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials UserCredentials) {
        String token = userService.login(UserCredentials.getUsername(), UserCredentials.getPassword());
        LoginResponse lr = new LoginResponse();
        lr.setToken(token);
        return new ResponseEntity<>(lr, HttpStatus.OK);
    }

    private User convertToEntity(UserRegistration registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(registration.getPassword());
        user.setPassword(password);
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPhoneNo(registration.getPhoneNo());
        user.setAddress(registration.getAddress());
        user.setSalary(registration.getSalary());
        user.setMemberClassification(MemberClassification.valueOf(registration.getSalary()));
        user.setRole(Role.USER);
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
        Date now = new Date();
        user.setRegisterDate(now);
        System.out.println(format.format(now));
        user.setReferenceCode(format.format(now) + registration.getPhoneNo().substring(registration.getPhoneNo().length() - 4));
        return user;
    }

    private UserResponse convertToResponse(User user) {
        UserResponse ur = new UserResponse();
        ur.setFirstName(user.getFirstName());
        ur.setLastName(user.getLastName());
        ur.setAddress(user.getAddress());
        ur.setId(user.getId().toString());
        ur.setPhoneNo(user.getPhoneNo());
        ur.setReferenceCode(user.getReferenceCode());
        ur.setMemberClassify(user.getMemberClassification().name());
        return ur;
    }
}
