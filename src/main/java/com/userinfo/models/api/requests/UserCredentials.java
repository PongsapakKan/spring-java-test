package com.userinfo.models.api.requests;

import javax.validation.constraints.NotNull;

public class UserCredentials {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
