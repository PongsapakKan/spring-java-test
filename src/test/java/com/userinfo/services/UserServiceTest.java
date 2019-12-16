package com.userinfo.services;

import com.userinfo.exceptions.DBNotFoundException;
import com.userinfo.exceptions.DuplicateUsernameException;
import com.userinfo.exceptions.InvalidTokenException;
import com.userinfo.exceptions.WrongPasswordException;
import com.userinfo.models.entities.User;
import com.userinfo.repositories.UserRepository;
import com.userinfo.security.jwt.JwtTokenProvider;
import com.userinfo.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserServiceImpl();
    }

    @Test
    public void registerUserSuccessfully() {
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

    @Test
    public void loginWithUsernamePasswordSuccess() {
        String username = "testUsername";
        String password = "password";
        User user = mock(User.class);
        when(userRepository.findOneByUsername(username)).thenReturn(Optional.of(user));
        when(jwtTokenProvider.createToken(user)).thenReturn("Bearer testtoken");
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        String token = userService.login(username, password);
        assertThat(token).isEqualTo("Bearer testtoken");
        verify(userRepository).findOneByUsername(username);
        verify(jwtTokenProvider).createToken(user);
        verify(passwordEncoder).matches(any(), any());
    }

    @Test(expected = DBNotFoundException.class)
    public void loginWithUsernamePasswordFailureWithNotFound() {
        String username = "testUsername";
        String password = "password";
        User user = mock(User.class);
        when(userRepository.findOneByUsername(username)).thenReturn(Optional.empty());
        userService.login(username, password);
        verify(userRepository).findOneByUsername(username);
    }

    @Test(expected = WrongPasswordException.class)
    public void loginWithUsernamePasswordFailureWithWrongPassword() {
        String username = "testUsername";
        String password = "password";
        User user = mock(User.class);
        when(userRepository.findOneByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);
        String token = userService.login(username, password);
        assertThat(token).isEqualTo("Bearer testtoken");
        verify(userRepository).findOneByUsername(username);
        verify(passwordEncoder).matches(any(), any());
    }

    @Test
    public void getUserByTokenSuccessfully() {
        String mockToken = "mock";
        HttpServletRequest req = mock(HttpServletRequest.class);
        User u = mock(User.class);
        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.of(u));
        when(jwtTokenProvider.resolveToken(req)).thenReturn(mockToken);
        when(jwtTokenProvider.getUsername(mockToken)).thenReturn("username");
        User userResponse = userService.getUserByToken(req);
        assertThat(userResponse).isEqualTo(u);
        verify(userRepository).findOneByUsername(anyString());
        verify(jwtTokenProvider).resolveToken(req);
        verify(jwtTokenProvider).getUsername(mockToken);
    }

    @Test(expected = InvalidTokenException.class)
    public void getUserByTokenFailureWithInvalidToken() {
        String mockToken = "mock";
        HttpServletRequest req = mock(HttpServletRequest.class);
        User u = mock(User.class);
        when(jwtTokenProvider.resolveToken(req)).thenReturn(null);
        userService.getUserByToken(req);
        verify(jwtTokenProvider).resolveToken(req);
    }

    @Test(expected = DBNotFoundException.class)
    public void getUserByTokenFailureWithNotFoundUser() {
        String mockToken = "mock";
        HttpServletRequest req = mock(HttpServletRequest.class);
        User u = mock(User.class);
        when(userRepository.findOneByUsername(anyString())).thenReturn(Optional.empty());
        when(jwtTokenProvider.resolveToken(req)).thenReturn(mockToken);
        when(jwtTokenProvider.getUsername(mockToken)).thenReturn("username");
        userService.getUserByToken(req);
        verify(userRepository).findOneByUsername(anyString());
        verify(jwtTokenProvider).resolveToken(req);
        verify(jwtTokenProvider).getUsername(mockToken);
    }
}
