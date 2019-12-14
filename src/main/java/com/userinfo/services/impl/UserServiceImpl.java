package com.userinfo.services.impl;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.exceptions.InvalidPasswordException;
import com.userinfo.models.entities.User;
import com.userinfo.repositories.UserRepository;
import com.userinfo.services.UserService;
import com.userinfo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
   private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User createUser(User user) throws DuplicateUsernameException {
        Optional<User> ou = userRepository.findOneByUsername(user.getUsername());
        if (ou.isPresent())
            throw new DuplicateUsernameException();
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getByUsername(String username) throws DBNotFoundException {
        Optional<User> ou = userRepository.findOneByUsername(username);
        return ou.orElseThrow(() -> new DBNotFoundException(""));
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) throws DBNotFoundException {
        Optional<User> ou = userRepository.findOneByUsernameAndPassword(username, password);
        return ou.orElseThrow(() -> new DBNotFoundException(""));
    }

    @Override
    public String login(String username, String password) throws DBNotFoundException, InvalidPasswordException {
        Optional<User> ou = userRepository.findOneByUsername(username);
        if (!ou.isPresent()) {
            throw new DBNotFoundException("");
        }
        User user = ou.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new InvalidPasswordException("Wrong password.");

        return jwtTokenUtil.generateToken(username);
    }
}
