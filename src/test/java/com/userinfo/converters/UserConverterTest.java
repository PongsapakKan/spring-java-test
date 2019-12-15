package com.userinfo.converters;

import com.userinfo.converters.impl.UserConverterImpl;
import com.userinfo.exceptions.SalaryNotMatchClassifyException;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.UserResponse;
import com.userinfo.models.entities.MemberClassification;
import com.userinfo.models.entities.Role;
import com.userinfo.models.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserConverterTest {

    private UserConverterImpl userConverter;

    @Before
    public void setup() {
        userConverter = new UserConverterImpl(Clock.fixed(Instant.parse("2019-12-15T03:20:49.691Z"), ZoneId.of("UTC")));
    }

    @Test
    public void testConvertRegistrationToEntitySuccessfullyWithGoldClassify() {
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(30000);

        User user = userConverter.convertRegistrationToEntity(ur);

        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getFirstName()).isEqualTo("firstname");
        assertThat(user.getLastName()).isEqualTo("lastname");
        assertThat(user.getPhoneNo()).isEqualTo("0000000000");
        assertThat(user.getAddress()).isEqualTo("some address");
        assertThat(user.getReferenceCode()).isEqualTo("201912150000");
        assertThat(user.getSalary()).isEqualTo(30000);
        assertThat(user.getMemberClassification()).isEqualTo(MemberClassification.Gold);
    }

    @Test
    public void testConvertRegistrationToEntitySuccessfullyWithPlatinumClassify() {
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(60000);

        User user = userConverter.convertRegistrationToEntity(ur);

        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getFirstName()).isEqualTo("firstname");
        assertThat(user.getLastName()).isEqualTo("lastname");
        assertThat(user.getPhoneNo()).isEqualTo("0000000000");
        assertThat(user.getAddress()).isEqualTo("some address");
        assertThat(user.getReferenceCode()).isEqualTo("201912150000");
        assertThat(user.getSalary()).isEqualTo(60000);
        assertThat(user.getMemberClassification()).isEqualTo(MemberClassification.Platinum);
    }

    @Test
    public void testConvertRegistrationToEntitySuccessfullyWithSilverClassify() {
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(15000);

        User user = userConverter.convertRegistrationToEntity(ur);

        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getFirstName()).isEqualTo("firstname");
        assertThat(user.getLastName()).isEqualTo("lastname");
        assertThat(user.getPhoneNo()).isEqualTo("0000000000");
        assertThat(user.getAddress()).isEqualTo("some address");
        assertThat(user.getReferenceCode()).isEqualTo("201912150000");
        assertThat(user.getSalary()).isEqualTo(15000);
        assertThat(user.getMemberClassification()).isEqualTo(MemberClassification.Silver);
    }

    @Test(expected = SalaryNotMatchClassifyException.class)
    public void testConvertRegistrationToEntityFailWithInvalidSalary() {
        UserRegistration ur = new UserRegistration();
        ur.setUsername("username");
        ur.setPassword("password");
        ur.setPhoneNo("0000000000");
        ur.setFirstName("firstname");
        ur.setLastName("lastname");
        ur.setAddress("some address");
        ur.setSalary(110);
        userConverter.convertRegistrationToEntity(ur);
    }

    @Test
    public void testConvertUserToUserResponseSuccessfully() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);
        user.setPhoneNo("0000000000");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setAddress("some address");
        user.setReferenceCode("201912150000");
        user.setMemberClassification(MemberClassification.Gold);
        user.setSalary(30000);
        user.setRole(Role.USER);
        Date date = Date.from(Instant.parse("2019-12-15T03:20:49.691Z"));
        user.setRegisterDate(date);

        UserResponse userResponse = userConverter.convertUserToUserResponse(user);
        assertThat(userResponse.getFirstName()).isEqualTo("firstname");
        assertThat(userResponse.getLastName()).isEqualTo("lastname");
        assertThat(userResponse.getPhoneNo()).isEqualTo("0000000000");
        assertThat(userResponse.getReferenceCode()).isEqualTo("201912150000");
        assertThat(userResponse.getAddress()).isEqualTo("some address");
        assertThat(userResponse.getMemberClassify()).isEqualTo("Gold");
        assertThat(userResponse.getSalary()).isEqualTo(30000);
    }
}
