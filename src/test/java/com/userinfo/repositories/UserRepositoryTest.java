package com.userinfo.repositories;

import com.userinfo.models.entities.MemberClassification;
import com.userinfo.models.entities.Role;
import com.userinfo.models.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @After
    public void down() throws Exception {
        userRepository.deleteAll();
    }

    @Before
    public void up() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setSalary(15000);
        user.setAddress("test address");
        user.setRole(Role.USER);
        user.setRegisterDate(new Date());
        user.setReferenceCode("2148787887");
        user.setPhoneNo("0000000000");
        user.setMemberClassification(MemberClassification.Silver);
        user.setFirstName("firstnametest");
        user.setLastName("lastnametest");
        userRepository.save(user);
    }

    @Test
    public void findOneByUsernameWithExistsUser() {
        Optional<User> ou = userRepository.findOneByUsername("username");
        assertThat(ou.isPresent());
    }

    @Test
    public void findOneByUsernameNotExistsUser() {
        Optional<User> ou = userRepository.findOneByUsername("test");
        assertThat(ou.isPresent()).isFalse();
    }
}
