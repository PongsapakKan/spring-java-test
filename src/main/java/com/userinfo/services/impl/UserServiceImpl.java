package com.userinfo.services.impl;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.exceptions.InvalidTokenException;
import com.userinfo.exceptions.WrongPasswordException;
import com.userinfo.models.entities.User;
import com.userinfo.repositories.UserRepository;
import com.userinfo.security.jwt.JwtTokenProvider;
import com.userinfo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User createUser(User user) {
        Optional<User> ou = userRepository.findOneByUsername(user.getUsername());
        if (ou.isPresent())
            throw new DuplicateUsernameException();
        return userRepository.save(user);
    }

    @Override
    public User getUser(UUID id) {
        Optional<User> ou = userRepository.findById(id);
        if (!ou.isPresent())
            throw new DBNotFoundException("User not found.");
        return ou.get();
    }

    @Override
    public User getUserByToken(HttpServletRequest req) {
        String token = jwtTokenProvider.resolveToken(req);
        if (token == null)
            throw new InvalidTokenException("Invalid Token.");

        String username = jwtTokenProvider.getUsername(token);

        Optional<User> ou = userRepository.findOneByUsername(username);
        if (!ou.isPresent())
            throw new DBNotFoundException("User not found.");
        return ou.get();
    }

    @Override
    public String login(String username, String password) throws DBNotFoundException, WrongPasswordException {
        Optional<User> ou = userRepository.findOneByUsername(username);
        if (!ou.isPresent()) {
            throw new DBNotFoundException("");
        }
        User user = ou.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new WrongPasswordException("Wrong password.");

        return jwtTokenProvider.createToken(user);
    }
}
