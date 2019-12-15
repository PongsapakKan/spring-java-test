package com.userinfo.services;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.WrongPasswordException;
import com.userinfo.models.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User getUser(UUID id);
    User getUserByToken(HttpServletRequest req);
    String login(String username, String password);
}
