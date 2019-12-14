package com.userinfo.security.jwt;

import com.userinfo.models.entities.User;
import com.userinfo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> oUser = userRepository.findOneByUsername(username);

    if (!oUser.isPresent()) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    User user = oUser.get();

    return org.springframework.security.core.userdetails.User//
        .withUsername(username)//
        .authorities(user.getAuthorities())//
        .password(user.getPassword())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
