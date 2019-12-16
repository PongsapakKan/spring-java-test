package com.userinfo.services;

import com.userinfo.models.entities.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User createUser(User user);
    User getUserByToken(HttpServletRequest req);
    String login(String username, String password);
}
