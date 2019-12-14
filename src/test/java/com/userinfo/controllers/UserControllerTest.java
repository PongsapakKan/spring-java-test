package com.userinfo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.entities.MemberClassification;
import com.userinfo.models.entities.Role;
import com.userinfo.models.entities.User;
import com.userinfo.security.jwt.JwtTokenProvider;
import com.userinfo.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUserSuccessfully() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        User user = new User();
        user.setId(id);
        user.setPhoneNo("0000000000");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setAddress("some address");
        user.setReferenceCode("201908080000");
        user.setMemberClassification(MemberClassification.Gold);
        user.setSalary(30000);
        user.setRole(Role.USER);
        user.setRegisterDate(new Date());

        when(userService.createUser(any(User.class))).thenReturn(user);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("some address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNo").value("0000000000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.referenceCode").value("201908080000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memberClassify").value("Gold"))
                .andReturn();
        verify(userService).createUser(any(User.class));
    }

    @Test
    public void testRegisterUserSuccessfullyWithTrimSpace() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername(" username ");
        ur.setPassword(" password ");
        ur.setPhoneNo("0000000000");
        ur.setFirstName(" firstname ");
        ur.setLastName(" lastname ");
        ur.setAddress(" some address ");
        ur.setSalary(30000);

        User user = new User();
        user.setId(id);
        user.setPhoneNo("0000000000");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setAddress("some address");
        user.setReferenceCode("201908080000");
        user.setMemberClassification(MemberClassification.Gold);
        user.setSalary(30000);
        user.setRole(Role.USER);
        user.setRegisterDate(new Date());

        when(userService.createUser(any(User.class))).thenReturn(user);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("some address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNo").value("0000000000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.referenceCode").value("201908080000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.memberClassify").value("Gold"))
                .andReturn();
        verify(userService).createUser(any(User.class));
    }

    @Test
    public void testRegisterUserFailWithInvalidUsername() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("user-/-/name");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidUsernameLength() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("u");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidPassword() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("pass---word");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidPasswordLength() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("p");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidPhoneNo() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000asdb");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidFirstNameLength() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("fi");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidLastNameLength() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("l");
        ur.setAddress("some address");
        ur.setSalary(30000);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailWithInvalidSalary() throws Exception {
        UUID id = UUID.randomUUID();
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(1);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/users")
                .content(objectMapper.writeValueAsBytes(ur))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions result = mockMvc.perform(request);

        result.andExpect(status().isBadRequest());
    }

//    @Test
//    public void testGetUserSuccessfully() throws Exception {
//        UUID id = UUID.randomUUID();
//        User user = new User();
//        user.setId(id);
//        user.setPhoneNo("0000000000");
//        user.setFirstName("firstname");
//        user.setLastName("lastname");
//        user.setAddress("some address");
//        user.setReferenceCode("201908080000");
//        user.setMemberClassification(MemberClassification.Gold);
//        user.setSalary(30000);
//        user.setRole(Role.USER);
//        user.setRegisterDate(new Date());
//
//        when(userService.getUser(id)).thenReturn(user);
//
//        RequestBuilder request = MockMvcRequestBuilders.get("/api/users/" + id.toString())
//                .contentType(MediaType.APPLICATION_JSON);
//
//        ResultActions result = mockMvc.perform(request);
//
//        result.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstname"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("lastname"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("some address"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNo").value("0000000000"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.referenceCode").value("201908080000"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.memberClassify").value("Gold"))
//                .andReturn();
//    }
}
