package com.userinfo.controllers;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.exceptions.InvalidPasswordException;
import com.userinfo.models.api.requests.UserCredentials;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.ApiError;
import com.userinfo.models.api.response.LoginResponse;
import com.userinfo.models.api.response.UserResponse;
import com.userinfo.models.entities.MemberType;
import com.userinfo.models.entities.User;
import com.userinfo.services.UserService;
import com.userinfo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/users")
    public UserResponse register(@RequestBody @Valid UserRegistration registration, Errors errors) {
        User u = convertToEntity(registration);
        try {
            return convertToResponse(userService.createUser(u));
        } catch (DuplicateUsernameException ex) {
            throw new IllegalArgumentException("");
        }
    }

    @GetMapping("/users/{id}")
    public UserResponse getUser(@PathVariable("id") String  id) throws IllegalArgumentException {
        return convertToResponse(userService.getUser(UUID.fromString(id)));
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() throws IllegalArgumentException {
        List<UserResponse> list = userService.getUsers().stream().map(this::convertToResponse).collect(Collectors.toList());
        return list;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody UserCredentials UserCredentials) {
        try {
            String token = userService.login(UserCredentials.getUsername(), UserCredentials.getPassword());
            LoginResponse lr = new LoginResponse();
            lr.setToken(token);
            return lr;
        } catch (DBNotFoundException | InvalidPasswordException ex) {
            throw new IllegalArgumentException("TESTETST");
        }
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
        user.setMemberType(MemberType.applyMemberType(registration.getSalary()));
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMDD");
        Date now = new Date();
        user.setRegisterDate(now);
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
        ur.setUserType(user.getMemberType());
        return ur;
    }
}
