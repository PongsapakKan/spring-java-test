package com.userinfo.models.api.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserCredentials {
    @NotNull @Length(min = 5)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Invalid username.")
    private String username;

    @NotNull @Length(min = 5)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Invalid password.")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
