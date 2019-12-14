package com.userinfo.services;

import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.models.entities.User;
import com.userinfo.repositories.UserRepository;
import com.userinfo.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserServiceImpl();
    }

    @Test
    public void registerUserSuccessfully() throws Exception {
        User u = mock(User.class);
        doReturn(u).when(userRepository).save(u);
        when(userRepository.findOneByUsername(u.getUsername())).thenReturn(Optional.empty());
        User userResponse = userService.createUser(u);
        assertThat(userResponse.getUsername()).isEqualTo(u.getUsername());
        verify(userRepository).findOneByUsername(u.getUsername());
        verify(userRepository).save(u);
    }

    @Test(expected = DuplicateUsernameException.class)
    public void registerUserFailureWithDuplicateUsername() throws Exception {
        User u = mock(User.class);
        when(userRepository.findOneByUsername(u.getUsername())).thenReturn(Optional.of(u));
        userService.createUser(u);
        verify(userRepository).findOneByUsername(anyString());
    }
}
