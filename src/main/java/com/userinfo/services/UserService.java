package com.userinfo.services;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.exceptions.InvalidPasswordException;
import com.userinfo.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user) throws DuplicateUsernameException;
    User getUser(UUID id);
    List<User> getUsers();
    User getByUsername(String username) throws DBNotFoundException;
    User getByUsernameAndPassword(String username, String password) throws DBNotFoundException;
    String login(String username, String password) throws DBNotFoundException, InvalidPasswordException;
}
