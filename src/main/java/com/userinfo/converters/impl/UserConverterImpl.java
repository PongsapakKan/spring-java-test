package com.userinfo.converters.impl;

import com.userinfo.converters.UserConverter;
import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.UserResponse;
import com.userinfo.models.entities.MemberClassification;
import com.userinfo.models.entities.Role;
import com.userinfo.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class UserConverterImpl implements UserConverter {

    private Clock clock;

    @Autowired
    public UserConverterImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public User convertRegistrationToEntity(UserRegistration registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(registration.getPassword());
        user.setPassword(password);
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setPhoneNo(registration.getPhoneNo());
        user.setAddress(registration.getAddress());
        user.setSalary(registration.getSalary());
        user.setMemberClassification(MemberClassification.valueOf(registration.getSalary()));
        user.setRole(Role.USER);
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
        LocalDateTime ldt = LocalDateTime.now(clock);
        Date now = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        user.setRegisterDate(now);
        user.setReferenceCode(format.format(now) + registration.getPhoneNo().substring(registration.getPhoneNo().length() - 4));
        return user;
    }

    @Override
    public UserResponse convertUserToUserResponse(User user) {
        UserResponse ur = new UserResponse();
        ur.setFirstName(user.getFirstName());
        ur.setLastName(user.getLastName());
        ur.setAddress(user.getAddress());
        ur.setId(user.getId().toString());
        ur.setPhoneNo(user.getPhoneNo());
        ur.setReferenceCode(user.getReferenceCode());
        ur.setMemberClassify(user.getMemberClassification().name());
        ur.setSalary(user.getSalary());
        return ur;
    }
}
