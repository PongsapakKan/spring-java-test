package com.userinfo.converters;

import com.userinfo.models.api.requests.UserRegistration;
import com.userinfo.models.api.response.UserResponse;
import com.userinfo.models.entities.User;

public interface UserConverter {
    User convertRegistrationToEntity(final UserRegistration userRegistration);
    UserResponse convertUserToUserResponse(final User user);
}
